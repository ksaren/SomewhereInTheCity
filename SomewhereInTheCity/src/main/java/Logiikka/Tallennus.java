/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

import Kayttajat.Kayttaja;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author kaisa
 */
public class Tallennus {
    
     public static boolean talleta(Kayttaja talletettava) {
        try {
            FileOutputStream t = new FileOutputStream("st");
            ObjectOutputStream olioT = new ObjectOutputStream(t);
            olioT.writeObject(talletettava);
            olioT.flush();
            t.close();

        } catch (Exception e) {
            System.out.println("Talletusvirhe: " + e);
            return false;
        }
        return true;
    }

    public static boolean tallesta(Kayttaja talletettu) {
        try {
            FileInputStream t = new FileInputStream("st");
            ObjectInputStream olioT = new ObjectInputStream(t);
            talletettu = (Kayttaja) olioT.readObject();
            t.close();
        } catch (Exception e) {
            System.out.println("Palautusvirhe: " + e);
            return false;
        }
        return true;
    }
}
