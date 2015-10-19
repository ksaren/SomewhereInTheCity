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
import javax.swing.*;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

/**Käyttöliittymän asiakkaan toimintoja ohjaava paneeli. Mahdollistaa suosikkitoimijoiden hallinnan.
 *
 * @author kaisa
 */
public class AsiakasPaneeli extends JPanel implements ActionListener {

    private JPanel nappiAlue;
    private JPanel valikkoAlue;
    private DefaultListModel listamalli;
    private JList graafLista;
    private JScrollPane suosikkiScrollausAlue;
    private JButton lisaaSuosikki;
    private JTextArea suosikkini;
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

        this.suosikkini = new JTextArea("Suosikkini: \n");
        this.nimiLabel = new JLabel("Käyttäjä:\n" + aktiivi.getNimi());
        this.nimiLabel.setSize(60, 300);

        this.nappiAlue = new JPanel(new BorderLayout());
        nappiAlue.add(nimiLabel, "North");
        nappiAlue.add(suosikkini, "South");

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
        this.listamalli = new DefaultListModel();
        this.graafLista = new JList(listamalli);
        this.graafLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.graafLista.setSize(100, 200);
        this.suosikkiScrollausAlue = new JScrollPane(graafLista);
        this.suosikkiScrollausAlue.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        this.suosikkiScrollausAlue.setSize(new Dimension(200, 100));
        this.toimijatValikkoon();

    }

    /** Metodi joka vie kaikki ruokatoimijat toimija-valikkoon. Valikossa toimijoiden viitteenä 
     * toimii niiden nimi.
     */
    public void toimijatValikkoon() {
        this.graafLista.setListData(Toimijat.kaikkiToimijat().toArray());
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
                for (Toimija t : kaikkiToimijat()) {
                    if (graafLista.getSelectedValue().equals(t)) {
                        if (aktiivi.setSuosikki(t)) {
                            this.suosikkini.append(t.getNimi() + "\n"); 
                        }
                    }
                }
                break;

        }
    }

}
