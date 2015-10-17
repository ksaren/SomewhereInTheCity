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

    private static Toimija tm, tr, tp;

    @Test
    public void konstruktoriTest() {
        tp = new Toimija("FineFillari", "fifi", "dining", "dining");
        assertEquals("FineFillari",tp.getNimi());
    }

    @Before
    public void setUp() {
        tm = new Toimija("Testaamo", "tm", "password", "password");
    }
    
    @After
    public void tearDown() {
        poistaToimija(tm);
        poistaToimija(tr);
        tm = null;
        tr = null;
    }
    
    @Test
    public void toimijaOnJoListallaTest() {
         tr = new Toimija("Kallen Kahvila", "fressi", "tosihyva", "tosihyva");
         System.out.println("tr luotu");
        assertEquals(toimijaListalle(tr), false);
    }
    
       public void yksiLisaaAsiakkailla() {
        tr = new Toimija("Kallen Kahvila", "fressi", "tosihyva", "tosihyva");
        assertEquals(tm.getNro(), tr.getNro()-1);
    }
    

}
