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

    private static int uusinToimija = 1000; //alkuarvo, testailuvaiheessa!
    private static Set<Toimija> toimijat = new HashSet(); //siirretään Toimijat-luokkaan

    private String karrynNimi;
    private String kayttajatunnus;
    private int karryNro;
    private String kuvaus;

    //Luo uuden Toimijan, antaa nimeksi uusiToimija jos toimijan nimi on jo listalla.
    //Lisää myös toimijan listalle 'toimijat'.
    public Toimija(String toimijanNimi) {
        this.karrynNimi = toimijanNimi;
        for (Toimija t : toimijat) {
            if (t.karrynNimi.equalsIgnoreCase(this.karrynNimi)) {
                this.karrynNimi = "uusiToimija";
            }
        }
            if (toimijaListalle(this)) {
                System.out.println("Toimija lisatty.");
                uusinToimija++;
                this.karryNro = uusinToimija;
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

    public static boolean poistaToimija(Toimija poistettava) {
        if (toimijat.remove(poistettava)) {
            System.out.println("Toimija " + poistettava + " poistettu");
            return true;
        } else {
            return false;
        }
    }

    public Toimija() {
    }

    public String getNimi() {
        return this.karrynNimi;
    }

    public int getNro() {
        return this.karryNro;
    }
    
    public String getTunnus() {
        return this.kayttajatunnus;
    }

    
}
