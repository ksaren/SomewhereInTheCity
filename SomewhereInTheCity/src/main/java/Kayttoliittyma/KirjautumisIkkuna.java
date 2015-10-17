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

/**
 * Luokka joka sisältää graafisen kirjautumisikkunan. Onnistunut kirjautuminen
 * on edellytys varsinaisen ohjelmaikkunan avaamiselle.
 *
 * @author kaisa
 */
public class KirjautumisIkkuna extends JFrame implements ActionListener {

    //Käyttöliittymäkomponentteja
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

    public KirjautumisIkkuna() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.GRAY);
        this.kirjautumisPaneeli = new JPanel(new FlowLayout());
        this.kirjautumisPaneeli.setPreferredSize(null);
        this.OKnappi = new JButton("Kirjaudu");
        this.kirjAputeksti = new JLabel("Käyttäjätunnus:");
        this.kirjautumisTekstikentta = new JTextField(20);
        this.alanappi = new JButton("Uusi käyttäjä?");
        this.lopeta = new JButton("Lopeta");

        OKnappi.addActionListener(this);
        alanappi.addActionListener(this);
        lopeta.addActionListener(this);

        kirjautumisPaneeli.add(kirjAputeksti);
        kirjautumisPaneeli.add(kirjautumisTekstikentta);
        kirjautumisPaneeli.add(OKnappi);
        kirjautumisPaneeli.add(alanappi);
        kirjautumisPaneeli.add(lopeta);

        this.add(kirjautumisPaneeli, "Center");
        this.setMinimumSize(new Dimension(500, 100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "Lopeta":
                dispose();
                break;

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
                    this.alanappi.setVisible(true);
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
                OhjelmaIkkuna.gui(this.k);
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
                if (salasana.length() >= 6) {
                    this.kirjoita("Salasana uudelleen:");
                    this.muutaNappi("Valmista");
                } else {
                    this.kirjoita("Liian lyhyt salasana. Yritä uudelleen:");
                }
                break;
            case "Valmista":
                uudSalasana = kirjautumisTekstikentta.getText();
                if (uudSalasana.length() < 6 || !salasana.equals(uudSalasana)) {
                    this.kirjoita("Liian lyhyt salasana tai salasanat eivät täsmää. Yritä uudelleen:");
                    this.muutaNappi("Salasana OK");
                } else {
                    try {
                        if (ruokatoimija) {
                            k = new Toimija(nimi, kTunnus, salasana, uudSalasana);
                        } else {
                            k = new Asiakas(nimi, kTunnus, salasana, uudSalasana);
                        }
                    } catch (Exception ex) {
                        this.kirjoita("Tilin luonti ei onnistunut. Joko salasanat eivät täsmää, tai nimi, "
                                + "käyttäjätunnus tai salasana on jo käytössä.");
                        this.muutaNappi("Alkuun");
                        break;
                    }
                    this.kirjoita("Tunnukset luotu. Kirjaudu nyt sisään.");
                    this.muutaNappi("Kirjaudu");
                }

                break;
            case "Alkuun":
                alanappi.setVisible(true);
                this.kirjoita("Käyttäjätunnus:");
                this.muutaNappi("Kirjaudu");
                break;
        }
        revalidate();
        repaint();
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

    public Kayttaja getKayttaja() {
        return this.k;
    }

    /**
     * "gui"-metodi, joka rakentaa kirjautumisikkunan.
     */
    protected void kirjaudu() {
        this.pack();
        this.setTitle("Somewhere in the City - kirjautuminen");
        this.setLocation(200, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void virheIlmo(String virhe) {
        JOptionPane.showMessageDialog(this, virhe);
    }

    public static void main(String[] args) {
        luoMalliAsiakkaat();
        luoMalliToimijat();
        final KirjautumisIkkuna kI = new KirjautumisIkkuna();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                kI.kirjaudu();
            }
        });
    }
}
/*WindowStateListener ikkunaVahti = new WindowStateListener() {

 @Override
 public void windowStateChanged(WindowEvent e) {
 if (l.isVisible() == false) {
                    
 } else 
                    
 }
 };*/
