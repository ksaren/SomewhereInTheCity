/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Kayttajat.Toimija;
import Sijainti.Kartta;
import com.google.maps.model.LatLng;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author kaisa
 */
public class SuosikkiLahella {

    private JLabel suosikkiLabel;
    private int numero;
    /**
     * Kenttä johon tallennetaan piste, johon suosikkiLabel sijoitetaan
     * merkkipaneelissa.
     */
    private Point sijaintiKuvassa;
    /**
     * Kenttä jossa on toimijan maantieteellinen sijainti pituus- ja
     * leveyspiireissä.
     */
    private LatLng maantSijainti;
    /**
     * Kenttä joka toimii viitteenä kyseisellä hetkellä käytössä olevaan
     * karttakuvaan.
     */
    private Kartta karttaNyt;
    private ImageIcon suosikkiIkoni;
    private Toimija suosikkiToimija;
    /**
     * Kenttä joka säilöö vakiota koorditaattien vaihtamiseksi pikseleihin.
     */
    private final double pixKoordRatio;

    public SuosikkiLahella(int nro, Kartta kartta, LatLng sijainti, Toimija lemppari) {
        this.suosikkiToimija = lemppari;
        this.numero = nro;
        this.karttaNyt = kartta;
        this.maantSijainti = new LatLng(sijainti.lat, sijainti.lng);
        this.pixKoordRatio = karttaNyt.getKartta().getWidth()
                / (karttaNyt.getItaraja() - karttaNyt.getLansiraja());
        this.suosikkiIkoni = new ImageIcon("sydanpinni.png");

    }

    /**
     * Metodi joka rakentaa JLabelin kuvaamaan toimijan sijainti. Markkeri
     * koostuu kuvasta ja numeroviitteestä.
     */
    protected void rakennaLabel() {
        suosikkiLabel = new JLabel(Integer.toString(this.numero),
                this.suosikkiIkoni, JLabel.CENTER);
        suosikkiLabel.setOpaque(false);
    }

    /**
     * Metodi joka määrittää markkerin sijainnin merkkipaneelissa.
     *
     * @param koord
     * @return sijaintia kuvaava Point-olio
     */
    protected Point maaritaKuvaan(LatLng koord) {
        this.sijaintiKuvassa = new Point((int) Math.round((koord.lng - karttaNyt.getLansiraja()) * this.pixKoordRatio),
                (int) Math.round((karttaNyt.getPohjoisraja() - koord.lat) * this.pixKoordRatio));
        return this.sijaintiKuvassa;
    }

    public JLabel getSuosikkiLabel() {
        return this.suosikkiLabel;
    }

    public int getNumeroListalla() {
        return this.numero;
    }

    public LatLng getMaantieteellinenSijainti() {
        return this.maantSijainti;
    }

    public Point getSijaintiKuvassa() {
        return this.sijaintiKuvassa;
    }

    public String getKuvaus() {
        return this.suosikkiToimija.getKuvaus();
    }

    public String getNimi() {
        return this.suosikkiToimija.getNimi();
    }

    public int getLabelinLeveys() {
        return this.suosikkiLabel.getWidth();
    }

    public int getLabelinKorkeus() {
        return this.suosikkiLabel.getHeight();
    }

}
