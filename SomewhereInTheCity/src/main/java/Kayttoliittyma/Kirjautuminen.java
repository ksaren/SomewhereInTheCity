/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Kayttajat.Asiakas;
import static Kayttajat.Asiakas.*;
import static Kayttajat.Asiakkaat.*;
import Kayttajat.Kayttaja;
import Kayttajat.Toimija;
import static Kayttajat.Toimija.*;
import static Kayttajat.Toimijat.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Luokka joka sisältää graafisen kirjautumisikkunan. Onnistunut kirjautuminen on edellytys 
 * varsinaisen ohjelmaikkunan avaamiselle.
 *
 * @author kaisa
 */
public class Kirjautuminen extends JFrame implements ActionListener {
    
    private JPanel kirjautumisPaneeli;
    private JButton OKnappi;
    private JLabel kirjAputeksti;
    private JTextField kirjautumisTekstikentta;
    private JButton alanappi;
    private JButton lopeta;

    //Tapahtumankäsittelyssä tarvittavia muuttujia
    Kayttaja k;
    String kTunnus;
    String nimi;
    String salasana;
    String uudSalasana;
    boolean ruokatoimija;
    boolean tunnistusOK = false;
    
    public Kirjautuminen() {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.GRAY);
        
        this.OKnappi = new JButton("Kirjaudu");
        this.kirjAputeksti = new JLabel("Käyttäjätunnus:");
        this.kirjautumisTekstikentta = new JTextField(20);
        this.alanappi = new JButton("Uusi käyttäjä?");
        this.lopeta = new JButton("Lopeta");
        
        OKnappi.addActionListener(this);
        alanappi.addActionListener(this);
        lopeta.addActionListener(this);
        
        this.add(kirjAputeksti, "North");
        this.add(kirjautumisTekstikentta, "Center");
        this.add(OKnappi, "East");
        this.add(alanappi, "South");
        this.add(lopeta, "West");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Lopeta":
                dispose();
            case "Kirjaudu":
                alanappi.setVisible(false);
                kTunnus = kirjautumisTekstikentta.getText();
                k = toimijaTunnusOlemassa(kTunnus);
                System.out.println("käyttäjäolio luotu");
                if (k == null) {
                    k = asiakasTunnusOlemassa(kTunnus);
                }
                if (k == null) {
                    this.kirjoita("Kayttäjätunnusta " + kTunnus
                            + " ei löydy. Yritä uudelleen tai luo uusi tunnus.");
                } else {
                    this.kirjoita("Salasana:");
                    this.muutaNappi("Valmis");
                }
                break;
            
            case "Valmis":
                salasana = kirjautumisTekstikentta.getText();
                if (!salasana.equals(k.getSalasana())) {
                    this.kirjoita("Salasana ei täsmää. Yritä uudelleen:");
                    break;
                } else {
                    this.kirjoita("Käyttäjä tunnistettu!");
                    this.muutaNappi("Käynnistä ohjelma");
                    lopeta.setVisible(false);
                    kirjautumisTekstikentta.setVisible(false);
                    break;
                }
            case "Käynnistä ohjelma":
                tunnistusOK = true;
                //suljetaan kirjautumisikkuna (ja kutsutaan GUIta main-ohjelmassa)
                this.setVisible(false);
                break;
            
            case "Uusi käyttäjä?":
                alanappi.setVisible(false);
                kTunnus = kirjautumisTekstikentta.getText();
                //Jos käyttäjä painoi samantien nappia:
                if (kTunnus.equals("")) {
                    this.kirjoita("Syötä haluamasi käyttäjätunnus:");
                }
                this.muutaNappi("Tunnus OK");
                break;
            case "Tunnus OK":
                kTunnus = kirjautumisTekstikentta.getText();
                this.kirjoita("Oletko ruokatoimija(R) vai asiakas(A)?");
                this.muutaNappi("Jatka");
                break;
            case "Jatka":
                if (kirjautumisTekstikentta.getText().equalsIgnoreCase("R")) {
                    this.kirjoita("Luodaan toiminnallesi käyttäjätili. Yrityksen (markkinointi)nimi: ");
                    ruokatoimija = true;
                } else {
                    this.kirjoita("Luodaan sinulle käyttäjätili. Nimesi: ");
                    ruokatoimija = false;
                }
                this.muutaNappi("Nimi OK");
                break;
            case "Nimi OK":
                nimi = kirjautumisTekstikentta.getText();
                this.kirjoita("Salasana (vah. 6 merkkiä):");
                this.muutaNappi("Salasana OK");
                break;
            case "Salasana OK":
                salasana = kirjautumisTekstikentta.getText();
                this.kirjoita("Salasana uudelleen:");
                this.muutaNappi("Valmista");
                break;
            case "Valmista":
                uudSalasana = kirjautumisTekstikentta.getText();
                try {
                    if (ruokatoimija) {
                        k = new Toimija(nimi, kTunnus, salasana, uudSalasana);
                    } else {
                        k = new Asiakas(nimi, kTunnus, salasana, uudSalasana);
                    }
                } catch (Exception ex) {
                    //ok = false;
                    this.kirjoita("Tilin luonti ei onnistunut. Joko salasanat eivät täsmää, tai nimi, "
                            + "käyttäjätunnus tai salasana on jo käytössä.");
                    this.muutaNappi("Alkuun");
                    break;
                }
                this.kirjoita("Tunnukset luotu. Kirjaudu nyt sisään.");
                this.muutaNappi("Kirjaudu");
                
                break;
        }
    }

    /**
     * Yksityinen metodi, joka vaihtaa kirjautumisen aputekstin ja tyhjentää
     * valmiiksi tekstikentän *
     */
    private void kirjoita(String teksti) {
        this.kirjautumisTekstikentta.setText("");
        this.kirjAputeksti.setText(teksti);
        repaint();
    }

    /**
     * Yksityinen metodi, joka vaihtaa OK-napin tekstin. *
     */
    private void muutaNappi(String teksti) {
        this.OKnappi.setText(teksti);
        repaint();
    }
    
    /**
     * "gui"-metodi, joka rakentaa kirjautumisikkunan. Tarkoitus, että kertoo paluuarvolla 
     * kirjautumisen onnistumisen - ominaisuus ei toimi vielä
     * @return boolean , false jos kirjautumistoimet epäonnistuivat
     */
    protected static boolean kirjaudu() {
        Kirjautuminen k = new Kirjautuminen();
        k.pack();
        k.setTitle("Somewhere in the City - kirjautuminen");
        k.setLocation(200, 200);
        k.setVisible(true);
        return k.tunnistusOK;
    }
    
    public static void main(String[] args) {
        luoMalliAsiakkaat();
        luoMalliToimijat();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                kirjaudu();
            }
        });
    }
}
