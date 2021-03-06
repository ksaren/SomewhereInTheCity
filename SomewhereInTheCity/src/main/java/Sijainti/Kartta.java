/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sijainti;

import com.google.maps.model.LatLng;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;


/**
 * Luokka joka tallentaa senhetkisen sijainnin mukaisen kartan kuvana. Omistaa
 * myös sijaintitiedot ko. kartan alueelta. Huom! kartta on speksattu
 * pohjoisille leveyksille ja itäisille pituuksille kartan zoom-tasolla 15!
 *
 * @author kaisa
 */
public class Kartta {

    private Image map = null;
    private BufferedImage bufMap = null;
    private GoogleSijainti sijainti;
    private URL URLolio;

    private final String URLalku = "http://maps.google.com/maps/api/staticmap?center=";
    private final String URLloppu = "&zoom=15&size=1024x1024&maptype=roadmap";
    private final String oletusSijainti = "Helsinki,Finland";
    private final double keskeltaReunaan = 0.015;

    private double lansiraja;
    private double itaraja;
    private double pohjoisraja;
    private double etelaraja;

    public Kartta() {
        try {
            this.sijainti = new GoogleSijainti();
            this.paivitaKartta();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodi palauttaa oletussijainnin Helsingin rautatieasemalta, käytetään
     * jos karttaa ei saada päivitettyä.
     *
     * @return Sivuosoite merkkijonona URL-oliota varten.
     */
    public String setURL() {
        return URLalku + oletusSijainti + URLloppu;
    }

    /**
     * Metodi asettaa URL-osoitteen sopivaksi kartanhakua varten Googlen
     * karttapalvelusta.
     *
     * @return Sivuosoite merkkijonona URL-oliota varten.
     */
    public String setURL(String osoite) {
        return URLalku + osoite + URLloppu;
    }

    /**
     * Metodi asettaa URL-osoitteen sopivaksi kartanhakua varten Googlen
     * karttapalvelusta annettujen koordinaattien mukaan.
     *
     * @return Sivuosoite merkkijonona URL-oliota varten.
     */
    public String setURL(LatLng koordinaatit) {
        try {
            return URLalku + koordinaatit.toString() + URLloppu;
        } catch (Exception e) {
            return "Virhe: " + e;
        }
    }

    /**
     * Metodi tallentaa senhetkisen sijainnin rajat. Rajojen perusteella
     * tutkitaan onko Toimija-olio asiakkaan lähellä.
     *
     * @return false jos koordinaattien haku tuottaa poikkeuksen.
     *
     */
    public boolean setRajat() {
        try {
            this.lansiraja = sijainti.getKoordinaatit().lng
                    - keskeltaReunaan;
            this.itaraja = sijainti.getKoordinaatit().lng
                    + keskeltaReunaan;
            this.pohjoisraja = sijainti.getKoordinaatit().lat
                    + keskeltaReunaan;
            this.etelaraja = sijainti.getKoordinaatit().lat
                    - keskeltaReunaan;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public double getLansiraja() {
        return this.lansiraja;
    }

    public double getItaraja() {
        return this.itaraja;
    }

    public double getPohjoisraja() {
        return this.pohjoisraja;
    }

    public double getEtelaraja() {
        return this.etelaraja;
    }

    /**
     * Metodi asettaa kuvan (BufferedImage) rakennetun URL-osoitteen
     * perusteella. Mikäli sijainti paikantuisi automaattisesti, ei alun
     * setSijainti-kutsua tarvittaisi.
     *
     * @return false mikäli karttatietoja ei saada (esim. yhteysvirhe).
     */
    public boolean paivitaKartta() {
        try {
            sijainti.setSijainti();
            URLolio = new URL(this.setURL(sijainti.getKoordinaatit()));
            this.map = ImageIO.read(URLolio);
            this.bufMap = bufferiKuvaksi(map);
            this.setRajat();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Metodi tutkii onko annettu sijainti karttarajojen sisäpuolella.
     *
     * @param koordinaatit
     * @return tosi, jos koordinaatit ovat kartalla.
     */
    public boolean onkoKartalla(LatLng koordinaatit) {
        if (koordinaatit.lat >= this.etelaraja
                && koordinaatit.lat <= this.pohjoisraja
                && koordinaatit.lng <= this.itaraja
                && koordinaatit.lng >= this.lansiraja) {
            return true;
        } else {
            return false;
        }
    }

    public BufferedImage getKartta() {
        return this.bufMap;
    }

    /**
     * Metodi joka muuntaa Image-kuvan BufferedImage:ksi
     *
     * @param Image
     * @return BufferedImage
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

}
