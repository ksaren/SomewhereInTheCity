/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Kayttajat.Asiakas;
import Kayttajat.Toimija;
import Kayttajat.Toimijat;
import static Kayttajat.Toimijat.kaikkiToimijat;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author kaisa
 */
public class AsiakasPaneeli extends JPanel implements ActionListener {

    private JPanel nappiAlue;
    private JPanel valikkoAlue;
    private JList suosikkiAlue;
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
        this.nimiLabel.setSize(60, 300);

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
        this.suosikkiAlue = new JList();
        this.suosikkiAlue.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.suosikkiAlue.setSize(100, 200);
        this.suosikkiScrollausAlue = new JScrollPane(suosikkiAlue);
        this.suosikkiScrollausAlue.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        this.suosikkiScrollausAlue.setMinimumSize(new Dimension(200, 100));
        
        suosikkiAlue.addListSelectionListener(new ListSelectionListener() {
                
                
    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        if(!e.getValueIsAdjusting()) {
            final Toimija valittu =  (Toimija) suosikkiAlue.getSelectedValue();
            System.out.println(valittu);
        }
    }
        });

            
        
        this.toimijatValikkoon();

    }

    public void toimijatValikkoon() {
        this.suosikkiAlue.setListData(Toimijat.kaikkiToimijat().toArray());
    }

    private ImageIcon pienennaKuva(ImageIcon pienennettava) {
        Image img = pienennettava.getImage();
        Image newimg = img.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "suosikki":
                System.out.println(aktiivi.getSuosikit());
                for (Toimija t : kaikkiToimijat()) {
                    if (suosikkiAlue.getSelectedValue().equals(t)) {
                        aktiivi.setSuosikki(t);
                        System.out.println(aktiivi.getSuosikit());

                    }
                    System.out.println(t);
                }
                break;
                
        }
    }

}
