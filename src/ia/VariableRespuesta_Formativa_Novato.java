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
public class VariableRespuesta_Formativa_Novato extends Variable_Difusa {

    public VariableRespuesta_Formativa_Novato(float numero_repro) {
        float a = numero_repro;
        float b = numero_repro;
        float m = (a + b) / 2;
        if(numero_repro>3)b=3;
        this.rangos_funcion.put("Peligro_Prueba", new MembresiaTriangular(b, a, m));
        a = numero_repro;
        b = numero_repro+1;
        m = (a + b) / 2;
        this.rangos_funcion.put("Peligro_Reprobadas", new MembresiaTriangular(b, a, m));

        a = 1;
        b = 2;
        m = (a + b) / 2;
        this.rangos_funcion.put("Muy_Facil", new MembresiaTriangular(b, a, m));
        a = 2;
        b = 2;
        m = (a + b) / 2;
        this.rangos_funcion.put("Facil", new MembresiaTriangular(b, a, m));
        a = 2;
        b = 3;
        m = (a + b) / 2;
        this.rangos_funcion.put("Normal", new MembresiaTriangular(b, a, m));
        a = 3;
        b = 3;
        m = (a + b) / 2;
        this.rangos_funcion.put("Regular", new MembresiaTriangular(b, a, m));
        a = 3.0f;
        b = 4.0f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Dificil", new MembresiaTriangular(b, a, m));
        a = 4f;
        b = 5f;
        m = (a + b) / 2;
        this.rangos_funcion.put("Muy_Dificil", new MembresiaTriangular(b, a, m));
    }

}
