/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import static Kayttajat.Toimijat.toimijaTunnusOlemassa;
import java.util.HashSet;
import java.util.Set;

/**
 * Määrittelee asiakkaan ja tarjoaa tarvittavat asiakkaan lisäominaisuudet,
 * kuten suosikkien tallentamisen.
 *
 * @author kaisa
 */
public class Asiakas extends Kayttaja {

    private Set<Toimija> suosikit = new HashSet();

    private static Asiakkaat asiakkaat = new Asiakkaat();

    public Asiakas(String nimi, String tunnus, String salasana, String uudSalasana)
            throws AlreadyDefinedException {
        super(nimi, tunnus, salasana, uudSalasana);
        if (asiakkaat.asiakasTunnusOlemassa(this.getTunnus()) == null) {
            try {
                asiakasListalle(this);
                System.out.println("Asiakas lisatty.");//testaus
                this.setNro(seuraavaKayttaja());
            } catch (Exception e) {
                System.out.println("Asiakkaan lisäys ei onnistu.");
            }

        } else {
            throw new AlreadyDefinedException("Asiakas on jo listalla.");
        }

    }
    /** Metodi jolla asiakas voi tallentaa suosikkitoimijoitaan.**/
    public boolean setSuosikki(Toimija lemppari) {
        if (this.suosikit.add(lemppari) && lemppari != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public Set<Toimija> getSuosikit() {
        return this.suosikit;
    }
    
    /** Luokkametodi jolla lisätään asiakas asiakkaat-joukkoon ja kasvattaa käyttäjälaskuria yhdellä.**/
    public static boolean asiakasListalle(Asiakas uusiAsiakas) {
        if (asiakkaat.lisaa(uusiAsiakas)) {
            yksiKayttajaLisaa();
            return true;
        } else {
            return false;
        }
    }
    /** Luokkametodi jolla poistetaan asiakas asiakkaat-joukosta.**/
    public static boolean poistaAsiakas(Asiakas poistettava) {
        if (asiakkaat.poista(poistettava)) {
            System.out.println("Toimija " + poistettava + " poistettu");
            return true;
        } else {
            return false;
        }
    }
    /** Luokkametodi jolla voi ohjelman alussa luoda malliasiakkaita 
     * ohjelman testikäyttöä varten.
     **/
    public static boolean luoMalliAsiakkaat() {
        boolean ok = true;
        try {
            Asiakas a1 = new Asiakas("Mina Vaanikainen", "minavaan", "helppo",
                    "helppo");
            Asiakas a2 = new Asiakas("Teppo Testaaja", "tt", "123456", "123456");
            Asiakas a3 = new Asiakas("Kaisa Kahvikissa", "kk", "JAVACoffee", "JAVACoffee");
        } catch (Exception e) {
            ok = false;
        }
        return ok;
    }
    
    /** Metodi joka lisää asiakkaalle pari suosikkitoimijaa mallitoimijoiden joukosta. 
     *  Toimii jos on ensin luotu malliToimijat.**/
    public boolean luoMalliSuosikit() {
        try {
        this.setSuosikki(toimijaTunnusOlemassa("susu"));
        this.setSuosikki(toimijaTunnusOlemassa("tm"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
