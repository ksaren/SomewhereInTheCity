/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import java.awt.Image;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author kaisa
 */
public class AsiakasPaneeli extends JPanel {

    private JButton lisaaSuosikki;
    private JScrollPane suosikkivalikko;
    private ImageIcon tahtimerkki;

    public AsiakasPaneeli() {
        this.tahtimerkki = new ImageIcon("star.png", "tähti");
        this.tahtimerkki = this.pienennaKuva(tahtimerkki);
        this.lisaaSuosikki = new JButton(tahtimerkki);
        this.add(lisaaSuosikki);
    }

    private ImageIcon pienennaKuva(ImageIcon pienennettava) {
        Image img = pienennettava.getImage();
        Image newimg = img.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
}
