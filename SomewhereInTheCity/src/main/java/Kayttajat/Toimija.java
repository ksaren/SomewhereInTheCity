/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

/**
 * Luokka ruokatoimijoiden tallennukseen. Tarjoaa toimijan omat metodit, kuten toiminnan tilan 
 * (auki/kiinni) päivityksen ja promotekstien asettamisen.
 *
 * @author kaisa
 */
public class Toimija extends Kayttaja {

    private static Toimijat toimijat = new Toimijat();
    private String kuvaus;
    private boolean aukiVaiEi = false;

    public Toimija(String nimi, String tunnus, String salasana, String uudSalasana) {
        super(nimi, tunnus, salasana, uudSalasana);
        if (toimijat.toimijanTiedotOlemassa(this.getTunnus(), this.getNimi()) == null) {
                this.toimijaListalle(this);
                this.setNro(seuraavaKayttaja());
        } else {
            throw new AlreadyDefinedException("Toimija on jo listalla.");
        }
    }

    /** Kuvaustekstin asetusmetodi, joka samalla lisää tekstiin rivinvaihdon 35 merkin välein.
     * 
     * @param kuvausteksti 
     */
    public void setKuvaus(String kuvausteksti) {
         this.kuvaus = kuvausteksti.replaceAll("(.{35})", "$1\n");
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
     * Luokkametodi jolla poistetaan toimija toimijat-listalta.
     *
     * @param poistettava
     * @return tosi, jos toimija on listalla ja poisto onnistuu.
     */
    public static boolean poistaToimija(Toimija poistettava) {
        if (toimijat.poista(poistettava)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Luokkametodi jolla luodaan esimerkkitoimijoita ohjelman alussa.
     * Demo-käyttöön kun olioiden tallennus ei ole käytössä.
     *
     * @return onnistuiko lisäys
     */
    public static boolean luoMalliToimijat() {
        boolean ok = true;
        try {
            Toimija t1 = new Toimija("Suvin Sumppila", "susu", "pannukuuma", "pannukuuma");
            t1.setKuvaus("Suloisia suosikkikahvejasi Suvin kärrystä.");
            t1.setStatus(true);
            Toimija t2 = new Toimija("Testaamo", "tm", "password", "password");
            t2.setKuvaus("Innovatiiviset fuusiokokeilut läheltä.");
            t1.setStatus(true);
            Toimija t3 = new Toimija("Helppo Hodari", "hh", "lallallaa", "lallallaa");
            t3.setKuvaus("Hyviä hodareita lähinakeilla.");
            t3.setStatus(true);
            Toimija t4 = new Toimija("GrilliPyörä", "gr", "moikkis", "moikkis");
            t4.setKuvaus("Klassista kunnon grilliruokaa, vie takuulla nälän!");
            t4.setStatus(true);
            Toimija t5 = new Toimija("Taunon taikku", "taikku", "tairuokaa", "tairuokaa");
            t5.setKuvaus("Taimaalaista Taunon tapaan. Päivän tarjous kanaa!");
            t5.setStatus(true);
            Toimija t6 = new Toimija("Hamppari Oy", "hamppari", "hhhhhh", "hhhhhh");
            t6.setKuvaus("Gourmethampurilaisia parhaista kauden aineksista.");
            Toimija t7 = new Toimija("Kuutosen pitsa", "pitsa6e", "poliisionystava", "poliisionystava");
            t7.setKuvaus("Täytteenä veronkiertoa, halpatyötä ja kurjia aineksia. Kysy upeaa MaRa-versiota tai kuittia!");
            t7.setStatus(true);

        } catch (Exception e) {
            ok = false;
        }
        return ok;
    }

    public String getKuvaus() {
        return this.kuvaus;
    }

    public boolean getStatus() {
        return this.aukiVaiEi;
    }

    public void setStatus(boolean avoinna) {
        this.aukiVaiEi = avoinna;
    }

    @Override
    public String toString() {
        return this.getNimi();
    }

}
