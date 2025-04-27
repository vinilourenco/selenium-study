import br.sp.vinilourenco.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static br.sp.vinilourenco.core.DriverFactory.*;

public class TestePrime {

    private DSL dsl;

    @Before
    public void inicializa() {
        dsl = new DSL();
    }

    @After
    public void finaliza() {
        killDriver();
    }

    @Test
    public void interageComRadioPrime() {
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=228b0");
        dsl.clicarRadio(By.xpath("//input[@id='j_idt249:line:0']/../..//span"));
        Assert.assertTrue(dsl.isRadioMarcado("j_idt249:line:0"));
    }

    @Test
    public void interageComBasicComboBox() {
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=07770");
        dsl.selecionarComboPrime("j_idt248:option", "Option3");
        Assert.assertEquals("Option3", dsl.obterTexto("j_idt248:option_label"));
    }
}
