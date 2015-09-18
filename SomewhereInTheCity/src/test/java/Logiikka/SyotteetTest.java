/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

import static Logiikka.Syotteet.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kaisa
 */
public class SyotteetTest {
    
    private static Syotteet s;
    
    
    @Before
    public void setUp() {
        s = new Syotteet();
        
    }
    
    @Test
    public void testaaSyoteTest() {
        assertEquals((testaaSyote("moi3","moi3")),true);
    }
    
    @Test
    public void testaaSyoteNumeroilla() {
        assertEquals((testaaSyote(3,5)),false);
    }
    
    @Test
    public void testaaSyoteVaihtoehdoilla() {
        assertEquals((testaaSyote("moi","hei","moi")),true);
    }
}
