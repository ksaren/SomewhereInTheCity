/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

/** Yläluokka eri asiakastyypeille.
 *
 * @author kaisa
 */
public class Asiakas implements Kayttaja {
    
    int asNro;
    String nimi;
    
     public int getNro() {
        return this.asNro;
    }
    
    public String getNimi() {
        return this.nimi;
    }
}
