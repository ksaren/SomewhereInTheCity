/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

/** Testiluokka yhteisten käyttäjätoimintojen testaamiseen.
 *
 * @author kaisa
 */
import static Kayttajat.Asiakas.*;
import static Kayttajat.Toimija.poistaToimija;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KayttajaTest {

    private static Asiakas a, b;
    private static Toimija t, v;
    private static Kayttaja k, u;

    @Before
    public void setUp() {
        a = new Asiakas("Teppo Testaaja", "tt", "123456", "123456");
        t = new Toimija("Helppo Hodari", "hh", "lallallaa", "lallallaa");
    }

    public void kayttajaTyypitSamallaListalla() {
        assertEquals(a.getNro(), t.getNro()-1);
    }
    
    //Ei nappaa poikkeusta oikein?
    /*@Test
    public void konstruktoriHeittaaPoikkeuksenKunSamatTiedot() {
        String ilmo = "";
        try {
            k = new Asiakas("Teppo Testaaja", "tt", "123456", "123456");
        } catch (AlreadyDefinedException ADE) {
            ilmo = ADE.getMessage();
            System.out.println(ilmo);
        }
        assertEquals("Käyttäjä on jo listalla.", ilmo);

    }*/

    @After
    public void tearDown() {
        poistaAsiakas(a);
        a = null;
        poistaAsiakas(b);
        b = null;
        poistaToimija(t);
        t = null;
        v = null;
        k = null;
        u = null;
        

    }

}
