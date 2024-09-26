package com.mixturaperuana.pe.model;

import com.mixturaperuana.pe.enums.Rol;

public class AsistenteCocina extends Empleado{
    private String especialidad;

    public AsistenteCocina(String usuario, String contrasena, int idEmpleado, Rol rol, double salario, String especialidad) {
        super(usuario, contrasena, idEmpleado, rol, salario);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}
