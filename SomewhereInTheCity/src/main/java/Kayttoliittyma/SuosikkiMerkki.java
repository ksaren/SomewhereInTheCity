/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Kayttajat.Toimija;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.ImageIcon;

/** Luokka suosikkitoimijaa kuvaavaa merkkiä varten.
 *
 * @author kaisa
 */
public class SuosikkiMerkki extends Merkki{
    
    
    private ImageIcon suosikkiIkoni;
    
    public SuosikkiMerkki(Toimija t) {
        super(t);
        this.suosikkiIkoni = new ImageIcon("sydanpinni.png",
            "Sijaintimerkki varustettuna sydämellä.");
    }
    
   
    public Graphics getSuosikkiGrafiikka() {
        return this.suosikkiIkoni.getImage().getGraphics();
    }
    
    public ImageIcon getSuosikkiIkoni() {
        return this.suosikkiIkoni;
    }
    
     /**
     * Metodi joka poistaa toimijan merkin kartalta jos toimija on lopettanut myynnin, ei kuulu enää 
     * suosikkeihin tai ei näy enää kartalla.
     * @return 
     */
    public boolean poistaMerkkiKuvasta() {
        return true;
    }
}
