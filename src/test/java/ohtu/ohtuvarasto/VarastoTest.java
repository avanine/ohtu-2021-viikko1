package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void kayttokelvotonVarastoNollataan() {
        Varasto huonoVarasto = new Varasto(-10);
        assertEquals(0.0, huonoVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldollinenTilavuusNollataan() {
        Varasto huonoVarasto = new Varasto(-10, -5);
        assertEquals(0.0, huonoVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenAlkusaldoNollataan() {
        Varasto huonoVarasto = new Varasto(-10, -5);
        assertEquals(0.0, huonoVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldollinenVarastoToimii() {
        Varasto saldollinenVarasto = new Varasto(10, 5);
        assertEquals(10, saldollinenVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, saldollinenVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldollinenVarastoEiTaytyYli() {
        Varasto saldollinenVarasto = new Varasto(5, 10);
        assertEquals(5, saldollinenVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonTayttaminenOnnistuu() {
        Varasto saldollinenVarasto = new Varasto(10, 5);
        saldollinenVarasto.lisaaVarastoon(-2);
        assertEquals(5, saldollinenVarasto.getSaldo(), vertailuTarkkuus);
        saldollinenVarasto.lisaaVarastoon(2);
        assertEquals(7, saldollinenVarasto.getSaldo(), vertailuTarkkuus);
        saldollinenVarasto.lisaaVarastoon(5);
        assertEquals(10, saldollinenVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaOttaminenOnnistuu() {
        Varasto saldollinenVarasto = new Varasto(10, 5);
        saldollinenVarasto.otaVarastosta(-2);
        assertEquals(5, saldollinenVarasto.getSaldo(), vertailuTarkkuus);
        saldollinenVarasto.otaVarastosta(2);
        assertEquals(3, saldollinenVarasto.getSaldo(), vertailuTarkkuus);
        saldollinenVarasto.otaVarastosta(5);
        assertEquals(0, saldollinenVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoesitysOnOikein() {
        Varasto testivarasto = new Varasto(10, 5);
        assertEquals("saldo = 4.0, vielä tilaa 5.0", testivarasto.toString());
    }

}