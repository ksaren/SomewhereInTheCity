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
    private boolean aukiVaiEi = false;

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
            t1.setKuvaus("Suloisia suosikkikahvejasi Suvin kärrystä.");
            Toimija t2 = new Toimija("Testaamo", "tm", "password", "password");
            t2.setKuvaus("Innovatiiviset fuusiokokeilut läheltä.");
            Toimija t3 = new Toimija("Helppo Hodari", "hh", "lallallaa", "lallallaa");
            t3.setKuvaus("Hyviä hodareita lähinakeilla.");
            Toimija t4 = new Toimija("GrilliPyörä", "gr", "moikkis", "moikkis");
            t4.setKuvaus("Klassista kunnon grilliruokaa, vie takuulla nälän!");
            Toimija t5 = new Toimija("Taunon taikku", "taikku", "tairuokaa", "tairuokaa");
            t5.setKuvaus("Taimaalaista Taunon tapaan. Päivän tarjous kanaa!");
            Toimija t6 = new Toimija("Hamppari Oy", "hamppari", "hhhhhh", "hhhhhh");
            t6.setKuvaus("Gourmethampurilaisia parhaista kauden aineksista.");
            Toimija t7 = new Toimija("Kuutosen pitsa", "pitsa6e", "poliisionystava", "poliisionystava");
            t7.setKuvaus("Täytteenä veronkiertoa, halpatyötä ja kurjia aineksia. Kysy MaRa-versiota tai kuittia!");
            
        } catch (Exception e) {
            ok = false;
        }
        return ok;
    }
    
    public String getKuvaus() {
        return this.kuvaus;
    }
    
    
    public void setStatus(boolean avoinna) {
        this.aukiVaiEi = avoinna;
    }
    
    @Override
    public String toString() {
        return this.getNimi();
    }


    


}
