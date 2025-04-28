package br.sp.vinilourenco.test;

import static br.sp.vinilourenco.core.DriverFactory.getDriver;

import br.sp.vinilourenco.core.BaseTest;
import br.sp.vinilourenco.page.CampoTreinamentoPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DesafioCadastro extends BaseTest {

    private CampoTreinamentoPage page;
    private final String URL = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

    @Before
    public void inicializa() {
        getDriver().get(URL);
        page = new CampoTreinamentoPage();
    }

    @Test
    public void testeCadastro (){

        page.setNome("Vinícius");
        page.setSobrenome("Lourenço");
        page.setSexoMasculino();
        page.setComidaPizza();
        page.setEscolaridade("Superior");
        page.setEsporte("Futebol");
        page.cadastrar();

        Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
        Assert.assertEquals("Vinícius", page.obterNomeCadastro());
        Assert.assertEquals("Lourenço", page.obterSobrenomeomeCadastro());
        Assert.assertEquals("Masculino", page.obterSexoCadastro());
        Assert.assertEquals("Pizza", page.obterComidaCadastro());
        Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
        Assert.assertEquals("Futebol", page.obterEsporteCadastro());
    }
}
