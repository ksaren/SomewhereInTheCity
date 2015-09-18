/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

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
        a = new Asiakas("minavaan");
        assertEquals("minavaan", a.getNimi());
    }

    @Before
    public void setUp() {
        a = new Asiakas("testiasiakas");
        b = new Asiakas("toinenAsiakas");
        t = new Toimija("testitoimija");
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

}
