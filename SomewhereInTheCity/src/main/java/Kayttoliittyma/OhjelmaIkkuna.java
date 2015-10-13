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

    private Kayttaja ohjelmanKayttaja;
    private Asiakas asiakasKayttaja;
    private Toimija toimijaKayttaja;

    private JButton testiNappi;
    BufferedImage osImage = null;
    Graphics gr = null;

    //private boolean kirjautumisenTila = false;
    //private JButton kirjaudu;
    //private JButton uusiKayttaja;
    //private KarttaPaneeli kp;

    public OhjelmaIkkuna(Kayttaja k) {
        this.ohjelmanKayttaja = k;
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.kaynnistaOhjelma();

    } //konstruktori

    /**
     * Metodi, joka käynnistää varsinaisen ohjelman onnistuneiden
     * kirjautumistoimien jälkeen.*
     */
    public void kaynnistaOhjelma() {
        //k = kirjautumisIkkuna.getKayttaja();
        //kirjautumisIkkuna.setVisible(false);
        ylapaneeli = new YlaPaneeli(this.ohjelmanKayttaja);
        this.add(ylapaneeli, "North");
        System.out.println("Ylapaneeli luotu");
        if (this.ohjelmanKayttaja.getClass().equals(Asiakas.class)) {
            this.asiakasKayttaja = (Asiakas)this.ohjelmanKayttaja;
            ohjausPaneeli = new AsiakasPaneeli(asiakasKayttaja);
            System.out.println("asiakaspaneeli luotu");
        } else if (this.ohjelmanKayttaja.getClass().equals(Toimija.class)) {
            this.toimijaKayttaja = (Toimija)this.ohjelmanKayttaja;
            ohjausPaneeli = new ToimijaPaneeli(toimijaKayttaja);
        } else {
            ohjausPaneeli = new JPanel(new FlowLayout());
            ohjausPaneeli.add(new JLabel("Tietojasi ei löydy. Käynnistä ohjelma"
                    + " uudelleen."));
        }
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
                if (asiakasKayttaja != null)
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
   

    static void gui(Kayttaja k) {
        OhjelmaIkkuna g = new OhjelmaIkkuna(k);
        g.pack();
        g.setTitle("Somewhere in the City");
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
    }
    
}
