package com.mixturaperuana.pe.data;


import com.mixturaperuana.pe.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DataEmpleados {

    public static Map<String, Empleado> inicializar() {
        Map<String, Empleado> empleados = new HashMap<>();
        empleados.put("mesero1", new Mesero(1, "mesero1", "1234", "Juan", "Pérez", "juan@example.com", 1000.0, LocalDate.of(2022, 1, 15), Arrays.asList(1, 2)));
        empleados.put("cocina1", new AsistenteCocina(2, "cocina1", "1234", "María", "González", "maria@example.com", 1200.0, LocalDate.of(2021, 11, 1), "Postres"));
        empleados.put("chef1", new Chef(3, "chef1", "1234", "Carlos", "Rodríguez", "carlos@example.com", 1800.0, LocalDate.of(2020, 6, 1), "Cocina Internacional", 10));
        empleados.put("cajero1", new Cajero(4, "cajero1", "1234", "Ana", "Martínez", "ana@example.com", 1100.0, LocalDate.of(2022, 3, 1)));
        empleados.put("admin", new Administrador(5, "admin", "admin", "Luis", "Sánchez", "luis@example.com", 2000.0, LocalDate.of(2019, 1, 1), 1, "Gerencia"));
        return empleados;
    }

}
