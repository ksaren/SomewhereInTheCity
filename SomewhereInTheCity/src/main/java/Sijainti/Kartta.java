/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sijainti;

import com.google.maps.model.LatLng;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kaisa
 */
public class Kartta {

    private Image map = null;
    private BufferedImage bufMap = null;
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
            sijainti.setSijainti(); //testivaiheessa arpoo, myöh parametreilla!
            URLolio = new URL(this.setURL(sijainti.getKoordinaatit()));
            this.map = ImageIO.read(URLolio);
            this.bufMap = bufferiKuvaksi(map);
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

    public BufferedImage getKartta() {
        return this.bufMap;
    }

    /**
     * Metodi joka muuntaa Image-kuvan BufferedImage:ksi*
     */
    public static BufferedImage bufferiKuvaksi(Image kuva) {
        BufferedImage bKuva = new BufferedImage(kuva.getWidth(null), kuva.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        //Luodaan kuvaa varten 2D-grafiikkaolio
        Graphics2D bGr = bKuva.createGraphics();
        //...ja piirretään siihen kuva
        bGr.drawImage(kuva, 0, 0, null);
        //siivotaan jäljet...
        bGr.dispose();
        return bKuva;
    }
    
        


    //Testataan että kartta toimii...
    public static void main(String[] args) {
        System.out.println("Main:");
        //Kartta testikartta = new Kartta("http://maps.google.com/maps/api/staticmap?center=Helsinki,Finland&zoom=15&size=1024x1024&maptype=roadmap");
        Kartta testikartta2 = new Kartta();
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(1200, 1200);
        JPanel paneeli = new JPanel(new FlowLayout());
        paneeli.setSize(1000,1000);
       //paneeli.paintComponent(testikartta2.map.getGraphics());
        paneeli.setVisible(true);
        frame.add(paneeli);
        frame.setVisible(true);
        frame.repaint();
        frame.revalidate();
        System.out.println(testikartta2.paivitaKartta());
        
        

    }

}
