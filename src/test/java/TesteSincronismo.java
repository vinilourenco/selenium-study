import br.sp.vinilourenco.core.DSL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TesteSincronismo {

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
//        driver.quit();
    }

    @Test
    public void esperaFixa() throws InterruptedException {
        dsl.clicarBotao("buttonDelay");
        Thread.sleep(5000);
        dsl.escrever("novoCampo", "Teste");
    }

    @Test
    public void esperaImplicita() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        dsl.clicarBotao("buttonDelay");
        dsl.escrever("novoCampo", "Teste");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    @Test
    public void esperaExplicita() {
        dsl.clicarBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escrever("novoCampo", "Teste");
    }
}
