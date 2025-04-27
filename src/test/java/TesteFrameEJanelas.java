import br.sp.vinilourenco.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrameEJanelas {

    private WebDriver driver;
    private final String URL = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html"; //URL
    private DSL dsl;

    @Before
    public void inicializa() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        dsl = new DSL(driver);
        driver.get(URL);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void testeFrames() {
        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);
        alert.accept();

        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
    }

    @Test
    public void interageFrameEscondido() {
        WebElement frame = driver.findElement(By.id("frame2"));
        dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
        driver.switchTo().frame("frame2");
        driver.findElement(By.id("frameButton")).click();
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);
    }

    @Test
    public void testeJanelas() {
        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo!");
        driver.close();
        driver.switchTo().window("");
        driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
    }

    @Test
    public void testeJanelaSemTitulo() {
        driver.findElement(By.id("buttonPopUpHard")).click();
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
    }
}
