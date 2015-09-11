/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

/** Satunnaisasiakkaiden luokka, käyttäjätietoja ei tallenneta.
 *
 * @author kaisa
 */


public class SatunnaisAsiakas extends Asiakas {
    
    public SatunnaisAsiakas() {
        nimi = "anonyymi käyttäjä";
        asNro = 2000 + (int)(Math.random()*1000);
    }
    
    
    
    
}
