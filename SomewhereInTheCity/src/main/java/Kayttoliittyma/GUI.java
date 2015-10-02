/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import static Kayttajat.Asiakas.luoMalliAsiakkaat;
import static Kayttajat.Toimija.luoMalliToimijat;
import Logiikka.*;
import Sijainti.Kartta;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

/**
 * Graafisen käyttöliittymän luokka.
 *
 * @author kaisa
 */
public class GUI extends JFrame implements ActionListener {

    private JButton kirjaudu;
    private JButton uusiKayttaja;
    private Kartta karttaKuva;
    private JLabel karttaKuvaLabel;
    private JPanel kirjautumisPaneeli;  //kirjautumistoimiin
    private JButton OKnappi;
    private JLabel kirjAputeksti;
    private JTextField kirjautumisTekstikentta;
    private JButton testiNappi;
    BufferedImage osImage = null;
    Graphics gr = null;

    private boolean kirjautumisenTila = false;

    private KarttaPaneeli kp;

    public GUI() {

        karttaKuva = new Kartta();
        this.setLayout(new BorderLayout());

        ///JPanel karttaPaneeli = new JPanel();
        KarttaPaneeli kp = new KarttaPaneeli();
        kp.setPreferredSize(); //asettaa kartan koon paneelin kooksi

        //karttapaneelin toiminnot
        ///karttaPaneeli.setLayout(new BorderLayout());
        /// karttaPaneeli.setSize(1200, 1200);
        ///karttaPaneeli.setBackground(Color.DARK_GRAY);
        ///karttaPaneeli.add(karttaKuvaLabel, "West");
        ///
        //this.add(karttaPaneeli, "North");
        this.add(kp, "North");

        //tekstipaneelin toiminnot
        JPanel nappiPaneeli = new JPanel(new FlowLayout());
        nappiPaneeli.setBackground(Color.DARK_GRAY);
        uusiKayttaja = new JButton("Uusi käyttäjä");
        kirjaudu = new JButton("Kirjaudu");
        testiNappi = new JButton("Päivitä");
        nappiPaneeli.add(kirjaudu);
        nappiPaneeli.add(uusiKayttaja);
        nappiPaneeli.add(testiNappi, "South");

        this.add(nappiPaneeli, "Center");

        //tapahtumankuuntelijat
        kirjaudu.addActionListener(this);
        uusiKayttaja.addActionListener(this);
        testiNappi.addActionListener(this);
    } //konstruktori

    public class KarttaPaneeli extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(karttaKuva.getKartta(), 0, 0, null);
        }

        ;

        public void setPreferredSize() {
            super.setPreferredSize(new Dimension(karttaKuva.getKartta()
                    .getWidth(null), karttaKuva.getKartta().getHeight(null)));
        }

    ;

    }

    /*private void luoApupaneeli(int valinta) {
        this.kirjautumisPaneeli = new JPanel(new FlowLayout());
        kirjautumisPaneeli.setBackground(Color.DARK_GRAY);
        OKnappi = new JButton("OK");
        if (valinta == 1) {
            kirjAputeksti = new JLabel("Käyttäjätunnus:");
            kirjautumisTekstikentta = new JTextField(20);
        } else if (valinta == 2) {
            kirjAputeksti = new JLabel("Valitse käyttäjätunnus:");
            kirjautumisTekstikentta = new JTextField(20);
        }
        kirjautumisPaneeli.add(kirjAputeksti);
        kirjautumisPaneeli.add(kirjautumisTekstikentta);
        kirjautumisPaneeli.add(OKnappi);
        this.add(kirjautumisPaneeli, "East");

        //...ja sitten salasana yms.
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {   //mitä tehdään...
        switch (e.getActionCommand()) {
            case "Kirjaudu":
                //this.luoApupaneeli(1);
                System.out.println("kirjaudutaan");
                break;
            case "Uusi käyttäjä":
                System.out.println("uusi käyttäjä");
                //this.luoApupaneeli(2);
                break;
            case "Päivitä":
                karttaKuva.paivitaKartta();
                osImage = karttaKuva.getKartta();
                gr = osImage.getGraphics();
                kp.paint(gr);
                break;
        }
        revalidate();
        repaint();

    }

    private static void gui() {
        GUI g = new GUI();
        g.pack();
        g.setTitle("Somewhere in the City");
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        luoMalliAsiakkaat();
        luoMalliToimijat();
        //if (Kirjautuminen.kirjaudu()) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui();
            }
        });
    }
}
//}
   //karttaKuvaLabel.setIcon(new ImageIcon(karttaKuva.getKartta()));

            //case "":
            //System.out.println("ok on");
            // break;