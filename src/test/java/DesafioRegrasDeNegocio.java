import br.sp.vinilourenco.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;

import static br.sp.vinilourenco.core.DriverFactory.*;

public class DesafioRegrasDeNegocio {

    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void inicializa() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
        page = new CampoTreinamentoPage();
    }

    @After
    public void finaliza() {
        killDriver();
    }

    @Test
    public void testeCampoNomeObrigatorio() {
        page.cadastrar();
        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
    }

    @Test
    public void testeCampoSobrenomeObrigatorio() {
        page.setNome("Nome");
        page.cadastrar();
        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    }

    @Test
    public void testeSexoObrigatorio() {
        page.setNome("Nome");
        page.setSobrenome("Sobrenome");
        page.cadastrar();
        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
    }

    @Test
    public void testeRegraVegetariano() {
        page.setNome("Nome");
        page.setSobrenome("Sobrenome");
        page.setSexoFeminino();
        page.setComidaCarne();
        page.setComidaVegetariano();
        page.cadastrar();
        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }

    @Test
    public void testeRegraEsporte() {
        page.setNome("Nome");
        page.setSobrenome("Sobrenome");
        page.setSexoMasculino();
        page.setComidaCarne();
        page.setEsporte("Futebol", "O que eh esporte?");
        page.cadastrar();
        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
    }
}
