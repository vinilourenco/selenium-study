import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DeasafioCadastro {

    private WebDriver driver;
    private DSL dsl;
    private final String URL = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

    @Before
    public void inicializa() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,765));
        driver.get(URL);
        dsl = new DSL(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void testeCadastro (){

        dsl.escrever("elementosForm:nome", "Vinícius");
        dsl.escrever("elementosForm:sobrenome", "Lourenço");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
        dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
        dsl.selecionarCombo("elementosForm:esportes", "Futebol");
        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
        Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Vinícius"));
        Assert.assertEquals("Sobrenome: Lourenço", dsl.obterTexto("descSobrenome"));
        Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
        Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
        Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
        Assert.assertEquals("Esportes: Futebol", dsl.obterTexto("descEsportes"));
    }
}
