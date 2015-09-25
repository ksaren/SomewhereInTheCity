/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Logiikka.*;
import Sijainti.Kartta;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

/** Graafisen käyttöliittymän luokka.
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

    public GUI() {

        this.setLayout(new BorderLayout());

        JPanel karttaPaneeli = new JPanel() /*{
                 @Override
                 public void paintComponent(Graphics g) {
                 super.paintComponent(g);

                 g.drawImage(
                 karttaKuva.tulostaKartta(), 0, 0, null
                 );
                 }

                 ;
                 }*/;

        //karttapaneelin toiminnot
        karttaPaneeli.setLayout(new BorderLayout());
        karttaPaneeli.setSize(1200, 1200);
        karttaPaneeli.setBackground(Color.DARK_GRAY);

        karttaKuva = new Kartta();
        karttaKuvaLabel = new JLabel(new ImageIcon(karttaKuva.getKartta()), 10);
        testiNappi = new JButton("päivitä");

        karttaPaneeli.add(karttaKuvaLabel, "West");
        karttaPaneeli.add(testiNappi, "South");

        this.add(karttaPaneeli, "North");

        //tekstipaneelin toiminnot
        JPanel nappiPaneeli = new JPanel(new FlowLayout());
        nappiPaneeli.setBackground(Color.DARK_GRAY);
        uusiKayttaja = new JButton("Uusi käyttäjä");
        kirjaudu = new JButton("Kirjaudu");
        nappiPaneeli.add(kirjaudu);
        nappiPaneeli.add(uusiKayttaja);

        this.add(nappiPaneeli, "Center");

        //tapahtumankuuntelijat
        kirjaudu.addActionListener(this);
        uusiKayttaja.addActionListener(this);
        testiNappi.addActionListener(this);

    } //konstruktori

    private void luoApupaneeli(int valinta) {
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
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {    //mitä tehdään...
        switch (e.getActionCommand()) {
            case "Kirjaudu":
                this.luoApupaneeli(1);
                break;
            case "Uusi käyttäjä":
                this.luoApupaneeli(2);
                break;
            case "päivitä":
                karttaKuva.paivitaKartta();
                karttaKuvaLabel.setIcon(new ImageIcon(karttaKuva.getKartta()));
                break;
            case "OK":
                
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
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui();
            }
        });
    }
}
