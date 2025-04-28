package br.sp.vinilourenco.suites;

import br.sp.vinilourenco.test.DesafioCadastro;
import br.sp.vinilourenco.test.TesteRegrasCadastro;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static br.sp.vinilourenco.core.DriverFactory.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
       DesafioCadastro.class,
       TesteRegrasCadastro.class
})
public class SuiteTeste {

    @AfterClass
    public static void finalizaTudo() {
        killDriver();
    }
}
