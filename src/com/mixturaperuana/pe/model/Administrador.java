package com.mixturaperuana.pe.model;


import com.mixturaperuana.pe.enums.Rol;

import java.time.LocalDate;

public class Administrador extends Empleado {
    private int nivelAcceso;
    private String departamento;

    public Administrador(int idEmpleado, String usuario, String contrasena, String nombre, String apellido,
                         String email, double salario, LocalDate fechaContratacion, int nivelAcceso, String departamento) {
        super(idEmpleado, usuario, contrasena, nombre, apellido, email, Rol.ADMINISTRADOR, salario, fechaContratacion);
        this.nivelAcceso = nivelAcceso;
        this.departamento = departamento;
    }
}
