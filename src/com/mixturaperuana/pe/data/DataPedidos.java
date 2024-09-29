package com.mixturaperuana.pe.data;


import com.mixturaperuana.pe.model.Pedido;

import java.util.HashMap;
import java.util.Map;

public class DataPedidos {
    public static Map<String, Pedido> inicializar() {
        return new HashMap<>(); // Inicialmente vacío
    }
}
