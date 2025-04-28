package br.sp.vinilourenco.test;

import br.sp.vinilourenco.core.BaseTest;
import br.sp.vinilourenco.core.DSL;
import br.sp.vinilourenco.page.CampoTreinamentoPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Alert;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static br.sp.vinilourenco.core.DriverFactory.*;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends BaseTest {

    private DSL dsl;
    private CampoTreinamentoPage page;

    @Parameterized.Parameter
    public String nome;
    @Parameterized.Parameter(value = 1)
    public String sobrenome;
    @Parameterized.Parameter(value = 2)
    public String sexo;
    @Parameterized.Parameter(value = 3)
    public List<String> comidas;
    @Parameterized.Parameter(value = 4)
    public String[] esportes;
    @Parameterized.Parameter(value = 5)
    public String msg;


    @Before
    public void inicializa() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
        page = new CampoTreinamentoPage();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getCollection() {
        return Arrays.asList(new Object[][] {
                {"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
                {"Vinicius", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
                {"Vinicius", "Lourenço", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
                {"Vinicius", "Lourenço", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
                {"Vinicius", "Lourenço", "Masculino", Arrays.asList("Carne"), new String[]{"Futebol", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
        });
    }

    @Test
    public void deveValidarRegras() {
        page.setNome(nome);
        page.setSobrenome(sobrenome);
        if (sexo.equals("Masculino")) {
            page.setSexoMasculino();
        }
        if (sexo.equals("Feminino")) {
            page.setSexoFeminino();
        }
        if (comidas.contains("Carne")) page.setComidaCarne();
        if (comidas.contains("Pizza")) page.setComidaPizza();
        if (comidas.contains("Vegetariano")) page.setComidaVegetariano();
        page.setEsporte(esportes);
        page.cadastrar();
        Alert alert = getDriver().switchTo().alert();
        System.out.println(msg);
        Assert.assertEquals(msg, alert.getText());
    }
}
