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
    public void negaatiivisenMaaranOttaminenPalauttaaNolla() {
        double saatuMaara = varasto.otaVarastosta(-1);
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void liianSuuriOttaminenPalauttaaKokoSaldon() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(10);
        assertEquals(8, saatuMaara, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoesitysOnOikea() {
        varasto.lisaaVarastoon(8);
        assertEquals("saldo = 8.0, vielä tilaa 2.0", varasto.toString());
    }

    @Test
    public void virheellinenAlkutilavuusNollaksi() {
        Varasto toinen = new Varasto(-1);
        assertEquals(0.0, toinen.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenLisaaminenEiMuutaSaldoa() {
        varasto.lisaaVarastoon(8);
        varasto.lisaaVarastoon(-1);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaminenYliTilavuudenAsettaaSaldoksiTilavuuden() {
        varasto.lisaaVarastoon(12);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAsettaaTilavuudenJaSaldonOikein() {
        Varasto toinen = new Varasto(10, 5);
        assertEquals(10, toinen.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, toinen.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenTilavuusSaldonKanssaAsettaaTilavuudeksiNolla() {
        Varasto toinen = new Varasto(-1, 5);
        assertEquals(0, toinen.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenAlkusaldoAsetetaanNollaksi() {
        Varasto toinen = new Varasto(10, -2);
        assertEquals(2, toinen.getSaldo(), vertailuTarkkuus);
    }

}