import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DesafioRegrasDeNegocio {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializa() {
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void testeCampoNomeObrigatorio() {
        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
    }

    @Test
    public void testeCampoSobrenomeObrigatorio() {
        dsl.escrever("elementosForm:nome", "Nome");
        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    }

    @Test
    public void testeSexoObrigatorio() {
        dsl.escrever("elementosForm:nome", "Nome");
        dsl.escrever("elementosForm:sobrenome", "Sobrenome");
        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
    }

    @Test
    public void testeRegraVegetariano() {
        dsl.escrever("elementosForm:nome", "Nome");
        dsl.escrever("elementosForm:sobrenome", "Sobrenome");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarChecbox("elementosForm:comidaFavorita:0");
        dsl.clicarChecbox("elementosForm:comidaFavorita:3");
        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }

    @Test
    public void testeRegraEsporte() {
        dsl.escrever("elementosForm:nome", "Nome");
        dsl.escrever("elementosForm:sobrenome", "Sobrenome");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarChecbox("elementosForm:comidaFavorita:0");
        new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Futebol");
        new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("O que eh esporte?");
        dsl.clicarBotao("elementosForm:cadastrar");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
    }
}
