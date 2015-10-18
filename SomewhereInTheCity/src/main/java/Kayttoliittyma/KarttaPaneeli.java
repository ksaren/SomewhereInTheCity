/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Sijainti.Kartta;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author kaisa
 */
public class KarttaPaneeli extends JPanel {

    protected Kartta karttaKuva;

    public KarttaPaneeli() {
        karttaKuva = new Kartta();
        this.setLayout(new FlowLayout());
        this.setPreferredSize();
    }

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(karttaKuva.getKartta(), 0, 0, null);
    }

    /**  Metodi asettaa komponentin koon karttakuvan koon kanssa samaksi.
     * 
     */
    public void setPreferredSize() {
        super.setPreferredSize(new Dimension(karttaKuva.getKartta()
                .getWidth(null), karttaKuva.getKartta().getHeight(null)));
    }
    
    /** Metodi joka palauttaa karttakuvan koon käytettäväksi päällä olevan paneelin kokona.**/
    public Dimension getMapSize() {
        return new Dimension(karttaKuva.getKartta().getWidth(null),
                karttaKuva.getKartta().getHeight(null));
    }
}
