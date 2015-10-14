/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Kayttajat.Toimija;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author kaisa
 */
public class ToimijaPaneeli extends JPanel implements ActionListener {
    
    private Toimija yrittajaKayttaja;
    private JButton asetaTeksti;
    private JButton statusNappi;
    private JLabel ohjeTeksti;
    private JLabel nimiLabel;
    private JTextArea kuvausTeksti;
    private JPanel tekstiPaneeli;
    private JPanel nappiPaneeli;
    private Border kehys;

public ToimijaPaneeli(Toimija t) {
    this.setLayout(new BorderLayout());
    this.yrittajaKayttaja = t;
    this.tekstiPaneeli = new JPanel(new FlowLayout());
    this.nappiPaneeli = new JPanel(new BorderLayout());
    this.nimiLabel = new JLabel("Ohjelmaa käyttää: \n" +t.getNimi());
    this.asetaTeksti = new JButton("Aseta");
    this.statusNappi = new JButton("AVAA MYYNTI");
    this.ohjeTeksti = new JLabel("Mitä haluat kertoa asiakkaillesi?\n (lyhyt kuvaus)");
    this.kuvausTeksti = new JTextArea(5,40);
    this.kehys = BorderFactory.createLineBorder(Color.DARK_GRAY, 5);
    kuvausTeksti.setBorder(kehys);
    nappiPaneeli.add(asetaTeksti, "North");
    nappiPaneeli.add(statusNappi, "South");
    tekstiPaneeli.add(ohjeTeksti);
    tekstiPaneeli.add(kuvausTeksti);
    tekstiPaneeli.add(nappiPaneeli);
    
    this.add(nimiLabel, "West");
    this.add(tekstiPaneeli, "South");
    asetaTeksti.addActionListener(this);
    
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Aseta")) {
            yrittajaKayttaja.setKuvaus(kuvausTeksti.getText());
            kuvausTeksti.setText("");
        } else if (e.getActionCommand().equals(("AVAA MYYNTI"))) {
            yrittajaKayttaja.setStatus(true);
            statusNappi.setText("LOPETA MYYNTI");
        } else if (e.getActionCommand().equals("LOPETA MYYNTI")) {
            yrittajaKayttaja.setStatus(false);
            statusNappi.setText("AVAA MYYNTI");
        }
    }

}