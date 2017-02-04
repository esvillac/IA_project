/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author emele_000
 */
public class Variable_Difusa {

    HashMap rangos_funcion = new HashMap();

    public Variable_Difusa() {

    }

    public HashMap EvaluarRango(float valor) {
        Iterator it = rangos_funcion.entrySet().iterator();
        HashMap rangos_valor = new HashMap();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            Membresia mem = (Membresia) e.getValue();
            float val = mem.calculaMembresia(valor);
            if (val > 0) {
                rangos_valor.put(e.getKey(), val);
            }

        }
        return rangos_valor;
    }

    public HashMap getRangos() {
        return rangos_funcion;
    }

    public void setRangos(HashMap rangos) {
        this.rangos_funcion = rangos;
    }

}
