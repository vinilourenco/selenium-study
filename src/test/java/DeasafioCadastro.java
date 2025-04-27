import br.sp.vinilourenco.core.DSL;
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
    private CampoTreinamentoPage page;
    private final String URL = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

    @Before
    public void inicializa() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,765));
        driver.get(URL);
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void testeCadastro (){

        page.setNome("Vinícius");
        page.setSobrenome("Lourenço");
        page.setSexoMasculino();
        page.setComidaPizza();
        page.setEscolaridade("Superior");
        page.setEsporte("Futebol");
        page.cadastrar();

        Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
        Assert.assertEquals("Vinícius", page.obterNomeCadastro());
        Assert.assertEquals("Lourenço", page.obterSobrenomeomeCadastro());
        Assert.assertEquals("Masculino", page.obterSexoCadastro());
        Assert.assertEquals("Pizza", page.obterComidaCadastro());
        Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
        Assert.assertEquals("Futebol", page.obterEsporteCadastro());
    }
}
