package com.mixturaperuana.pe.model;

import com.mixturaperuana.pe.enums.Rol;

public class Administrador extends Empleado{
    private int nivelAcceso;

    public Administrador(String usuario, String contrasena, int idEmpleado, Rol rol, double salario, int nivelAcceso) {
        super(usuario, contrasena, idEmpleado, rol, salario);
        this.nivelAcceso = nivelAcceso;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }
}
