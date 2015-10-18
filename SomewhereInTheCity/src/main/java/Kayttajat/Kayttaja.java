/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import Sijainti.GoogleSijainti;

/**
 * Yläluokka joka tarjoaa kaikille käyttäjille yhteiset toiminnot.
 *
 * @author kaisa
 */
public class Kayttaja {

    private static int seuraavaKayttaja = 1000;
    private int kayttajaNro = -1;
    private String kokoNimi;
    private String kayttajatunnus;
    private String salasana;
    private GoogleSijainti sijainti;

    public Kayttaja(String nimi, String tunnus, String salasana, String uudSalasana) {
        this.sijainti = new GoogleSijainti();
        if (!this.setNimi(nimi)
                || !this.setTunnus(tunnus)
                || !this.setSalasana(salasana, uudSalasana)) {
            throw new IllegalArgumentException("Jokin parametreista ei kelpaa.");
        }

    }

    /**
     * Luokkametodi joka palauttaa seuraavan käyttäjän järjestysnumeron.
     */
    public static int seuraavaKayttaja() {
        return seuraavaKayttaja;
    }

    /**
     * Luokkametodi kasvattaa käyttäjälaskuria yhdellä. Kutsutaan aina
     * onnistuneen käyttäjän luonnin jälkeen.
     */
    public static void yksiKayttajaLisaa() {
        seuraavaKayttaja++;
    }

    /**
     * Metodi joka asettaa annetun merkkijonon käyttäjän nimeksi, jos ei se ole
     * tyhjä.
     */
    public boolean setNimi(String nimi) {
        if (nimi.length() > 0) {
            this.kokoNimi = nimi;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodi joka asettaa annetun merkijonon käyttäjätunnukseksi jos ei se ole
     * tyhjä. Huom. metodi ei testaa onko käyttäjätunnus vapaa.
     */
    public boolean setTunnus(String tunnus) {
        if (tunnus.length() > 0) {
            this.kayttajatunnus = tunnus;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Asettaa salasanan mikäli molemmat parametreinä annetut salasanakentät
     * ovat samat * ja salasana on vähintään 6 merkkiä pitkä.*
     */
    public boolean setSalasana(String salasana, String uudSalasana) {
        if (salasana.equals(uudSalasana) && salasana.length() >= 6) {
            this.salasana = salasana;
            return true;
        } else {
            return false;
        }
    }

    public void setNro(int nro) {
        this.kayttajaNro = nro;
    }

    public int getNro() {
        return this.kayttajaNro;
    }

    public String getNimi() {
        return this.kokoNimi;
    }

    public String getTunnus() {
        return this.kayttajatunnus;
    }

    public String getSalasana() {
        return this.salasana;
    }

    public GoogleSijainti getSijainti() {
        return this.sijainti;
    }

}
