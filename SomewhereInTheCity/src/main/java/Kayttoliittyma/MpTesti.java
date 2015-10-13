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
import Sijainti.Kartta;
import com.google.maps.model.LatLng;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kaisa
 */
public class MpTesti extends JPanel {

        private ArrayList<SuosikkiLahella> suosikitLahella;
        private ImageIcon omaPaikkaIkoni;
        private SuosikkiMerkki suosikkimerkki;
        private Dimension kartanKoko;
        private JLabel omaLabel;
        private Kayttaja kayttaja;
        private Kartta kartta;
        private AbsoluteLayoutManager layout;
        
        public MpTesti(Kayttaja k, Dimension koko) {
            layout = new AbsoluteLayoutManager();
            this.setLayout(layout);
            this.setOpaque(false);
            this.suosikitLahella = new ArrayList();
            this.omaPaikkaIkoni = new ImageIcon("tyyppipinni.png",
            "Sijaintimerkki varustettuna ihmisen kuvalla.");
            this.omaLabel = new JLabel(this.omaPaikkaIkoni);
            //this.suosikkimerkki = new SuosikkiMerkki((Asiakas) k);
            this.kartanKoko = koko;
            this.kayttaja = k;
            this.add(omaLabel);
            omaLabel.setSize(20,20);
            omaLabel.setLocation(512, 512);
            kartta = new Kartta();
            this.asetaSuosikitPaneeliin(kayttaja, kartta);
            layout.setBounds(this, new Rectangle(kartanKoko));
            

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setOpaque(false);//läpinäkyvä
            this.add(omaLabel);
            omaLabel.setSize(20,20);
            omaLabel.setLocation(512, 512);
            this.asetaSuosikitPaneeliin((Asiakas) kayttaja, kartta);
            revalidate();
            repaint();
            //pallo.liikuJaNay(g); //pallo liikkuu ja piirtaa itsensä
        }

        /**
         * Metodi joka asettaa merkkipaneelin koon samaksi kuin kartan koko.*
         */
        public void setPreferredSize() {
            super.setPreferredSize(kartanKoko);
        }

        public boolean suosikitPaneeliin() {
            return true;
        }

        /**
         * Metodi joka tutkii onko asiakkaan suosikkitoimijoita kartan alueella
         * ja jos on, asettaa kartalle markkerin vastaavalle paikalle.*
         */
        private boolean asetaSuosikitPaneeliin(Kayttaja ka, Kartta k) {
            if (ka.getClass().equals(Asiakas.class)) {
            LatLng sijainti = new LatLng(0.0, 0.0);
            Asiakas a = (Asiakas)ka;
            this.suosikitLahella.clear();
            int i = 1;
            for (Toimija lemppari : a.getSuosikit()) {
                sijainti = lemppari.getSijainti().getKoordinaatit();
                if (k.onkoKartalla(sijainti)) {
                    suosikitLahella.add(new SuosikkiLahella(i, sijainti, k));
                    i++;
                }
            }
            if (!suosikitLahella.isEmpty()) {
                //aseta jLabelit paikoilleen paneeliin
                JLabel asetettava = new JLabel("");
                for (SuosikkiLahella lahiSuosikki : suosikitLahella) {
                    asetettava = lahiSuosikki.getSuosikkiLabel();
                    this.add(asetettava);
                    asetettava.setSize(20, 20);
                    asetettava.setLocation(lahiSuosikki.getSijaintiKuvassa().x, lahiSuosikki.getSijaintiKuvassa().y);
                }
                //luo lista numeroiden toimijoista kartan viereen (jos ehtii!)
                return true;
            }
            return false;
            

        } else {
                return false;
        }
        }
        
        public static class AbsoluteLayoutManager implements LayoutManager {

        private Map<Component, Rectangle> rajat = new LinkedHashMap<Component,
                Rectangle>();

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
            for (Map.Entry<Component, Rectangle> e : rajat.entrySet()) {
                e.getKey().setBounds(e.getValue());
            }
        }

        public void setBounds(Component c, Rectangle rajat) {
            this.rajat.put(c, rajat);
        }
    }

        
        public static void main(String[] args) {
            final AbsoluteLayoutManager alm= new AbsoluteLayoutManager();
        luoMalliAsiakkaat();
        luoMalliToimijat();
        Kartta k = new Kartta();
        Asiakas a = new Asiakas("kaisa vaan", "kaisa", "kkkkkk", "kkkkkk");
        a.luoMalliSuosikit();
        MpTesti mpt = new MpTesti(a,new Dimension(k.getKartta().getHeight(), k.getKartta().getWidth()));
        JFrame f = new JFrame();
        alm.setBounds(mpt, new Rectangle(new Dimension(k.getKartta().getHeight(), k.getKartta().getWidth())));
        f.setLayout(null);
        f.setSize(1200, 1200);
        f.add(mpt);
        f.pack();
        alm.setBounds(f, new Rectangle(new Dimension(k.getKartta().getHeight(), k.getKartta().getWidth())));
        f.setVisible(true);
            System.out.println(mpt.suosikitLahella);
    
    }
    }


