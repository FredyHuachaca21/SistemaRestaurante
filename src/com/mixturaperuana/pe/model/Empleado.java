package com.mixturaperuana.pe.model;


import com.mixturaperuana.pe.enums.Rol;

import java.time.LocalDate;

public abstract class Empleado extends Usuario {

    protected int idEmpleado;
    protected Rol rol;
    protected double salario;
    protected LocalDate fechaContratacion;

    public Empleado(int idEmpleado, String usuario, String contrasena, String nombre, String apellido,
                    String email, Rol rol, double salario, LocalDate fechaContratacion) {
        super(usuario, contrasena, nombre, apellido, email);
        this.idEmpleado = idEmpleado;
        this.rol = rol;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
}
