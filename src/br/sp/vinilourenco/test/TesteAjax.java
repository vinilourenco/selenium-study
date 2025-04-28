package br.sp.vinilourenco.test;

import br.sp.vinilourenco.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static br.sp.vinilourenco.core.DriverFactory.*;

public class TesteAjax {

    private DSL dsl;
    private final String URL = "https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=6b159";

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
    public void testAjax() {
        dsl.escrever("j_idt248:name", "Teste");
        dsl.clicarBotao("j_idt248:j_idt252");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBe(By.id("j_idt248:display"), "Teste"));
        Assert.assertEquals("Teste", dsl.obterTexto("j_idt248:display"));
    }
}
