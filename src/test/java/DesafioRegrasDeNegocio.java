import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DesafioRegrasDeNegocio {

    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void inicializa() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void testeCampoNomeObrigatorio() {
        page.cadastrar();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
    }

    @Test
    public void testeCampoSobrenomeObrigatorio() {
        page.setNome("Nome");
        page.cadastrar();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    }

    @Test
    public void testeSexoObrigatorio() {
        page.setNome("Nome");
        page.setSobrenome("Sobrenome");
        page.cadastrar();
        Alert alert = driver.switchTo().alert();
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
        Alert alert = driver.switchTo().alert();
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
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
    }
}
