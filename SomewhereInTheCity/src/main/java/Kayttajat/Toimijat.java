/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import java.io.*;
import java.util.*;

/**
 * Luokka Toimijoiden varastointiin. Tämän luokan toimijat on tarkoitus tallentaa
 * oliotiedostoon.
 *
 * @author kaisa
 */
public class Toimijat implements Serializable {

    private Set<Toimija> toimijat = new HashSet();

    public boolean onkoNimiVapaa(Toimija testattava) {
        boolean ok = true;
        for (Toimija t : toimijat) {
            if (t.getNimi().equalsIgnoreCase(testattava.getNimi())) {
                ok = false;
                break;
            }
        }
        return ok;
    }
    
    public boolean lisaa(Toimija t) {
        //pitaiskö heittää oma poikkeus jos ei onnistu?
        if(this.toimijat.add(t)) 
            return true;
        else return false;
    }
    
    public boolean poista(Toimija p) {
        if(this.toimijat.remove(p))
            return true;
        else return false;
    }

}
