/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import static Kayttajat.Asiakas.*;
import org.junit.After;
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

    @Test
    public void konstruktoriToimii() {
        a = new Asiakas("Mina Vaanikainen", "minavaan", "helppo", "helppo");
        assertEquals("minavaan", a.getTunnus());
        
    }

    @Before
    public void setUp() {
        a = new Asiakas("Teppo Testaaja", "tt", "123456", "123456");
        b = new Asiakas("Kaisa Kahvikissa", "kk", "JAVACoffee", "LATTECoffee");
        t = new Toimija("Helppo Hodari", "hh", "lallallaa", "lallallaa");
    }

    @After
    public void tearDown() throws Exception {
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

    @Test
    public void setSalasana() {
        assertEquals(a.setSalasana("moi", "iom"), false);

    }

    /**
     * Test of asiakasListalle method, of class Asiakas.
     */
    @Test
    public void testAsiakasListalle() {
        assertEquals(asiakasListalle(a), true);
    }


}
