/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import java.util.HashSet;
import java.util.Set;

/**
 * Yläluokka eri asiakastyypeille.
 *
 * @author kaisa
 */
public class Asiakas implements Kayttaja {

    private int asNro = -1;
    private String kokoNimi;
    private String kayttajatunnus;
    private String salasana;

    private Set<Toimija> suosikit = new HashSet();

    public Asiakas(String nimi) {
        try {
            this.setNimi(nimi);
            //this.asNro =       // <--muuta!
        } catch (Exception e) {
            System.out.println("Nimi ei kelpaa.");
        }
    }

    public Asiakas(String nimi, String tunnus, String salasana, String uudSalasana) {
        try {
            this.setNimi(nimi);
            this.setTunnus(tunnus);
            this.setSalasana(salasana, uudSalasana);
             //this.asNro = 
        } catch (Exception e) {
            System.out.println("Jokin parametreista ei kelpaa.");
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

    public boolean setSuosikki(Toimija lemppari) {
        if (lemppari != null) {
            this.suosikit.add(lemppari);
            return true;
        } else {
            return false;
        }
    }

    public int getNro() {
        return this.asNro;
    }

    public String getNimi() {
        return this.kokoNimi;
    }

    public String getTunnus() {
        return this.kayttajatunnus;
    }
}
