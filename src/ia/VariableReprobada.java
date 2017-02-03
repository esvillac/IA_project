/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.HashMap;

/**
 *
 * @author emele_000
 */
public class VariableReprobada extends Variable_Difusa{


    public VariableReprobada() {
        float a = 0;
        float b = 0;
        float m = (a + b) / 2;
        this.rangos_funcion.put("Facil", new MembresiaTriangular(b, a, m));
        a = 0.0f;
        b = 4.5f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Regular", new MembresiaTriangular(b, a, m));
        a = 3.5f;
        b = 8.0f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Dificil", new MembresiaTriangular(b, a, m));
        a = 6.5f;
        b = 10.0f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Muy Dificil", new MembresiaTriangular(b, a, m));
     
    }


}
