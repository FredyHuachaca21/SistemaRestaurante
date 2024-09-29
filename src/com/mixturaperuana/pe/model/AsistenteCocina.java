package com.mixturaperuana.pe.model;


import com.mixturaperuana.pe.enums.Rol;

import java.time.LocalDate;

public class AsistenteCocina extends Empleado {

    private String especialidad;
    private int platosPreparados;

    public AsistenteCocina(int idEmpleado, String usuario, String contrasena, String nombre, String apellido,
                           String email, double salario, LocalDate fechaContratacion, String especialidad) {
        super(idEmpleado, usuario, contrasena, nombre, apellido, email, Rol.ASISTENTE_COCINA, salario, fechaContratacion);
        this.especialidad = especialidad;
        this.platosPreparados = 0;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getPlatosPreparados() {
        return platosPreparados;
    }

    public void setPlatosPreparados(int platosPreparados) {
        this.platosPreparados = platosPreparados;
    }
}
