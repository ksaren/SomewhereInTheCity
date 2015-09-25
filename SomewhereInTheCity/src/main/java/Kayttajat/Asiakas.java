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
public class Asiakas extends Kayttaja {

    private Set<Toimija> suosikit = new HashSet();

    private static Asiakkaat asiakkaat = new Asiakkaat();

    public Asiakas(String nimi, String tunnus, String salasana, String uudSalasana) {
        super(nimi, tunnus, salasana, uudSalasana);

        if (asiakkaat.onkoNimiVapaa(this)) {
            try {
                this.asiakasListalle(this);
                System.out.println("Asiakas lisatty.");//testaus
                this.setNro(seuraavaKayttaja());
            } catch (Exception e) {
                System.out.println("Asiakkaan lisäys ei onnistu.");
            }

        } else {
            //voisko tässä lähettää jonkun virheilmon ja vaatia uuden nimen..?
            this.setNimi("Uusi Asiakas");
            System.out.println("Nimi on jo listalla.");
        }

    }
    
    public boolean setSuosikki(Toimija lemppari) {
        if (this.suosikit.add(lemppari) && lemppari!=null)
            return true;
        else return false;
    }

    public static boolean asiakasListalle(Asiakas uusiAsiakas) {
        if (asiakkaat.lisaa(uusiAsiakas)) {
            yksiKayttajaLisaa();
            return true;
        } else {
            return false;
        }
    }

    public static boolean poistaAsiakas(Asiakas poistettava) {
        if (asiakkaat.poista(poistettava)) {
            System.out.println("Toimija " + poistettava + " poistettu");
            return true;
        } else {
            return false;
        }
    }

}
