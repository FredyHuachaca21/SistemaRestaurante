package com.mixturaperuana.pe.model;


import com.mixturaperuana.pe.enums.Rol;

import java.time.LocalDate;

public class Cajero extends Empleado {

    private double efectivoManejado;
    private int transaccionesRealizadas;

    public Cajero(int idEmpleado, String usuario, String contrasena, String nombre, String apellido,
                  String email, double salario, LocalDate fechaContratacion) {
        super(idEmpleado, usuario, contrasena, nombre, apellido, email, Rol.CAJERO, salario, fechaContratacion);
        this.efectivoManejado = 0;
        this.transaccionesRealizadas = 0;
    }
}
