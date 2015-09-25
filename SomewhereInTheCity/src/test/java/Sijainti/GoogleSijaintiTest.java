/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sijainti;

import com.google.maps.model.LatLng;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kaisa
 */
public class GoogleSijaintiTest {
    
    private static GoogleSijainti gs, gs2;
    
    
    /**
     * Testataan parametrillista konstruktoria
      */
 
    
    @Test
    public void KonstruktoriVaarillaParametreillaTest() {
        gs = new GoogleSijainti(200,0);
        assertEquals(false,gs.onkoPaikannettu());
    }
    
    
    @Before
    public void setUp() {
        gs = new GoogleSijainti();
    }


    /**
     * Testaa sijainnin arpomista.
     */
    @Test
    public void testArvoOmaSijainti() {
        assertEquals(true, gs.arvoOmaSijainti());
    }

    /**
     * Testaa setKey metodia, onko parametri 40-merkkinen?
     */
    @Test
    public void testSetKey_String_String() {
        assertEquals(true, gs.setKey("avain1", 
                "1234567890123456789012345678901234567890"));
    }

    /**
     * Testaa setSijainti-metodia kahdella Double-arvolla.
     */
    @Test
    public void testSetSijaintiPitkaDouble() {
        gs.setSijainti(10.000000000000000000000000000000000000001,
                -20.599999999999999999999999999999999);
        assertEquals(true, gs.onkoPaikannettu());
    }
    
    @Test
    public void testSetSijaintiEpakelvotArvot() {
        gs.setSijainti(1000.0, 200.1);
        assertEquals(false, gs.onkoPaikannettu());
    }

    /**
     * Testaa parametritöntä setSijainti-metodia.
     */
    @Test
    public void testSetSijainti() {
        assertEquals(true,gs.setSijainti());
    }

    /**
     * Testaa getKoordinaatit metodia.
     */
    @Test
    public void testGetKoordinaatit() {
        LatLng verrokki = new LatLng(60.000, 24.500);
        System.out.println(gs.setSijainti(60.000, 24.500));
        assertEquals(gs.getKoordinaatit().lat,verrokki.lat, 0.000001);
        
    }

    @After
    public void tearDown() {
    }
}
