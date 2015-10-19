/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Kayttajat.Toimija;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

/** Luokka joka vastaa yrityskäyttäjän tarvitsemista hallintavälineistä paneelissa. Mahdollistaa 
 * toimijan tilan muuttamisen ja promotekstin asettamisen.
 *
 * @author kaisa
 */
public class ToimijaPaneeli extends JPanel implements ActionListener {
    
    private Toimija yrittajaKayttaja;
    private JButton asetaTeksti;
    private JButton statusNappi;
    private JLabel ohjeTeksti;
    private JLabel nimiLabel;
    private JLabel kayttaaLabel;
    private JLabel statusLabel;
    private JTextArea kuvausTeksti;
    private JPanel tekstiPaneeli;
    private JPanel nappiPaneeli;
    private JPanel nimiPaneeli;
    private Border kehys;
    private String tila;
    
    public ToimijaPaneeli(Toimija t) {
        this.setLayout(new BorderLayout());
        this.yrittajaKayttaja = t;
        this.tila = "SULJETTU";
        this.tekstiPaneeli = new JPanel(new BorderLayout());
        this.nappiPaneeli = new JPanel(new BorderLayout());
        this.nimiPaneeli = new JPanel(new BorderLayout());
        this.kayttaaLabel = new JLabel("Ohjelmaa käyttää:");
        this.nimiLabel = new JLabel(t.getNimi());
        this.statusLabel = new JLabel(this.tila);
        this.asetaTeksti = new JButton("Aseta");
        this.statusNappi = new JButton("AVAA MYYNTI");
        this.ohjeTeksti = new JLabel("Mitä haluat kertoa asiakkaillesi?\n (lyhyt kuvaus)");
        this.kuvausTeksti = new JTextArea(5, 40);
        this.kehys = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        kuvausTeksti.setBorder(kehys);
        nimiPaneeli.add(kayttaaLabel, "North");
        nimiPaneeli.add(nimiLabel, "Center");
        nimiPaneeli.add(statusLabel, "South");
        nappiPaneeli.add(asetaTeksti, "South");
        nappiPaneeli.add(statusNappi, "North");
        tekstiPaneeli.add(ohjeTeksti, "North");
        tekstiPaneeli.add(kuvausTeksti, "South");
        this.add(nappiPaneeli, "East");
        this.add(nimiPaneeli, "West");
        this.add(tekstiPaneeli, "South");
        asetaTeksti.addActionListener(this);
        statusNappi.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Aseta")) {
            yrittajaKayttaja.setKuvaus(kuvausTeksti.getText());
            kuvausTeksti.setText("");
        } else if (e.getActionCommand().equals(("AVAA MYYNTI"))) {
            yrittajaKayttaja.setStatus(true);
            this.tila = "AVOINNA";
            statusNappi.setText("LOPETA MYYNTI");
            statusLabel.setText(this.tila);
        } else if (e.getActionCommand().equals("LOPETA MYYNTI")) {
            yrittajaKayttaja.setStatus(false);
            this.tila = "SULJETTU";
            statusNappi.setText("AVAA MYYNTI");
            statusLabel.setText(this.tila);
        }
        revalidate();
        repaint();
    }
    
   
   
}
