package com.mixturaperuana.pe.model;

import com.mixturaperuana.pe.enums.Rol;

import java.util.List;

public class Mesero extends Empleado{
    private List<Integer> mesasAsignadas;

    public Mesero(String usuario, String contrasena, int idEmpleado, Rol rol, double salario, List<Integer> mesasAsignadas) {
        super(usuario, contrasena, idEmpleado, rol, salario);
        this.mesasAsignadas = mesasAsignadas;
    }

    public List<Integer> getMesasAsignadas() {
        return mesasAsignadas;
    }
}
