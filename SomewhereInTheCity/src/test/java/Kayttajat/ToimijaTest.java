/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import static Kayttajat.Toimija.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kaisa
 */
public class ToimijaTest {

    private static Toimija tm;

    @Test
    public void konstruktoriTest() {
        tm = new Toimija("Suvin Sumppila", "susu", "pannukuuma", "pannukuuma");
        assertEquals("Suvin Sumppila",tm.getNimi());
    }

    @Before
    public void setUp() {
        tm = new Toimija("Testaamo", "tm", "password", "password");
    }
    
    @After
    public void tearDown() {
        poistaToimija(tm);
    }
    
    @Test
    public void toimijaOnJoListallaTest() {
        toimijaListalle(tm);
        assertEquals(toimijaListalle(tm), false);
    }
    

}
