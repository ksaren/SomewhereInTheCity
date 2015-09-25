/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

/**
 * Yläluokka joka tarjoaa kaikille käyttäjille yhteiset toiminnot.
 *
 * @author kaisa
 */
public class Kayttaja {

    private static int seuraavaKayttaja = 1000; //alkuarvo, testailuvaiheessa!
    private int kayttajaNro = -1;
    private String kokoNimi;
    private String kayttajatunnus;
    private String salasana;


    public Kayttaja(String nimi, String tunnus, String salasana, String uudSalasana) {
        try {
            this.setNimi(nimi);
            this.setTunnus(tunnus);
            this.setSalasana(salasana, uudSalasana);
        } catch (Exception e) {
            System.out.println("Jokin parametreista ei kelpaa.");
        }

    }
    
    public static int seuraavaKayttaja() {
        return seuraavaKayttaja;
    }
    
    public static void yksiKayttajaLisaa() {
        seuraavaKayttaja++;
    }

    public boolean setNimi(String nimi) {
        if (nimi.length() > 0) {
            this.kokoNimi = nimi;
            return true;
        } else {
            return false;
        }
    }

    public boolean setTunnus(String tunnus) {
        if (tunnus.length() > 0) {
            this.kayttajatunnus = tunnus;
            return true;
        } else {
            return false;
        }
    }

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

}
