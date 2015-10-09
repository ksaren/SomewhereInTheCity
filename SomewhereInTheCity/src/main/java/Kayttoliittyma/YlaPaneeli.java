/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Kayttajat.Asiakas;
import Kayttajat.Kayttaja;
import Kayttajat.Toimija;
import Sijainti.Kartta;

import static Kayttajat.Asiakas.luoMalliAsiakkaat;
import static Kayttajat.Asiakkaat.asiakasTunnusOlemassa;
import static Kayttajat.Toimija.luoMalliToimijat;
import static Kayttajat.Toimijat.toimijaTunnusOlemassa;

import com.google.maps.model.LatLng;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
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
    
    public YlaPaneeli() {
    }

    public YlaPaneeli(Kayttaja k) {
        layout = new AbsoluteLayoutManager();
        this.setLayout(layout);
        System.out.println("rakennetaan ylapaneeli...");
        karttapaneeli = new KarttaPaneeli();
        //this.setPreferredSize(karttapaneeli.getMapSize());
        System.out.println("karttapaneeli ok");
        merkkipaneeli = new MerkkiPaneeli(k, karttapaneeli.getMapSize());
        System.out.println("merkkipaneeli ok");
       // karttapaneeli.setPreferredSize(); //asettaa kartan koon paneelin kooksi
        //merkkipaneeli.setPreferredSize(); //
        System.out.println("kokojen määritys ok");
        //this.setComponentZOrder(karttapaneeli, 0);
        //this.setComponentZOrder(merkkipaneeli, 1);
        System.out.println("järjestys ok");
        this.add(karttapaneeli, JLayeredPane.DEFAULT_LAYER);
        this.add(merkkipaneeli, JLayeredPane.PALETTE_LAYER);
        layout.setBounds(karttapaneeli, new Rectangle(karttapaneeli.getPreferredSize()));
        layout.setBounds(merkkipaneeli, new Rectangle(merkkipaneeli.getPreferredSize()));
        System.out.println("ylapaneeli valmis");
        repaint();
    }

    
    /**
     * Läpinäkyvä paneeli kartan päällä, jossa on käyttäjien sijainteja
     * osoittavat markkerit.
     *
     * @author kaisa
     */
    public class MerkkiPaneeli extends JPanel {

        private ArrayList<SuosikkiLahella> suosikitLahella;
        private final ImageIcon omaPaikkaIkoni;
        private SuosikkiMerkki suosikkimerkki;
        private Dimension kartanKoko;
        private JLabel omaLabel;
        private Kayttaja kayttaja;

        public MerkkiPaneeli(Kayttaja k, Dimension koko) {
            this.setLayout(null);
            this.setOpaque(false);
            this.suosikitLahella = new ArrayList();
            this.omaPaikkaIkoni = new ImageIcon("tyyppipinni.png",
            "Sijaintimerkki varustettuna ihmisen kuvalla.");
            this.omaLabel = new JLabel(this.omaPaikkaIkoni);
            //this.suosikkimerkki = new SuosikkiMerkki((Asiakas) k);
            this.kartanKoko = koko;
            this.kayttaja = k;
            this.add(omaLabel);
            omaLabel.setLocation(512, 512);
            

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setOpaque(false);//läpinäkyvä
            this.asetaSuosikitPaneeliin((Asiakas) kayttaja, karttapaneeli.karttaKuva);
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


        /*public Merkki getSuosikkiMerkki(Toimija t) {
         return ;
         }*/
    }



/**JPanelin perivä luokka jossa on päivittyvä karttapohja.
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
    
    /** Metodi joka palauttaa karttakuvan koon käytettäväksi päällä olevan paneelin kokona.**/
    public Dimension getMapSize() {
        return new Dimension(karttaKuva.getKartta().getWidth(null),
                karttaKuva.getKartta().getHeight(null));
    }
    
    public Kartta paivitaJaHaeKartta() {
        karttaKuva.paivitaKartta();
        return karttaKuva;
    }
}

/* Idea tähän on saatu SOF:sta, JLayeredPane manuaalinen layout-manager ratkaisuna siihen, että 
muuten JLayeredPanen JPanelit näkyvät.*/

public static class AbsoluteLayoutManager implements LayoutManager {

        private Map<Component, Rectangle> bounds = new LinkedHashMap<Component,
                Rectangle>();

        @Override
        public void addLayoutComponent(String name, Component comp) {
            bounds.put(comp, new Rectangle(comp.getPreferredSize()));
        }

        @Override
        public void removeLayoutComponent(Component comp) {
            bounds.remove(comp);
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            Rectangle rect = new Rectangle();
            for (Rectangle r : bounds.values()) {
                rect = rect.union(r);
            }
            return rect.getSize();
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return preferredLayoutSize(parent);
        }

        @Override
        public void layoutContainer(Container parent) {
            for (Entry<Component, Rectangle> e : bounds.entrySet()) {
                e.getKey().setBounds(e.getValue());
            }
        }

        public void setBounds(Component c, Rectangle bounds) {
            this.bounds.put(c, bounds);
        }
    }

    /*protected void initUI() {
        JFrame frame = new JFrame("test");
        AbsoluteLayoutManager layout = new AbsoluteLayoutManager();
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(layout);
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel.setBackground(Color.gray);
        panel.setVisible(true);
        layeredPane.add(panel);
        layout.setBounds(panel, new Rectangle(17, 59, 436, 480));
        frame.add(layeredPane);
        frame.pack();
        frame.setVisible(true);
    }*/

    /*public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new YlaPaneeli().initUI();
            }
        });
    }*/


/*public static void main(String[] args) {
    Asiakas alli = new Asiakas("Alli Malli", "alma", "mallijuu", "mallijuu");
    Kartta kr = new Kartta();
    luoMalliAsiakkaat();
    luoMalliToimijat();
    alli.luoMalliSuosikit();
    Dimension koko = new Dimension(kr.getKartta().getHeight(null),kr.getKartta().getWidth(null));
    YlaPaneeli mp;
    mp = new YlaPaneeli(alli);
    JPanel jlp = new JPanel();
    jlp.setOpaque(false);
    JPanel jp = new  JPanel();
    jp.add(new JLabel(new ImageIcon(kr.getKartta())));
    jlp.add(jp);
    jlp.setPreferredSize(koko);
    JFrame f = new JFrame();
    f.setPreferredSize(koko);
    f.add(jlp);
    f.pack();
    f.setVisible(true);
    f.revalidate();
    f.repaint();
    
}*/
}
