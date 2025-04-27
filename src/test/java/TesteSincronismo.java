import br.sp.vinilourenco.core.DSL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static br.sp.vinilourenco.core.DriverFactory.*;

public class TesteSincronismo {

    private DSL dsl;
    private CampoTreinamentoPage page;
    private final String URL = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"; //URL

    @Before
    public void inicializa() {
        getDriver().get(URL);
        dsl = new DSL();
        page = new CampoTreinamentoPage();
    }

    @After
    public void finaliza() {
        killDriver();
    }

    @Test
    public void esperaFixa() throws InterruptedException {
        dsl.clicarBotao("buttonDelay");
        Thread.sleep(5000);
        dsl.escrever("novoCampo", "Teste");
    }

    @Test
    public void esperaImplicita() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        dsl.clicarBotao("buttonDelay");
        dsl.escrever("novoCampo", "Teste");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    @Test
    public void esperaExplicita() {
        dsl.clicarBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escrever("novoCampo", "Teste");
    }
}
