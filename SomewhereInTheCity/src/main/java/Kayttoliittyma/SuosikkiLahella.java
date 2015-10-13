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
    private Point sijaintiKuvassa;
    private Kartta karttaNyt;
    private ImageIcon suosikkiIkoni;
    
 
        
    

    public SuosikkiLahella(int nro, LatLng sij, Kartta k) {
        this.numero = nro;
        this.karttaNyt = k;
        this.sijaintiKuvassa = this.maaritaKuvaan(sij);
        this.suosikkiIkoni = new ImageIcon("sydanpinni.png",
            "Sijaintimerkki varustettuna sydämellä.");
         this.rakennaLabel(nro);
    }
    
    private void rakennaLabel(int nro) {
        suosikkiLabel = new JLabel(Integer.toString(this.numero),
                        this.suosikkiIkoni, JLabel.CENTER);
        suosikkiLabel.setOpaque(true);
    }

    public Point maaritaKuvaan(LatLng koord) {
        this.sijaintiKuvassa.setLocation(koord.lat - karttaNyt.getLansiraja() * 1024,
                koord.lng - karttaNyt.getEtelaraja() * 1024);
        return this.sijaintiKuvassa;
    }
    
    public JLabel getSuosikkiLabel() {
        return this.suosikkiLabel;
    }
    
    public int getNumeroListalla() {
        return this.numero;
    }
    
    public Point getSijaintiKuvassa() {
        return this.sijaintiKuvassa;
    }
}
