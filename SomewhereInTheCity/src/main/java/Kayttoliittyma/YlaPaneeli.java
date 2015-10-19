/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Kayttajat.Asiakas;
import Kayttajat.Toimija;
import Sijainti.Kartta;

import com.google.maps.model.LatLng;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Kehyspaneeli jossa kaksi päällekkäistä Jpanelia; kartta- ja markkeritietoja
 * säilövät KarttaPaneeli a MerkkiPaneeli. Saa kokonsa kartan koosta.
 *
 * @author kaisa
 */
public class YlaPaneeli extends JLayeredPane {

    protected KarttaPaneeli karttapaneeli;
    protected MerkkiPaneeli merkkipaneeli;
    private static AbsoluteLayoutManager layout;

    /**
     * Konstruktori jonka parametrinä Asiakas-olio.**
     */
    public YlaPaneeli(Asiakas a) {
        layout = new AbsoluteLayoutManager();
        this.setLayout(layout);
        karttapaneeli = new KarttaPaneeli();
        this.setPreferredSize(karttapaneeli.getMapSize());
        merkkipaneeli = new MerkkiPaneeli(a, karttapaneeli.getMapSize());
        this.add(karttapaneeli, JLayeredPane.DEFAULT_LAYER);
        this.add(merkkipaneeli, JLayeredPane.PALETTE_LAYER);
        layout.setBounds(karttapaneeli, new Rectangle(karttapaneeli.getPreferredSize()));
        layout.setBounds(merkkipaneeli, new Rectangle(merkkipaneeli.getPreferredSize()));
        repaint();
    }

    /**
     * Konstruktori jonka parametrinä Asiakas-olio.**
     */
    public YlaPaneeli(Toimija t) {
        layout = new AbsoluteLayoutManager();
        this.setLayout(layout);
        karttapaneeli = new KarttaPaneeli();
        this.setPreferredSize(karttapaneeli.getMapSize());
            merkkipaneeli = new MerkkiPaneeli(karttapaneeli.getMapSize());
        this.add(karttapaneeli, JLayeredPane.DEFAULT_LAYER);
        this.add(merkkipaneeli, JLayeredPane.PALETTE_LAYER);
        layout.setBounds(karttapaneeli, new Rectangle(karttapaneeli.getPreferredSize()));
        layout.setBounds(merkkipaneeli, new Rectangle(merkkipaneeli.getPreferredSize()));
        repaint();
    }

    /**
     * Läpinäkyvä paneeli kartan päällä, jossa on käyttäjien sijainteja
     * osoittavat markkerit sekä keskellä omaa sijaintia kuvaava merkki.
     *
     * @author kaisa
     */
    public class MerkkiPaneeli extends JPanel {

        /**
         * ArrayList -kenttä, jonne luodaan ja talletetaan sillä hetkellä
         * kartta-alueella olevat Toimijat SuosikkiLahella -olion muodossa.
         */
        ArrayList<SuosikkiLahella> suosikitLahella;
        private final ImageIcon omaPaikkaIkoni;
        private Dimension kartanKoko;
        private Dimension labelinKoko;
        private JLabel omaLabel;
        private Asiakas asKayttaja;

        public MerkkiPaneeli(Asiakas a, Dimension koko) {
            this.kartanKoko = koko;
            this.asKayttaja = a;
            this.setLayout(null);
            this.setOpaque(false);
            this.setSize(kartanKoko);
            this.suosikitLahella = new ArrayList();

            this.omaPaikkaIkoni = new ImageIcon("tyyppipinni.png");
            this.omaLabel = new JLabel(this.omaPaikkaIkoni);

            int labelY = (int) Math.round(kartanKoko.getHeight() / 2);
            int labelX = (int) Math.round((kartanKoko.getWidth()) / 2);
            this.labelinKoko = omaLabel.getPreferredSize();
            this.omaLabel.setBounds(labelX, labelY, labelinKoko.width, labelinKoko.height);
            this.add(omaLabel);
            this.asetaSuosikitPaneeliin(asKayttaja, karttapaneeli.karttaKuva);

        }

        public MerkkiPaneeli(Dimension koko) {
            this.kartanKoko = koko;
            this.setLayout(null);
            this.setOpaque(false);
            this.setSize(kartanKoko);
            this.suosikitLahella = new ArrayList();
            this.omaPaikkaIkoni = new ImageIcon("tyyppipinni.png");
            this.asetaOmaLabel();

        }

        @Override
        public void paintComponent(Graphics g) {
        }

        /**
         * Metodi joka asettaa merkkipaneelin koon samaksi kuin kartan koko.*
         */
        public void setPreferredSize() {
            super.setPreferredSize(kartanKoko);
        }

