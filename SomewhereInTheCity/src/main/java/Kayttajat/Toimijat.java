/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import java.io.*;
import java.util.*;

/**
 * Luokka Toimijoiden varastointiin. Tämän luokan toimijat on tarkoitus
 * tallentaa oliotiedostoon.
 *
 * @author kaisa
 */
public class Toimijat implements Serializable {

    private static Set<Toimija> toimijat = new HashSet();

    /**Metodi tutkii onko toimijaa jo tallennettuna, jos on, palauttaa kyseisen Toimijan. Palauttaa 
     * nullin jos käyttäjätunnusta ei löydy.*/
    public static Toimija toimijaTunnusOlemassa(String tunnus) {
        for (Toimija t : toimijat) {
            if (t.getTunnus().equals(tunnus)) {
                return t;
            }
        }
        return null;
    }

     /** Metodi lisää toimijan kokoelmaan. 
     * * Palauttaa false:n jos toimija on jo listalla.**/
    public static boolean lisaa(Toimija t) {
        //pitaiskö heittää oma poikkeus jos ei onnistu?
        if (toimijat.add(t)) {
            return true;
        } else {
            return false;
        }
    }

        /** Metodi poistaa toimijan kokoelmasta. Palauttaa true:n jos toimija löytyy kokoelmasta
     * * ja poisto onnistuu. **/
    public static boolean poista(Toimija p) {
        if (toimijat.remove(p)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static Set<Toimija> kaikkiToimijat() {
        return toimijat;
    }



}
