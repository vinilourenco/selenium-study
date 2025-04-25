import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TesteAjax {

    private WebDriver driver;
    private DSL dsl;
    private final String URL = "https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=6b159";

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
    public void testAjax() {
        dsl.escrever("j_idt248:name", "Teste");
        dsl.clicarBotao("j_idt248:j_idt252");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBe(By.id("j_idt248:display"), "Teste"));
        Assert.assertEquals("Teste", dsl.obterTexto("j_idt248:display"));
    }
}
