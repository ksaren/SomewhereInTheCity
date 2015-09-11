/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import static Kayttajat.Toimija.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kaisa
 */
public class ToimijaTest {

    private static Toimija t;

    @Test
    public void konstruktoriTest() {
        t = new Toimija("testeri");
        assertEquals("testeri",t.getNimi());
    }

    @Before
    public void setUp() {
        t = new Toimija("testiToimija");
    }

    @Test
    public void poistoOnnistuuTest() {
        assertEquals(poistaToimija(t), true);
    }

}
