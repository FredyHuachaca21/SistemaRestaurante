package com.mixturaperuana.pe.model;

import com.mixturaperuana.pe.enums.Rol;

public abstract class Empleado extends Usuario {
    private int idEmpleado;
    private Rol rol;
    private double salario;

    public Empleado(String usuario, String contrasena, int idEmpleado, Rol rol, double salario) {
        super(usuario, contrasena);
        this.idEmpleado = idEmpleado;
        this.rol = rol;
        this.salario = salario;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public Rol getRol() {
        return rol;
    }

    public double getSalario() {
        return salario;
    }
}
