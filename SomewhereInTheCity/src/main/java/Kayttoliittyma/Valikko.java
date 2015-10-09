/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

/**
 * Tarjoaa työkaluja käyttäjän valintojen käsittelyyn ainakin testailuvaiheessa. Ei kehitetä tai testata!
 *
 * @author kaisa
 */
import static Logiikka.Syotteet.*;
import Kayttoliittyma.KirjautumisIkkuna.*;
import static Sijainti.Sijainti.*;
import java.util.*;

public class Valikko {

    private static String valinta;
    private static Scanner sc;
    
   

    public static void paavalikko() {
        sc = new Scanner(System.in);
        System.out.println("Tervetuloa kokeilemaan \"Somewhere in "
                + "the city\" -projektia!");
        System.out.println("Valitse 'A' käyttääksesi ohjelmaa asiakkaana ja "
                + "'Y' katuruokatoimijana: ");
        valinta = (sc.nextLine()).toUpperCase();
        if (testaaSyote(valinta, "Y", "A")) {
            if (valinta.equals("A")) {
                asiakasNakyma();
            } else {
                yrittajaNakyma();
            }

        } else {
            System.out.println("Syöte ei kelpaa. Kokeillaan uudelleen.");
            paavalikko();
        }

    }

    public static void asiakasNakyma() {
        sc = new Scanner(System.in);
        System.out.println("Asiakasnäkymässä voit valita seuraavista "
                + "vaihtoehdoista: ");
        System.out.println("Paikanna minut (P)");
        System.out.println("Etsi katuruokaa (S)");
        System.out.println("Kirjaudu (K)");
        valinta = (sc.nextLine()).toUpperCase();
        switch (valinta) {
            case "P":
                System.out.println("Paikannustoimet...");
                break; //paikanna();  Return sijainti!
            case "S":
                System.out.println("Metodi joka etsii toimijoita...");
                break; //etsiToimijat();
            case "K":
                System.out.println("Kirjautumisolion luonti");
                break; //kirjaudu();
            default:
                System.out.println("Virheellinen valinta, aloita alusta");
                break;
        }

    }

    public static void yrittajaNakyma() {
        System.out.println("Tästä siirrytään yrittäjänäkymään");
    }

    public static void main(String[] args) {
        paavalikko();
    }

}
