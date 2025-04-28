package br.sp.vinilourenco.core;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static br.sp.vinilourenco.core.DriverFactory.getDriver;
import static br.sp.vinilourenco.core.DriverFactory.killDriver;

public class BaseTest {

    @Rule
    public TestName testName = new TestName();

    @After
    public void finaliza() throws IOException {
        TakesScreenshot ss = (TakesScreenshot) getDriver();
        File archive = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(archive, new File("target" + File.separator + "screenshot" +
                File.separator + testName.getMethodName() + ".jpg"));

        if (Properties.FECHAR_BROWSER) {
            killDriver();
        }
    }
}
