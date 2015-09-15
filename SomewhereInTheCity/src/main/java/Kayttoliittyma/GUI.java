/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Logiikka.Istunto;
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
    private JButton paikanna;
    private JTextField tekstikentta;
    private Istunto istunto;
    private Kartta karttaKuva;
    private JLabel karttaKuvaLabel;

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
        karttaPaneeli.setBackground(Color.gray);
        kirjaudu = new JButton("Kirjaudu");
        kirjaudu.setForeground(Color.DARK_GRAY);
        karttaKuva = new Kartta();
        karttaKuvaLabel = new JLabel(new ImageIcon(karttaKuva.tulostaKartta()), 10);

        karttaPaneeli.add(kirjaudu, "East");
        karttaPaneeli.add(karttaKuvaLabel, "West");

        this.add(karttaPaneeli, "North");

        //tekstipaneelin toiminnot
        JPanel tekstiPaneeli = new JPanel(new FlowLayout());
        tekstiPaneeli.setBackground(Color.DARK_GRAY);
        paikanna = new JButton("Paikanna");
        tekstikentta = new JTextField(30);

        tekstiPaneeli.add(tekstikentta);
        tekstiPaneeli.add(paikanna);

        this.add(tekstiPaneeli, "South");

        /*JPanel laskuriB = new JPanel(new FlowLayout());

         laskuriB.setName (

         "LASKURI B");
         laskuriB.setBackground (Color.CYAN);
         lukemaB  = new JTextField(Integer.toString(B.lukema()), 5);
         nollaaB  = new JButton("NOLLAA LASKURI B");
         lisaaB  = new JButton("LISÄÄ LASKURIIN B");

         laskuriB.add (lukemaB);

         laskuriB.add (nollaaB);

         laskuriB.add (lisaaB);
             

         this.add(laskuriB, 

         "East");*/
        /* lisaaA.addActionListener(e
         -> {
         A.lisaa();
         lukemaA.setText(Integer.toString(A.lukema()));
         }
         );

         lisaaB.addActionListener(e
         -> {
         B.lisaa();
         lukemaB.setText(Integer.toString(B.lukema()));

         }
         );

         nollaaA.addActionListener(e
         -> {
         A.nollaa();
         lukemaA.setText(Integer.toString(A.lukema()));

         }
         );*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
