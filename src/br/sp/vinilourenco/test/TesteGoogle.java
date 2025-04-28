package br.sp.vinilourenco.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static br.sp.vinilourenco.core.DriverFactory.*;

public class TesteGoogle {

    @Before
    public void inicializa() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/vinicius/Desktop/Automações de Teste/drivers/chromedriver.exe");
        getDriver().get("http://www.google.com");
    }

    @After
    public void finaliza() {
        killDriver();
    }

    @Test
    public void teste() {
        Assert.assertEquals("Google", getDriver().getTitle());
    }
}