        /**
         * Metodi joka asettaa omaa sijaintia kuvaavan markkerin kuvan keskelle.
         */
        public void asetaOmaLabel() {
            int labelY = (int) Math.round(kartanKoko.getHeight() / 2);
            int labelX = (int) Math.round((kartanKoko.getWidth()) / 2);
            this.omaLabel = new JLabel(this.omaPaikkaIkoni);
            this.labelinKoko = omaLabel.getPreferredSize();
            this.omaLabel.setBounds(labelX, labelY, labelinKoko.width, labelinKoko.height);
            this.add(omaLabel);
        }

        /**
         * Metodi joka tutkii onko asiakkaan suosikkitoimijoita kartan alueella
         * ja jos on, asettaa kartalle markkerin vastaavalle paikalle.*
         */
        public boolean asetaSuosikitPaneeliin(Asiakas asiakas, Kartta kartta) {
            LatLng sijainti = new LatLng(0.0, 0.0);
            this.suosikitLahella.clear();
            this.removeAll();
            this.asetaOmaLabel();
            int i = 1;
            for (Toimija lemppari : asKayttaja.getSuosikit()) {
                if (lemppari.getStatus() == true) {
                    sijainti = lemppari.getSijainti().getKoordinaatit();
                    if (kartta.onkoKartalla(sijainti)) {
                        this.suosikitLahella.add(new SuosikkiLahella(i, kartta,
                                sijainti, lemppari));
                        i++;
                    }
                }
            }
            if (!this.suosikitLahella.isEmpty()) {
                JLabel asetettava;
                Dimension koko;
                for (SuosikkiLahella lahiSuosikki : suosikitLahella) {
                    lahiSuosikki.maaritaKuvaan(lahiSuosikki.getMaantieteellinenSijainti());
                    lahiSuosikki.rakennaLabel();
                    asetettava = lahiSuosikki.getSuosikkiLabel();
                    koko = asetettava.getPreferredSize();
                    asetettava.setBounds(lahiSuosikki.getSijaintiKuvassa().x, lahiSuosikki.getSijaintiKuvassa().y, koko.width, koko.height);
                    this.add(asetettava);
                }
                revalidate();
                repaint();
                return true;
            }
            return false;

        }

    }

    /**
     * JPanelin perivä luokka jossa on päivittyvä karttapohja.
     *
     * @author kaisa
     */
    public class KarttaPaneeli extends JPanel {

        protected Kartta karttaKuva;

        public KarttaPaneeli() {
            karttaKuva = new Kartta();
            this.setLayout(new FlowLayout());
            this.setPreferredSize();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(karttaKuva.getKartta(), 0, 0, null);
        }

        public void setPreferredSize() {
            super.setPreferredSize(new Dimension(karttaKuva.getKartta()
                    .getWidth(null), karttaKuva.getKartta().getHeight(null)));
        }

        /**
         * Metodi joka palauttaa karttakuvan koon käytettäväksi päällä olevan
         * paneelin kokona.*
         */
        public Dimension getMapSize() {
            return new Dimension(karttaKuva.getKartta().getWidth(null),
                    karttaKuva.getKartta().getHeight(null));
        }

        /**
         * Metodi päivittää kartan vastaamaan sijaintia ja lisäksi palauttaa
         * päivitetyn kartan viitteen.
         *
         * @return päivitetty Kartta
         */
        public Kartta paivitaJaHaeKartta() {
            karttaKuva.paivitaKartta();
            return karttaKuva;
        }
    }

    /**
     * JLayeredPanen tueksi rakennettu oma layout-manager.
     */
    public static class AbsoluteLayoutManager implements LayoutManager {

        private Map<Component, Rectangle> rajat = new LinkedHashMap<Component, Rectangle>();

        @Override
        public void addLayoutComponent(String nimi, Component comp) {
            rajat.put(comp, new Rectangle(comp.getPreferredSize()));
        }

        @Override
        public void removeLayoutComponent(Component comp) {
            rajat.remove(comp);
        }

        @Override
        public Dimension preferredLayoutSize(Container kehys) {
            Rectangle suorakulmio = new Rectangle();
            for (Rectangle r : rajat.values()) {
                suorakulmio = suorakulmio.union(r);
            }
            return suorakulmio.getSize();
        }

        @Override
        public Dimension minimumLayoutSize(Container kehys) {
            return preferredLayoutSize(kehys);
        }

        @Override
        public void layoutContainer(Container kehys) {
            for (Entry<Component, Rectangle> e : rajat.entrySet()) {
                e.getKey().setBounds(e.getValue());
            }
        }

        public void setBounds(Component c, Rectangle rajat) {
            this.rajat.put(c, rajat);
        }
    }

}
