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

/**
 *
 * @author kaisa
 */
public class GUI extends JFrame implements ActionListener {

    private JButton kirjaudu;
    private JButton uusiKayttaja;
    private Istunto istunto;
    private Kartta karttaKuva;
    private JLabel karttaKuvaLabel;
    private JPanel apupaneeli;  //kirjautumistoimiin
    private JButton apunappi;
    private JLabel aputeksti;
    private JTextField apuTekstikentta;

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

        karttaKuva = new Kartta(); //oletuskartta keskusta-alueelta
        karttaKuvaLabel = new JLabel(new ImageIcon(karttaKuva.getKartta()), 10);

        karttaPaneeli.add(karttaKuvaLabel, "West");

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

    } //konstruktori

    @Override
    public void actionPerformed(ActionEvent e) {    //mitä tehdään...
        if (e.getActionCommand().equals("Kirjaudu")) {
            this.luoApupaneeli(1);
        } else if (e.getActionCommand().equals("Uusi Käyttäjä")) {
            this.luoApupaneeli(2);
        }
        repaint();

    }

    private void luoApupaneeli(int valinta) {
        this.apupaneeli = new JPanel(new FlowLayout());
        apupaneeli.setBackground(Color.DARK_GRAY);
        apunappi = new JButton("OK");
        if (valinta == 1) {
            aputeksti = new JLabel("Käyttäjätunnus:");
            apuTekstikentta = new JTextField(20);
            apupaneeli.add(aputeksti);
            apupaneeli.add(apuTekstikentta);
            this.add(apupaneeli, "East");

            //...ja sitten salasana yms. 
        } else if (valinta==2) {
            aputeksti = new JLabel("Valitse käyttäjätunnus:");
            apuTekstikentta = new JTextField(20);
            apupaneeli.add(aputeksti);
            apupaneeli.add(apuTekstikentta);
            this.add(apupaneeli, "East");

            //...ja sitten salasana yms. 
        }
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
