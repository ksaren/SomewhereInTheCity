/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import static Kayttajat.Asiakas.*;
import static Kayttajat.Toimija.poistaToimija;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kaisa
 */
public class AsiakasTest {

    private static Asiakas a, b;
    private static Toimija t;

    @Before
    public void setUp() {
        a = new Asiakas("Teppo Testaaja", "tt", "123456", "123456");
        t = new Toimija("Helppo Hodari", "hh", "lallallaa", "lallallaa");
    }

    @After
    public void tearDown() {
        poistaToimija(t);
        poistaAsiakas(a);
        poistaAsiakas(b);
        a = null;
        b = null;
        t = null;

    }

    @Test
    public void konstruktoriToimii() {
        b = new Asiakas("Mina Vaanikainen", "minavaan", "helppo", "helppo");
        assertEquals("minavaan", b.getTunnus());

    }

    @Test
    public void konstruktoriHeittaaPoikkeuksen() {
        String ilmo = "";
        try {
            b = new Asiakas("Kaisa Kahvikissa", "kk", "JAVACoffee", "LATTECoffee");
        } catch (IllegalArgumentException IE) {
            ilmo = IE.getMessage();
        }
        assertEquals(ilmo, "Jokin parametreista ei kelpaa.");

    }

    @Test
    public void testSetSuosikki() {
        assertEquals(a.setSuosikki(t), true);
    }

    @Test
    public void setSuosikkiLisaaListalleNullin() {
        Toimija lemppari = null;
        assertEquals(a.setSuosikki(lemppari), false);
    }

    public void yksiLisaaAsiakkailla() {
        b = new Asiakas("Allu Asiakas", "allu", "lullaa", "lullaa");
        assertEquals(a.getNro(), b.getNro()-2);
    }
    
    @Test
    public void setSalasana() {
        assertEquals(a.setSalasana("moi", "iom"), false);

    }

    /*@Test
     public void AsiakasListalleUudelleen() {
     assertEquals(asiakasListalle(a), false);
     }*/
}
