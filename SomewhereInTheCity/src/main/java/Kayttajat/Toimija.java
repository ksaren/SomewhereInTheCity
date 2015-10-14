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
 * Luokka ruokatoimijoiden tallennukseen.
 *
 * @author kaisa
 */
public class Toimija extends Kayttaja {

    private static Toimijat toimijat = new Toimijat();
    private String kuvaus;
    private boolean avoinna = false;

    public Toimija(String nimi, String tunnus, String salasana, String uudSalasana) {
        super(nimi, tunnus, salasana, uudSalasana);
        if (toimijat.toimijaTunnusOlemassa(this.getTunnus()) == null) {
            try {
                this.toimijaListalle(this);
                System.out.println("Toimija lisatty.");//testaus
                this.setNro(seuraavaKayttaja());
            } catch (Exception e) {
                System.out.println("Toimijan lisäys ei onnistu.");
            }

        } else {
            //voisko tässä lähettää jonkun virheilmon ja vaatia uuden nimen..?
            this.setNimi("Uusi Toimija");
            System.out.println("Nimi on jo listalla.");
        }

    }

    public void setKuvaus(String kuvausteksti) {
        this.kuvaus = kuvausteksti;
    }

    /**
     * Luokkametodi jolla lisätään toimija toimijat-kokoelmaan.*
     */
    public static boolean toimijaListalle(Toimija uusiKarry) {
        if (toimijat.lisaa(uusiKarry)) {
            yksiKayttajaLisaa();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Luokkametodi jolla poistetaan toimija toimijat-listalta.*
     * @param poistettava
     */
    public static boolean poistaToimija(Toimija poistettava) {
        if (toimijat.poista(poistettava)) {
            System.out.println("Toimija " + poistettava.getNimi() + " poistettu");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Luokkametodi jolla luodaan esimerkkitoimijoita ohjelman alussa. Demo-käyttöön.
     * 
     * @return 
     */
    public static boolean luoMalliToimijat() {
        boolean ok = true;
        try {
            Toimija t1 = new Toimija("Suvin Sumppila", "susu", "pannukuuma", "pannukuuma");
            Toimija t2 = new Toimija("Testaamo", "tm", "password", "password");
            Toimija t3 = new Toimija("Helppo Hodari", "hh", "lallallaa", "lallallaa");
            Toimija t4 = new Toimija("GrilliPyörä", "gr", "moikkis", "moikkis");
        } catch (Exception e) {
            ok = false;
        }
        return ok;
    }
    
    @Override
    public String toString() {
        return this.getNimi();
    }

    public static void main(String[] args) {
        luoMalliToimijat();
        System.out.println(toimijaTunnusOlemassa("susu"));

    }

    public String getKuvaus() {
        return this.kuvaus;
    }

}
