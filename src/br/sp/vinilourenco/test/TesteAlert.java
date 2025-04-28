package br.sp.vinilourenco.test;

import br.sp.vinilourenco.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static br.sp.vinilourenco.core.DriverFactory.*;
import static br.sp.vinilourenco.core.DriverFactory.getDriver;

public class TesteAlert {

    private DSL dsl;

    @Before
    public void inicializa() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
    }

    @After
    public void finaliza() {
        killDriver();
    }

    @Test
    public void testeAlertSimples() {
        getDriver().findElement(By.id("alert")).click();
        Alert alert = getDriver().switchTo().alert();
        String texto = alert.getText();
        Assert.assertEquals("Alert Simples", texto);
        alert.accept();
        getDriver().findElement(By.id("elementosForm:nome")).sendKeys(texto);
    }

    @Test
    public void testeAlertConfirmado() {
        getDriver().findElement(By.id("confirm")).click();
        Alert alert = getDriver().switchTo().alert();
        String texto = alert.getText();
        Assert.assertEquals("Confirm Simples", texto);
        alert.accept();
        String confirmado = alert.getText();
        Assert.assertEquals("Confirmado", confirmado);
        alert.accept();
    }

    @Test
    public void testeAlertCancelado() {
        getDriver().findElement(By.id("confirm")).click();
        Alert alert = getDriver().switchTo().alert();
        String texto = alert.getText();
        Assert.assertEquals("Confirm Simples", texto);
        alert.dismiss();
        String negado = alert.getText();
        Assert.assertEquals("Negado", negado);
        alert.accept();
    }

    @Test
    public void testePrompt () {
        getDriver().findElement(By.id("prompt")).click();
        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals("Digite um numero", alert.getText());
        alert.sendKeys("12");
        alert.accept();
        Assert.assertEquals("Era 12?", alert.getText());
        alert.accept();
        Assert.assertEquals(":D", alert.getText());
        alert.accept();
    }

}
