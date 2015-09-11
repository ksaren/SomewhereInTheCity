/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

import java.util.*;

/**Luokka johon tallennetaan kantisten tiedot ja heidän suosikkitoimijansa.
 *
 * @author kaisa
 */
public class Kantis extends Asiakas {
    
    private Set<Toimija> suosikit = new HashSet();
    
    public Kantis(String kokoNimi) {
        this.nimi = kokoNimi;
    }
    
    public boolean setNimi(String kokoNimi) {
        if (kokoNimi.length() > 0 ) {
            this.nimi = kokoNimi;
            return true;
        }
        else return false;
    }
    
    public boolean setSuosikki(Toimija lemppari) {
        if (lemppari != null) {
            this.suosikit.add(lemppari);
            return true;
        }
        else return false;
    }
    
  
}
