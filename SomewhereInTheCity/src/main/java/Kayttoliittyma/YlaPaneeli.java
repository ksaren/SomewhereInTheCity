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
        karttapaneeli = new KarttaPaneeli();
        this.setPreferredSize(karttapaneeli.getMapSize());
        merkkipaneeli = new MerkkiPaneeli(k, karttapaneeli.getMapSize());
       //karttapaneeli.setPreferredSize(); //asettaa kartan koon paneelin kooksi
        //merkkipaneeli.setPreferredSize(); //
        this.add(karttapaneeli, JLayeredPane.DEFAULT_LAYER);
        this.add(merkkipaneeli, JLayeredPane.PALETTE_LAYER);
       layout.setBounds(karttapaneeli, new Rectangle(karttapaneeli.getPreferredSize()));
       layout.setBounds(merkkipaneeli, new Rectangle(merkkipaneeli.getPreferredSize()));
        repaint();
    }

    
    /**
     * Läpinäkyvä paneeli kartan päällä, jossa on käyttäjien sijainteja
     * osoittavat markkerit.
     *
     * @author kaisa
     */
    public class MerkkiPaneeli extends JPanel {

        /** ArrayList -kenttä, jonne luodaan ja talletetaan sillä hetkellä kartta-alueella olevat Toimijat
         * SuosikkiLahella -olion muodossa.
         */
        ArrayList<SuosikkiLahella> suosikitLahella;
        private final ImageIcon omaPaikkaIkoni;
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
            this.kartanKoko = koko;
            this.kayttaja = k;
            this.add(omaLabel);
            omaLabel.setSize(20, 20);
            omaLabel.setLocation(512, 512);
            

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setOpaque(false);//läpinäkyvä
            this.add(omaLabel);
          this.asetaSuosikitPaneeliin((Asiakas) kayttaja, karttapaneeli.karttaKuva);
            revalidate();
            repaint();
            
        }

        /**
         * Metodi joka asettaa merkkipaneelin koon samaksi kuin kartan koko.*
         */
        public void setPreferredSize() {
            super.setPreferredSize(kartanKoko);
        }

        

        /**
         * Metodi joka tutkii onko asiakkaan suosikkitoimijoita kartan alueella
         * ja jos on, asettaa kartalle markkerin vastaavalle paikalle.*
         */
       public boolean asetaSuosikitPaneeliin(Kayttaja kayttaja, Kartta kartta) {
            if (kayttaja.getClass().equals(Asiakas.class)) {
            LatLng sijainti = new LatLng(0.0, 0.0);
            Asiakas a = (Asiakas)kayttaja;
            this.suosikitLahella.clear();
            int i = 1;
            for (Toimija lemppari : a.getSuosikit()) {
                sijainti = lemppari.getSijainti().getKoordinaatit();
                if (kartta.onkoKartalla(sijainti)) {
                    suosikitLahella.add(new SuosikkiLahella(i, sijainti, kartta, lemppari));
                    i++;
                }
            }
            if (!suosikitLahella.isEmpty()) {
                //aseta jLabelit paikoilleen paneeliin
                JLabel asetettava = new JLabel("");
                System.out.println("suosikitLahella ei oo tyhjä");
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
    

/* Idea tähän on saatu SOF:sta, JLayeredPane ja manuaalinen layout-manager ratkaisuna siihen, että 
muuten JLayeredPanen JPanelit eivät näy.*/

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
            for (Entry<Component, Rectangle> e : rajat.entrySet()) {
                e.getKey().setBounds(e.getValue());
            }
        }

        public void setBounds(Component c, Rectangle rajat) {
            this.rajat.put(c, rajat);
        }
    }

    //malli JLayeredPanelle:
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


public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    Asiakas alli = new Asiakas("Alli Malli", "alma", "mallijuu", "mallijuu");
    Kartta kr = new Kartta();
    luoMalliAsiakkaat();
    luoMalliToimijat();
    alli.luoMalliSuosikit();
    Dimension koko = new Dimension(kr.getKartta().getHeight(null),kr.getKartta().getWidth(null));
    JPanel jp = new  JPanel();
    jp.add(new JLabel(new ImageIcon(kr.getKartta())));
    jp.setPreferredSize(koko);
    ImageIcon omaPaikkaIkoni = new ImageIcon("tyyppipinni.png",
            "Sijaintimerkki varustettuna ihmisen kuvalla.");
    JLabel omaLabel = new JLabel(omaPaikkaIkoni);
    JPanel omaPaneeli = new JPanel();
    omaPaneeli.add(omaLabel);
    omaPaneeli.setOpaque(false);
    JFrame f = new JFrame();
    JLayeredPane jlp = new JLayeredPane();
    AbsoluteLayoutManager layout = new AbsoluteLayoutManager();
    jlp.setLayout(layout);
    jlp.add(jp, JLayeredPane.DEFAULT_LAYER);
    jlp.add(omaPaneeli, JLayeredPane.PALETTE_LAYER);
    f.setPreferredSize(koko);
    f.add(jlp);
    f.pack();
    f.setVisible(true);
    f.revalidate();
    f.repaint();
    

}
}
