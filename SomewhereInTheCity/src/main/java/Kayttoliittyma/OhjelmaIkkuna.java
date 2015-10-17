/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Kayttajat.Asiakas;
import Kayttajat.Kayttaja;
import Kayttajat.Toimija;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.border.Border;

/**
 * Graafisen käyttöliittymän luokka.
 *
 * @author kaisa
 */
public class OhjelmaIkkuna extends JFrame implements ActionListener {

    private YlaPaneeli ylapaneeli;
    private JPanel ohjausPaneeli;
    private JPanel paivitysPaneeli;
    private InfoPaneeli infoPaneeli;

    private Kayttaja ohjelmanKayttaja;
    private Asiakas asiakasKayttaja;
    private Toimija toimijaKayttaja;

    private JButton paivitysNappi;
    BufferedImage osImage = null;
    Graphics gr = null;

    public OhjelmaIkkuna(Kayttaja k) {
        this.ohjelmanKayttaja = k;
        this.setLayout(new BorderLayout());
        //this.setResizable(false);
        this.kaynnistaOhjelma();

    } //konstruktori

    /**
     * Metodi, joka käynnistää varsinaisen ohjelman onnistuneiden
     * kirjautumistoimien jälkeen. Metodi tutkii onko kirjautunut käyttäjä asiakas vai toimija ja 
     * rakentaa käyttöliittymän sen mukaan.*
     */
    public void kaynnistaOhjelma() {
        if (this.ohjelmanKayttaja.getClass().equals(Asiakas.class)) {
            this.asiakasKayttaja = (Asiakas) this.ohjelmanKayttaja;
            ylapaneeli = new YlaPaneeli(this.asiakasKayttaja);
            ohjausPaneeli = new AsiakasPaneeli(asiakasKayttaja);
            infoPaneeli = new InfoPaneeli();
            this.add(ylapaneeli, "West");
            this.add(infoPaneeli, "East");
            System.out.println("asiakaspaneeli luotu");
        } else if (this.ohjelmanKayttaja.getClass().equals(Toimija.class)) {
            this.toimijaKayttaja = (Toimija) this.ohjelmanKayttaja;
            ylapaneeli = new YlaPaneeli(this.toimijaKayttaja);
            ohjausPaneeli = new ToimijaPaneeli(toimijaKayttaja);
            this.add(ylapaneeli, "North");
        } else {
            ohjausPaneeli = new JPanel(new FlowLayout());
            ohjausPaneeli.add(new JLabel("Tietojasi ei löydy. Käynnistä ohjelma"
                    + " uudelleen."));
        }
        paivitysNappi = new JButton("Päivitä");
        paivitysPaneeli = new JPanel(new FlowLayout());
        paivitysPaneeli.add(paivitysNappi);
        paivitysNappi.addActionListener(this);
        ohjausPaneeli.add(paivitysPaneeli);
        this.add(ohjausPaneeli, "South");
        revalidate();
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Päivitä":
                ;
                if (asiakasKayttaja != null) {
                    System.out.println("kutsu kuultu");
                    ylapaneeli.merkkipaneeli.asetaSuosikitPaneeliin(this.asiakasKayttaja,
                            ylapaneeli.karttapaneeli.paivitaJaHaeKartta());
                    infoPaneeli.paivitaInfo();
                } else if (toimijaKayttaja != null) {
                    ylapaneeli.karttapaneeli.paivitaJaHaeKartta();
                }
                break;

        }
        revalidate();
        repaint();

    }

    /**
     * JPanelin perivä luokka jonne tulostuu kaikkien alueella olevien
     * suosikkiToimijoiden nimet ja kuvaukset.
     *
     * @author kaisa
     */
    public class InfoPaneeli extends JPanel {

        private JTextArea tekstialue;
        private JScrollPane scrollAlue;
        private Border infonReuna;

        public InfoPaneeli() {
            this.setLayout(new BorderLayout());
            infonReuna = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
            tekstialue = new JTextArea("Suosikkisi alueella:");
            scrollAlue = new JScrollPane(tekstialue);
            tekstialue.setEditable(false);
            scrollAlue.setBounds(new Rectangle(new Dimension(50, ylapaneeli.getHeight())));
            scrollAlue.setBorder(infonReuna);
            this.add(scrollAlue, "Center");
            this.paivitaInfo();

        }

        /**
         * Metodi joka käy merkkipaneelin suosikitLähellä-listalta läpi kaikki
         * SuosikkiLahella-oliot ja lisää ne infopaneelin tekstialueelle
         * kuvauksineen ja numeroineen.
         */
        public void paivitaInfo() {
            System.out.println("päivitä info:");
            tekstialue.setText("Suosikkisi alueella:\n");
            for (SuosikkiLahella lahiKiska : ylapaneeli.merkkipaneeli.suosikitLahella) {
                System.out.println("lähellä: " + lahiKiska.getNimi()); //test
                tekstialue.append(lahiKiska.getNumeroListalla() + ". : ");
                tekstialue.append(lahiKiska.getNimi() + "\n");
                tekstialue.append(lahiKiska.getKuvaus() + "\n\n");
                repaint();

            }
        }

    }

    public static void gui(Kayttaja k) {
        OhjelmaIkkuna g = new OhjelmaIkkuna(k);
        g.pack();
        g.setTitle("Somewhere in the City");
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
