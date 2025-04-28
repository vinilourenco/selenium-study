package br.sp.vinilourenco.suites;

import br.sp.vinilourenco.test.DesafioRegrasDeNegocio;
import br.sp.vinilourenco.test.TesteCampoTreinamento;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
       DesafioRegrasDeNegocio.class,
       TesteCampoTreinamento.class
})
public class SuiteTeste {
}
