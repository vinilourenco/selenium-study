import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {

    private WebDriver driver;
    private DSL dsl;
    private final String URL = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"; //URL

    @Before
    public void inicializa() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get(URL);
        dsl = new DSL(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void testeTextField() {
        dsl.escrever("elementosForm:nome", "Teste de escrita");
        Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void testTextFieldDuplo() {
        dsl.escrever("elementosForm:nome", "Vinicius");
        Assert.assertEquals("Vinicius", dsl.obterValorCampo("elementosForm:nome"));
        dsl.escrever("elementosForm:nome", "Lourenço");
        Assert.assertEquals("Lourenço", dsl.obterValorCampo("elementosForm:nome"));

    }

    @Test
    public void testeTextArea() {
        dsl.escrever("elementosForm:sugestoes", "Teste TextArea\n\nTeste linha\nÚltima linha");
        Assert.assertEquals("Teste TextArea\n\nTeste linha\nÚltima linha", dsl.obterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void testeRadioButton() {
        dsl.clicarRadio("elementosForm:sexo:0");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void testeCheckBox() {
        dsl.clicarChecbox("elementosForm:comidaFavorita:2");
        Assert.assertTrue(dsl.isCheckboxMarcado("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void testeCombo() {
        dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
        Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
    }

    @Test
    public void testeVerificarValoresCombo() {
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        boolean encontrou = false;
        for(WebElement option: options) {
            if (option.getText().equals("Mestrado")) {
                encontrou = true;
            }
        }
        Assert.assertTrue(encontrou);
    }

    @Test
    public void testeComboMultiplaEscolha() {
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size());

        combo.deselectByVisibleText("Corrida");
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());
    }

    @Test
    public void testeInteragirComBotao() {
        dsl.clicarBotao("buttonSimple");
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void testeLink() {
        dsl.clicarLink("Voltar");
        // Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }

    @Test
    public void testeBuscarTextosNaPagina() {
        Assert.assertEquals("Campo de Treinamento", dsl.obterTagName("h3"));
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterClassName("facilAchar"));
    }
}
