import br.sp.vinilourenco.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;

import static br.sp.vinilourenco.core.DriverFactory.*;

public class TesteFrameEJanelas {

    private final String URL = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"; //URL
    private DSL dsl;

    @Before
    public void inicializa() {
        getDriver().get(URL);
        dsl = new DSL();
    }

    @After
    public void finaliza() {
        killDriver();
    }

    @Test
    public void testeFrames() {
        getDriver().switchTo().frame("frame1");
        getDriver().findElement(By.id("frameButton")).click();
        Alert alert = getDriver().switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);
        alert.accept();

        getDriver().switchTo().defaultContent();
        getDriver().findElement(By.id("elementosForm:nome")).sendKeys(msg);
    }

    @Test
    public void interageFrameEscondido() {
        WebElement frame = getDriver().findElement(By.id("frame2"));
        dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
        getDriver().switchTo().frame("frame2");
        getDriver().findElement(By.id("frameButton")).click();
        Alert alert = getDriver().switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);
    }

    @Test
    public void testeJanelas() {
        getDriver().findElement(By.id("buttonPopUpEasy")).click();
        getDriver().switchTo().window("Popup");
        getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo!");
        getDriver().close();
        getDriver().switchTo().window("");
        getDriver().findElement(By.tagName("textarea")).sendKeys("E agora?");
    }

    @Test
    public void testeJanelaSemTitulo() {
        getDriver().findElement(By.id("buttonPopUpHard")).click();
        System.out.println(getDriver().getWindowHandle());
        System.out.println(getDriver().getWindowHandles());
        getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[1]);
        getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[0]);
        getDriver().findElement(By.tagName("textarea")).sendKeys("E agora?");
    }
}
