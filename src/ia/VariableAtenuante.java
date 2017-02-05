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
public class VariableAtenuante extends Variable_Difusa{


    public VariableAtenuante() {
        float a = 0;
        float b = 0;
        float m = (a + b) / 2;
        this.rangos_funcion.put("No_Atenuantes", new MembresiaTriangular(b, a, m));
        a = 0f;
        b = 5f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Regular", new MembresiaTriangular(b, a, m));
        a = 3.0f;
        b = 8.0f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Dificil", new MembresiaTriangular(b, a, m));
        a = 6f;
        b = 11f;//cambios
        m = (a + b) / 2;
        this.rangos_funcion.put("Muy_Dificil", new MembresiaTriangular(b, a, m));
    }


}
