/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sijainti;

import com.google.api.services.coordinate.Coordinate; //ei vielä tarvita
import com.google.api.services.coordinate.Coordinate.Location; //ei vielä tarvita

/**
 *
 * @author kaisa
 */
public class Sijainti {

    private double pituuspiiri;
    private double leveyspiiri;
    private Kartta kartta;
    
    //private Location lokaatio;
    //private Coordinate koordinaatti;

    public Sijainti() {
        this.setLon(new LatLonConvert(-24, -56, -15).getDecimal());
        this.setLat(new LatLonConvert(60, 10, 15).getDecimal());
    }
    
    //Sijainti helposti kirjoitettavassa muodossa
     //public Sijainti(String pituuspiiri, String leveyspiiri) {
        //sijainti.setLon(new LatLonConvert());
        //sijainti.setLat(new LatLonConvert());
    //}
    
    public boolean setLon(double longitudi) {
         if (kartta.onkoKartalla(longitudi)) {
                this.pituuspiiri = longitudi;
                return true;
         }
         else return false;
    }
    
    public boolean setLat(double latitudi) {
         if (kartta.onkoKartalla(latitudi)) {
                this.pituuspiiri = latitudi;
                return true;
         }
         else return false;
    }
    
    
}
