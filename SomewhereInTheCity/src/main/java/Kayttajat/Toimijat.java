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

    /**
     * Metodi tutkii onko toimijan nimeä tai käyttäjätunnusta jo tallennettuna.
     *
     * @return käyttäjätunnuksen tai nimen mukaisen Toimijan, tai null-arvon jos
     * käyttäjätunnusta tai nimeä ei löydy.
     */
    public static Toimija toimijanTiedotOlemassa(String tunnus, String nimi) {
        for (Toimija t : toimijat) {
            if (t.getTunnus().equals(tunnus) || t.getNimi().equals(nimi)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Metodi tutkii onko toimijaa jo tallennettuna, jos on, palauttaa kyseisen
     * Toimijan.
     *
     * @return käyttäjätunnuksen mukaisen Toimijan, tai null-arvon jos
     * käyttäjätunnusta ei löydy.
     */
    public static Toimija toimijanTiedotOlemassa(String tunnus) {
        for (Toimija t : toimijat) {
            if (t.getTunnus().equals(tunnus)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Metodi lisää toimijan kokoelmaan.
     *
     * @return false jos toimija on jo listalla eikä lisäys onnistu.
     */
    public static boolean lisaa(Toimija t) {
        if (toimijat.add(t)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodi poistaa toimijan kokoelmasta.
     *
     * @return true jos toimija löytyy kokoelmasta ja poisto onnistuu.
     */
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
