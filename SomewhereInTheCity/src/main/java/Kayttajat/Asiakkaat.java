/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import java.io.*;
import java.util.*;

/**Luokka Asiakkaiden varastointiin Tämän luokan asiakkaat on tallennettuna oliotiedostoon.
 *
 * @author kaisa
 */
public class Asiakkaat implements Serializable {
    
    private Set<Asiakas> asiakkaat = new HashSet();
    
    public boolean onkoNimiVapaa(Asiakas testattava) {
        boolean ok = true;
        for (Asiakas a : asiakkaat) {
            if (a.getNimi().equalsIgnoreCase(testattava.getNimi())) {
                ok = false;
                break;
            }
        }
        return ok;
    }
    
    public boolean lisaa(Asiakas a) {
        //pitaiskö heittää oma poikkeus jos ei onnistu?
        if(this.asiakkaat.add(a)) 
            return true;
        else return false;
    }
    
    public boolean poista(Asiakas p) {
        if(this.asiakkaat.remove(p))
            return true;
        else return false;
    }
    
}
