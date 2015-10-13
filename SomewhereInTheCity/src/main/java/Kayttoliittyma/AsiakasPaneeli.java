/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Kayttajat.Asiakas;
import Kayttajat.Toimija;
import Kayttajat.Toimijat;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author kaisa
 */
public class AsiakasPaneeli extends JPanel implements ActionListener {

    private JPanel nappiAlue;
    private JPanel valikkoAlue;
    private JTextArea suosikkiAlue;
    private JScrollPane suosikkiScrollausAlue;
    private JButton lisaaSuosikki;
    private JButton paivita;
    private ImageIcon tahtimerkki;
    private JLabel nimiLabel;
    private JLabel valikonOtsikko;
    private Asiakas aktiivi;

    public AsiakasPaneeli(Asiakas a) {
        
        this.aktiivi = a;
         
        this.setLayout(new BorderLayout());
        this.rakennaToimijaValikko();
        
        this.tahtimerkki = new ImageIcon("star.png", "tähti");
        this.tahtimerkki = this.pienennaKuva(tahtimerkki);
        this.lisaaSuosikki = new JButton(tahtimerkki);
       this.valikonOtsikko = new JLabel("Kaikki yritykset:");
        
        this.nimiLabel = new JLabel("Käyttäjä:\n" + aktiivi.getNimi());
        this.nimiLabel.setSize(60,300);
        
        this.nappiAlue = new JPanel(new FlowLayout());
        nappiAlue.add(nimiLabel);
        
        this.valikkoAlue = new JPanel(new BorderLayout());
        valikkoAlue.add(suosikkiScrollausAlue, "South");
        valikkoAlue.add(lisaaSuosikki, "East");
        valikkoAlue.add(valikonOtsikko, "West");
        lisaaSuosikki.addActionListener(this);
        lisaaSuosikki.setActionCommand("suosikki");
        
        this.add(nappiAlue, "East");
        this.add(valikkoAlue, "West");
        
    }

    private void rakennaToimijaValikko() {
        this.suosikkiAlue = new JTextArea();
        this.suosikkiAlue.setSize(100,200);
        this.suosikkiScrollausAlue = new JScrollPane(suosikkiAlue);
        this.suosikkiScrollausAlue.setMinimumSize(new Dimension(200, 100));
        suosikkiAlue.setLineWrap(true);

        DefaultCaret caret = (DefaultCaret) suosikkiAlue.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.toimijatValikkoon();

    }

    public void toimijatValikkoon() {
        this.suosikkiAlue.setText("");
        for (Toimija t : Toimijat.kaikkiToimijat()) {
            this.suosikkiAlue.append(t.getNimi()+"\n");
        }
    }
    
    private ImageIcon pienennaKuva(ImageIcon pienennettava) {
        Image img = pienennettava.getImage();
        Image newimg = img.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
   
    }
}
