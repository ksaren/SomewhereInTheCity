/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sijainti;

import com.google.maps.model.LatLng;
import java.io.File;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author kaisa
 */
public class Kartta {

    private Image map = null;
    private GoogleSijainti sijainti;
    private URL URLolio;

    //vakioidaan tässä vaiheessa Google-kartan muut tiedot paitsi sijainti
    private final String URLalku = "http://maps.google.com/maps/api/staticmap?center=";
    private final String URLloppu = "&zoom=15&size=1024x1024&maptype=roadmap";
    private final String oletusSijainti = "Helsinki,Finland";
    private final double keskeltaReunaan = 0.015;
    /*Huom! ao. rajat speksattu pohjoisille leveyksille ja itäisille pituuksille kartan 
     zoom-tasolla 15!*/
    private double lansiraja;
    private double itaraja;
    private double pohjoisraja;
    private double etelaraja;

    public Kartta() {
        try {
            this.sijainti = new GoogleSijainti();
            System.out.println("Sijainti luotu.");
            this.paivitaKartta();
        } catch (Exception e) {
            System.out.println("Kartan luonti epäonnistui.");
        }
    }

    public String setURL() {
        return URLalku + oletusSijainti + URLloppu;
    }

    public String setURL(String osoite) {
        return URLalku + osoite + URLloppu;
    }

    public String setURL(LatLng koordinaatit) {
        String Lat, Lon;
        try {
            Lat = Double.toString(koordinaatit.lat);
            Lon = Double.toString(koordinaatit.lng);
            return URLalku + koordinaatit.toString() + URLloppu;
        } catch (Exception e) {
            return "Virhe";
        }
    }

    public boolean setRajat() {
        try {
            this.lansiraja = sijainti.getKoordinaatit().lat
                    + keskeltaReunaan;
            this.itaraja = sijainti.getKoordinaatit().lat
                    - keskeltaReunaan;
            this.pohjoisraja = sijainti.getKoordinaatit().lng
                    + keskeltaReunaan;
            this.etelaraja = sijainti.getKoordinaatit().lng
                    - keskeltaReunaan;
            return true;
        } catch (Exception e) {
            System.out.println("Rajojen luonti ei onnistu");
            return false;
        }
    }

    public boolean paivitaKartta() {//Myöh. parametreilla
        try {
            sijainti.setSijainti(); //testivaiheessa, myöh parametreilla!
            URLolio = new URL(this.setURL(sijainti.getKoordinaatit()));
            this.map = ImageIO.read(URLolio);
            this.setRajat();
            return true;
        } catch (IOException e) {
            System.out.println(e + " Karttaa ei päivitetty.");
            return false;
        }
    }

    public boolean onkoKartalla(LatLng koordinaatit) {
     if (koordinaatit.lat >= this.lansiraja
     && koordinaatit.lat <= this.itaraja
     && koordinaatit.lng <= this.pohjoisraja
     && koordinaatit.lng >= this.etelaraja) {
     return true;
     } else {
     return false;
     }
     }
    public Image getKartta() {
        return this.map;
    }

    //Testataan että kartta toimii...
    public static void main(String[] args) {
        System.out.println("Main:");
        //Kartta testikartta = new Kartta("http://maps.google.com/maps/api/staticmap?center=Helsinki,Finland&zoom=15&size=1024x1024&maptype=roadmap");
        Kartta testikartta2 = new Kartta();
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        JLabel label = new JLabel(new ImageIcon(testikartta2.map));
        frame.add(label);
        frame.setVisible(true);
        System.out.println(testikartta2.paivitaKartta());
        
    }

}
