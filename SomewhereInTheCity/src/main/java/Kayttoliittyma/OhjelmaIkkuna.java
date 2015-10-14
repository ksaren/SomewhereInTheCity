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
    private InfoPaneeli infoPaneeli;

    private Kayttaja ohjelmanKayttaja;
    private Asiakas asiakasKayttaja;
    private Toimija toimijaKayttaja;

    private JButton testiNappi;
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
     * kirjautumistoimien jälkeen.*
     */
    public void kaynnistaOhjelma() {
        ylapaneeli = new YlaPaneeli(this.ohjelmanKayttaja);
        this.add(ylapaneeli, "North");
        
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
        infoPaneeli = new InfoPaneeli();
        infoPaneeli.setPreferredSize(new Dimension(200, ylapaneeli.getHeight()));
        this.add(infoPaneeli, "East");
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
                ylapaneeli.merkkipaneeli.asetaSuosikitPaneeliin(this.asiakasKayttaja,ylapaneeli.karttapaneeli.paivitaJaHaeKartta());
                infoPaneeli.paivitaInfo();
                } else if (toimijaKayttaja != null) {
                    ylapaneeli.karttapaneeli.paivitaJaHaeKartta();
                }
                break;
          
      
        }
        revalidate();
        repaint();

    }
    
    /** JPanelin perivä luokka jonne tulostuu kaikkien alueella olevien suosikkiToimijoiden nimet ja 
 * kuvaukset.
 *
 * @author kaisa
 */
public class InfoPaneeli extends JPanel{
    
    private JTextArea tekstialue;
    private JScrollPane scrollAlue;
    
    public InfoPaneeli() {
        tekstialue = new JTextArea("Suosikkisi alueella:");
        tekstialue.setSize(200, 1000);
        scrollAlue = new JScrollPane(tekstialue);
        tekstialue.setEditable(false);
        this.add(scrollAlue);
        
    }
    
    public void paivitaInfo() {
        tekstialue.setText("Suosikkisi alueella:\n");
        for (SuosikkiLahella lahiKiska : ylapaneeli.merkkipaneeli.suosikitLahella) {
            //tekstialue.append
            System.out.println(lahiKiska.getNumeroListalla() + ". : ");
            tekstialue.append(lahiKiska.getNimi() +"\n");
            tekstialue.append(lahiKiska.getKuvaus() + "\n\n");
            
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
