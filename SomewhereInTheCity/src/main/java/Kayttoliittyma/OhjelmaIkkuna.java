/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Kayttajat.Asiakas;
import static Kayttajat.Asiakas.luoMalliAsiakkaat;
import Kayttajat.Kayttaja;
import Kayttajat.Toimija;
import static Kayttajat.Toimija.luoMalliToimijat;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

/**
 * Graafisen käyttöliittymän luokka.
 *
 * @author kaisa
 */
public class OhjelmaIkkuna extends JFrame implements ActionListener {

    private YlaPaneeli ylapaneeli;
    private JPanel ohjausPaneeli;
    // private KirjautumisIkkuna kirjautumisIkkuna;

    private Kayttaja k;

    private JButton testiNappi;
    BufferedImage osImage = null;
    Graphics gr = null;

    //private boolean kirjautumisenTila = false;
    //private JButton kirjaudu;
    //private JButton uusiKayttaja;
    //private KarttaPaneeli kp;

    public OhjelmaIkkuna() {

        this.setLayout(new BorderLayout());
        this.kaynnistaOhjelma();

    } //konstruktori

    /**
     * Metodi, joka käynnistää varsinaisen ohjelman onnistuneiden
     * kirjautumistoimien jälkeen.*
     */
    public void kaynnistaOhjelma() {
        //k = kirjautumisIkkuna.getKayttaja();
        //kirjautumisIkkuna.setVisible(false);
        k = new Asiakas("Minna Malli", "mima", "mamima", "mamima");
        ylapaneeli = new YlaPaneeli(k);
        this.add(ylapaneeli, "North");
        System.out.println("Ylapaneeli luotu");
        if (k.getClass().equals(Asiakas.class)) {
            ohjausPaneeli = new AsiakasPaneeli();
            System.out.println("asiakaspaneeli luotu");
        } else if (k.getClass().equals(Toimija.class)) {
            ohjausPaneeli = new ToimijaPaneeli();
        } else {
            ohjausPaneeli = new JPanel(new FlowLayout());
            ohjausPaneeli.add(new JLabel("Tietojasi ei löydy. Käynnistä ohjelma"
                    + " uudelleen."));
        }
        ohjausPaneeli.setBackground(Color.BLUE);
        testiNappi = new JButton("Päivitä");
        testiNappi.addActionListener(this);
        ohjausPaneeli.add(testiNappi);
        this.add(ohjausPaneeli, "South");
        revalidate();
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {   //mitä tehdään...
        switch (e.getActionCommand()) {
            case "Päivitä":
                System.out.println("kutsu kuultu");
                ylapaneeli.karttapaneeli.paivitaJaHaeKartta();
                ylapaneeli.merkkipaneeli.suosikitPaneeliin();
            /*case AsiakasPaneeli:
             System.out.println("uusi käyttäjä");
             //this.luoApupaneeli(2);
             break;
             case ToimijaPaneeli:
             karttaKuva.paivitaKartta();
             osImage = karttaKuva.getKartta();
             gr = osImage.getGraphics();
             kp.paint(gr);
             break;
             }*/
        }
        revalidate();
        repaint();

    }

    private static void gui() {
        OhjelmaIkkuna g = new OhjelmaIkkuna();
        g.pack();
        g.setTitle("Somewhere in the City");
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        final KirjautumisIkkuna l = new KirjautumisIkkuna();
        /*WindowStateListener ikkunaVahti = new WindowStateListener() {

         @Override
         public void windowStateChanged(WindowEvent e) {
         if (l.isVisible() == false) {
                    
         } else 
                    
         }
         };*/

        luoMalliAsiakkaat();
        luoMalliToimijat();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui();
                l.kirjaudu();
                Kayttaja k = l.getKayttaja();
                    Asiakas a = (Asiakas)k;
                    a.luoMalliSuosikit();
                System.out.println("if-lauseessa ollaan! Käynnistetään pääohjelma");
                
                
            }
            //  }
        });
    }
}

   //karttaKuvaLabel.setIcon(new ImageIcon(karttaKuva.getKartta()));

