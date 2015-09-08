/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

/**
 * Syötteiden testausvälineitä.
 *
 * @author kaisa
 */
public class Syotteet {

    public static boolean testaaSyote(String testattava,
            String vaihtis1) {
        testattava.toUpperCase();
        if (testattava.equals(vaihtis1)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean testaaSyote(String testattava,
            String vaihtis1, String vaihtis2) {
        testattava.toUpperCase();
        if (testattava.equals(vaihtis1)) {
            return true;
        } else if (testattava.equals(vaihtis2)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean testaaSyote(int testattava,
            int vaihtis1) {
        if (testattava == vaihtis1) {
            return true;
        } else {
            return false;
        }
    }

}
