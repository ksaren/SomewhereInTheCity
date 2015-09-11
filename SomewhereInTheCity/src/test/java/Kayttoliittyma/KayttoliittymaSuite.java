/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author kaisa
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({Kayttoliittyma.GUITest.class, Kayttoliittyma.TekstiliittymaTest.class, Kayttoliittyma.ValikkoTest.class})
public class KayttoliittymaSuite {

    @Before
    public void setUp() throws Exception {
    }
    
}
