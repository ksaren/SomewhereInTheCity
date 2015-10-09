/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

/**<div>Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from
 * <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a>          
 * is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons
 * BY 3.0">CC BY 3.0</a></div>
 *
 * <div>Icons made by <a href="http://www.flaticon.com/authors/simpleicon" title="SimpleIcon"
 * >SimpleIcon</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a>
 * is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons 
 * BY 3.0">CC BY 3.0</a></div>
 * @author kaisa
 */

import Kayttajat.Kayttaja;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Merkki {
    
    private Point merkinPaikka; // Point sisältää int-muotoiset x- ja y-koordinaatit
    private Kayttaja merkitys;

    public Merkki(Kayttaja k, Dimension kuvanKoko) {
        merkinPaikka = new Point();
        this.merkinPaikka.setLocation(kuvanKoko.getWidth()/2, 
            kuvanKoko.getHeight()/2);
        this.merkitys = k;      
    }

    public Merkki (Kayttaja k){
        this.merkitys = k;
    }
    public Merkki(Kayttaja k, Point paikka) {
        this.merkinPaikka = paikka;
        this.merkitys = k;      
    }
    
    public Point getPaikka() {
        return this.merkinPaikka;
    }
    
    public Kayttaja getPaikanKayttaja() {
        return this.merkitys;
    }
    
    public boolean setPaikka(Point uusiPaikka) {
        this.merkinPaikka = uusiPaikka;
        return true;
    }
    
    public boolean setPaikanKayttaja(Kayttaja uusiKayttaja) {
        this.merkitys = uusiKayttaja;
        return true;
    }

    /*public void asetaMerkki(Graphics g) {

        this.merkinPaikka += this.xSuunta;
        this.y += this.ySuunta;
        g.
        g.fillOval(x, y, 20, 20);
    }*/
    

    
}
