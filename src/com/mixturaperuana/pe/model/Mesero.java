package com.mixturaperuana.pe.model;


import com.mixturaperuana.pe.enums.Rol;

import java.time.LocalDate;
import java.util.List;

public class Mesero extends Empleado {

    private List<Integer> mesasAsignadas;
    private int propinasAcumuladas;

    public Mesero(int idEmpleado, String usuario, String contrasena, String nombre, String apellido,
                  String email, double salario, LocalDate fechaContratacion, List<Integer> mesasAsignadas) {
        super(idEmpleado, usuario, contrasena, nombre, apellido, email, Rol.MESERO, salario, fechaContratacion);
        this.mesasAsignadas = mesasAsignadas;
        this.propinasAcumuladas = 0;
    }
}
