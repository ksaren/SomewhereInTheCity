/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sijainti;

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

    double lansiraja;
    double itaraja;
    double pohjoisraja;
    double etelaraja;
    Image map = null;
    String oletuskartta = "http://maps.google.com/maps/api/staticmap?center=Helsinki,Finland&zoom=15&size=1024x1024&maptype=roadmap";

    public Kartta(String karttasivu) {
        try {
            URL url = new URL(karttasivu);
            map = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Kartta() {
        try{
        URL oletusUrl = new URL(oletuskartta);
        map = ImageIO.read(oletusUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     public boolean onkoKartalla(double lukema) {
        if (lukema >= this.lansiraja && lukema <= this.itaraja) {
            return true;
        } else {
            return false;
        }
    }
     
    public Image tulostaKartta() {
        return this.map;        
    } 
    
    /*URLConnection con = new URL("http://maps...").openConnection();
    InputStream is = con.getInputStream();
    byte bytes[] = new byte[con.getContentLength()];

    is.read (bytes);

    is.close ();
    Toolkit tk = getToolkit();
    map  = tk.createImage(bytes);

    tk.prepareImage (map, 

    -1, -1, null);
*/    
    
    
    //Testataan että kartta toimii...
    public static void main(String[] args) {
        Kartta testikartta = new Kartta("http://maps.google.com/maps/api/staticmap?center=Helsinki,Finland&zoom=15&size=1024x1024&maptype=roadmap");
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        JLabel label = new JLabel(new ImageIcon(testikartta.map));
        frame.add(label);
        frame.setVisible(true);
    
    }

}
