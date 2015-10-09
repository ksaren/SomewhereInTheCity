/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttajat;

/** Poikkeus jolla käsitellään tilanne kun käyttäjä on jo entuudestaan listalla.
 *
 * @author kaisa
 */
public class AlreadyDefinedException extends RuntimeException {
    
    public AlreadyDefinedException(String viesti) {
        super(viesti);
    }
}
