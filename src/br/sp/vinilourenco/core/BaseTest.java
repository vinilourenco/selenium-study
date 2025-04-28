package br.sp.vinilourenco.core;

import org.junit.After;

import static br.sp.vinilourenco.core.DriverFactory.killDriver;

public class BaseTest {

    @After
    public void finaliza() {
        killDriver();
    }
}
