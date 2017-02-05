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
public class VariablePromedio extends Variable_Difusa{


    public VariablePromedio() {
        float a = 6;
        float b = 7.5f;
        float m = (a + b) / 2;
        this.rangos_funcion.put("Bajo", new MembresiaTriangular(b, a, m));
        a = 6.5f;
        b = 8.5f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Regular", new MembresiaTriangular(b, a, m));
        a = 7.5f;
        b = 8.5f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Bueno", new MembresiaTriangular(b, a, m));
        a = 8.0f;
        b = 11.0f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Sobresaliente", new MembresiaTriangular(b, a, m));
 
    }


}
