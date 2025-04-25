import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestePrime {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializa() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,765));

        dsl = new DSL(driver);
    }

    @After
    public void finaliza() {
//        driver.quit();
    }

    @Test
    public void interageComRadioPrime() {
        driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=228b0");
        dsl.clicarRadio(By.xpath("//input[@id='j_idt249:line:0']/../..//span"));
        Assert.assertTrue(dsl.isRadioMarcado("j_idt249:line:0"));
    }

    @Test
    public void interageComBasicComboBox() {
        driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=07770");
        dsl.selecionarComboPrime("j_idt248:option", "Option3");
        Assert.assertEquals("Option3", dsl.obterTexto("j_idt248:option_label"));
    }
}
