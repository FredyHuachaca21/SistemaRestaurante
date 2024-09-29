package com.mixturaperuana.pe.data;


import com.mixturaperuana.pe.model.Plato;

import java.util.HashMap;
import java.util.Map;

public class DataPlatos {
    public static Map<Integer, Plato> inicializar() {
        Map<Integer, Plato> platos = new HashMap<>();
        platos.put(1, new Plato(1, "Lomo Saltado", 25.0, "Delicioso lomo saltado tradicional", "Plato Principal"));
        platos.put(2, new Plato(2, "Ají de Gallina", 20.0, "Sabroso ají de gallina con crema", "Plato Principal"));
        platos.put(3, new Plato(3, "Ceviche", 22.0, "Fresco ceviche de pescado", "Entrada"));
        return platos;
    }
}
