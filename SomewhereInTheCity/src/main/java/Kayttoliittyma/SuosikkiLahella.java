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
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author kaisa
 */
public class SuosikkiLahella {

    private JLabel suosikkiLabel;
    private int numero;
    private Point sijaintiKuvassa;
    private LatLng maantSijainti;
    private Kartta karttaNyt;
    private ImageIcon suosikkiIkoni;
    private Toimija suosikkiToimija;
    private final double pixKoordRatio;
    
 
        
    

    public SuosikkiLahella(int nro, Kartta kartta, LatLng sijainti, Toimija lemppari) {
        this.suosikkiToimija = lemppari;
        this.numero = nro;
        this.karttaNyt = kartta;
        this.maantSijainti = new LatLng(sijainti.lat, sijainti.lng);
        this.pixKoordRatio = karttaNyt.getKartta().getWidth()/(karttaNyt.getItaraja()-karttaNyt.getLansiraja())
                ;
        this.suosikkiIkoni = new ImageIcon("sydanpinni.png",
            "Sijaintimerkki varustettuna sydämellä.");
    }
    
    protected void rakennaLabel() {
        suosikkiLabel = new JLabel(Integer.toString(this.numero),
                        this.suosikkiIkoni, JLabel.CENTER);
        suosikkiLabel.setOpaque(false);
    }

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
