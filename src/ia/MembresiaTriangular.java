/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import javax.swing.JOptionPane;

/**
 *
 * @author emele_000
 */
public class MembresiaTriangular implements Membresia{

    public float extremo_derecho;
    public float extremo_izquierdo;
    public float media;

    public MembresiaTriangular(float extremo_derecho, float extremo_izquierdo, float media) {
        this.extremo_derecho = extremo_derecho;
        this.extremo_izquierdo = extremo_izquierdo;
        this.media = media;
    }

    @Override
    public float calculaMembresia(float x) {
        if(x==media){return 1;}
        if (x <= extremo_izquierdo) {
            return 0;
        } else if ((x <= media) && x > extremo_izquierdo) {
            return (x - extremo_izquierdo) / (media - extremo_izquierdo);
        } else if (x < extremo_derecho && x > media) {
            return (extremo_derecho - x) / (extremo_derecho - media);
        } else if (x >= extremo_derecho) {
            return 0;
        }
        return 0;
    }

    public float getExtremo_derecho() {
        return extremo_derecho;
    }

    public void setExtremo_derecho(float extremo_derecho) {
        this.extremo_derecho = extremo_derecho;
    }

    public float getExtremo_izquierdo() {
        return extremo_izquierdo;
    }

    public void setExtremo_izquierdo(float extremo_izquierdo) {
        this.extremo_izquierdo = extremo_izquierdo;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

   /* public static void main(String[] args) {
        float a = 3;
        float b = 6;
        float m = (a + b) / 2;
        MembresiaTriangular x = new MembresiaTriangular(b, a, m);
        float h = x.calculaMembresia(4);
        JOptionPane.showMessageDialog(null, h, "trabajadores", 1);

    }*/  
}
