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
public class VariableRespuesta_LibreOpcion_Novato extends Variable_Difusa {

    public VariableRespuesta_LibreOpcion_Novato() {
        float a = 0;
        float b = 0;
        float m = (a + b) / 2;
        this.rangos_funcion.put("Peligro_Prueba", new MembresiaTriangular(b, a, m));
        a = 0;
        b = 2;
        m = (a + b) / 2;
        this.rangos_funcion.put("Peligro_Reprobadas", new MembresiaTriangular(b, a, m));
        a = 0;
        b = 1;
        m = (a + b) / 2;
        this.rangos_funcion.put("Muy_Facil", new MembresiaTriangular(b, a, m));
        a = 0f;
        b = 0;
        m = (a + b) / 2;
        this.rangos_funcion.put("Facil", new MembresiaTriangular(b, a, m));
        a = 0.0f;
        b = 1.0f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Normal", new MembresiaTriangular(b, a, m));
        a = 0f;
        b = 0f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Regular", new MembresiaTriangular(b, a, m));
        a = 1.0f;
        b = 0.0f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Dificil", new MembresiaTriangular(b, a, m));
        a = 1f;
        b = 1f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Muy_Dificil", new MembresiaTriangular(b, a, m));
    }

}
