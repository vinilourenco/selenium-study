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
    private CampoTreinamentoPage page;
    private final String URL = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"; //URL

    @Before
    public void inicializa() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get(URL);
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void testeTextField() {
        page.setNome("Teste de escrita");
        Assert.assertEquals("Teste de escrita", page.obterValorCampoNome());
    }

    @Test
    public void testTextFieldDuplo() {
        page.setNome("Vinicius");
        Assert.assertEquals("Vinicius", page.obterValorCampoNome());
        page.setSobrenome("Lourenço");
        Assert.assertEquals("Lourenço", page.obterValorCampoSobrenome());

    }

    @Test
    public void testeTextArea() {
        page.setSugestoes("Teste TextArea\n\nTeste linha\nÚltima linha");
        Assert.assertEquals("Teste TextArea\n\nTeste linha\nÚltima linha", page.obterValorCampoSugestoes());
    }

    @Test
    public void testeRadioButton() {
        page.setSexoMasculino();
        Assert.assertTrue(page.obterRadioButtonMasculino());
    }

    @Test
    public void testeCheckBox() {
        page.setComidaPizza();
        Assert.assertTrue(page.obterValorCheckboxPizza());
    }

    @Test
    public void testeCombo() {
        page.setEscolaridade("2o grau completo");
        Assert.assertEquals("2o grau completo", page.obterValorCampoEscolaridade());
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
        page.setEsporte("Natacao", "Corrida", "O que eh esporte?");

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
        page.botaoSimples();
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void testeLink() {
        page.clicarLink();
        // Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }

    @Test
    public void testeBuscarTextosNaPagina() {
        Assert.assertEquals("Campo de Treinamento", dsl.obterTagName("h3"));
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterClassName("facilAchar"));
    }
}
