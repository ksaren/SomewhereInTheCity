/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sijainti;

/**
 * Luokka joka säilyttää senhetkistä sijaintia LatLng-oliokentässä ja
 * mahdollisia API-avaimia Googlen API:en käyttöä varten.
 *
 *
 * @author kaisa
 */
import com.google.maps.*;
import com.google.maps.model.LatLng;
import java.util.HashMap;
import java.util.Random;

public class GoogleSijainti {

    //LatLng säilöö koordinaatit kahdelle globaalille double-kentälle
    private LatLng omaSijainti;

    private boolean paikannettu = false;

    //joitain sattumanvaraisia koordinaatteja Helsingistä testailuun, saa lisäillä!
    private final int taulukonKoko = 30;
    private double[] randomLatitudi = new double[taulukonKoko];
    private double[] randomLongitudi = new double[taulukonKoko];

    /*API-avainta tarvitaan GoogleAPI:en käyttöön, niitä voi luoda 
     googlen Developers-sivuilla*/
    private HashMap<String, String> APIkeys = new HashMap();

    public GoogleSijainti() {
        this.omaSijainti = new LatLng(0, 0);
        this.luoRandomSijainnit();
        this.arvoOmaSijainti();
    }

    /**
     * asettaa sijainnin parametrien mukaan jos parametrit ok, muuten arpoo*
     */
    public GoogleSijainti(double lat, double lng) {
        if (!this.setSijainti(lat, lng)) {
            this.arvoOmaSijainti();
        }
        this.luoRandomSijainnit();
    }

    private void luoRandomSijainnit() {
        this.randomLatitudi[0] = 60.158;
        this.randomLongitudi[0] = 24.926;
        for (int i = 1; i < taulukonKoko; i++) {
            this.randomLatitudi[i] = this.randomLatitudi[i - 1] + 0.001;
            this.randomLongitudi[i] = this.randomLongitudi[i - 1] + 0.001;
        }
    }

    /**
     * yhdistää sattumanvaraisen pituus- ja leveyspiirin ko. randomlistoilta*
     */
    public boolean arvoOmaSijainti() {
        try {
            Random arpoja = new Random();
            this.omaSijainti.lat
                    = randomLatitudi[arpoja.nextInt(randomLatitudi.length)];
            this.omaSijainti.lng
                    = randomLongitudi[arpoja.nextInt(randomLongitudi.length)];
            return true;
        } catch (Exception e) {
            System.out.println("Sijainnin arvonta ei onnistunut");
            return false;
        }
    }

    public void setKey(String nimi, String avain) {
        APIkeys.put(nimi, avain);
    }

    //tällä hetkellä toimiva API-avain
    public void setKey() {
        APIkeys.put("Street1", "AIzaSyDwYZXu-fWF3DRHBQKTRASBIGv0IjGP9KE");
    }

    public boolean setSijainti(double lat, double lng) {
        if (lat <= -180.00 && lat >= 180.00
                && lng <= -180.00 && lng >= 180.00) {
            this.omaSijainti.lat = lat;
            this.omaSijainti.lng = lng;
            paikannettu = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * testivaiheen metodi, arpoo toistaiseksi sijainnin mutta tarkoitus olisi
     * myöhemmin paikantaa oikeasti.*
     */
    public boolean setSijainti() {
        return this.arvoOmaSijainti();
    }

    /**
     * palauttaa sijainnin LatLng-oliona*
     */
    public LatLng getKoordinaatit() {
        return this.omaSijainti;
    }

    //testailuun
    public static void main(String[] args) {
        GoogleSijainti gs = new GoogleSijainti();
        System.out.println(gs.getKoordinaatit());
        for (int i = 0; i < 30; i++) {
            System.out.println(gs.randomLatitudi[10]);
            System.out.println(gs.randomLongitudi[15]);
        }

    }
}
