/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import java.util.HashSet;
import java.util.Set;

/**Luokka ruokatoimijoiden tallennukseen.
 *
 * @author kaisa
 */
public class Toimija extends Kayttaja {

    private static Toimijat toimijat = new Toimijat();
    private String kuvaus;

    public Toimija(String nimi, String tunnus, String salasana, String uudSalasana) {
        super(nimi, tunnus, salasana, uudSalasana);
        if (toimijat.onkoNimiVapaa(this)) {
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

    public static boolean toimijaListalle(Toimija uusiKarry) {
        if (toimijat.lisaa(uusiKarry)) {
            yksiKayttajaLisaa();
            return true;
        } else {
            return false;
        }
    }

    public static boolean poistaToimija(Toimija poistettava) {
        if (toimijat.poista(poistettava)) {
            System.out.println("Toimija " + poistettava.getNimi() + " poistettu");
            return true;
        } else {
            return false;
        }
    }
    
    public static void main(String[] args) {
        Toimija gp = new Toimija("GrilliPyörä", "gr", "moikkis", "moikkis");
        System.out.println(gp.getNimi());
        System.out.println(gp.getTunnus());
        System.out.println(poistaToimija(gp));
        
    }

}
