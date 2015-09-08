/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author kaisa
 */
public class Toimija implements Kayttaja {

    private static int uusinToimija = 1000;
    private static Set<Toimija> toimijat = new HashSet();

    private String karrynNimi;
    private int karryNro;

    public Toimija(String toimijanNimi) {
        for (Toimija t : toimijat) {
            if (t.karrynNimi != toimijanNimi) {
                this.karrynNimi = toimijanNimi;
                if (toimijaListalle(this))
                    System.out.println("Toimija lisatty.");
            }
        }
    }
    
    public static boolean toimijaListalle(Toimija uusiKarry) {
        if (toimijat.add(uusiKarry)) {
            uusinToimija++;
            return true;
        } else {
            return false;
        }
    }
    
    

}
