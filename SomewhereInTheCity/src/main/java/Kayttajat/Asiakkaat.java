/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import java.io.*;
import java.util.*;

/**
 * Luokka Asiakkaiden varastointiin. Tämän luokan asiakkaat on tarkoitus
 * tallentaa oliotiedostoon.
 *
 * @author kaisa
 */
public class Asiakkaat implements Serializable {

    private static Set<Asiakas> asiakkaat = new HashSet();

    /**
     * Luokkametodi tutkii onko asiakasta jo tallennettuna, jos on, palauttaa
     * kyseisen Asiakkaan. Palauttaa nullin jos käyttäjätunnusta ei löydy.*
     */
    public static Asiakas asiakasTunnusOlemassa(String tunnus) {
        for (Asiakas a : asiakkaat) {
            if (a.getTunnus().equals(tunnus)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Metodi lisää asiakkaan kokoelmaan.
     *
     * @return false jos asiakas on jo listalla.
     */
    public boolean lisaa(Asiakas a) {
        if (asiakkaat.add(a)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodi poistaa asiakkaan kokoelmasta.
     *
     * @return toden jos asiakas löytyy kokoelmasta ja poisto onnistuu.
     */
    public boolean poista(Asiakas p) {
        if (this.asiakkaat.remove(p)) {
            return true;
        } else {
            return false;
        }
    }

}
