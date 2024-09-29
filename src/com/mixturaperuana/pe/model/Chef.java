package com.mixturaperuana.pe.model;


import com.mixturaperuana.pe.enums.Rol;

import java.time.LocalDate;

public class Chef extends Empleado {
    private String especialidadCulinaria;
    private int aniosExperiencia;

    public Chef(int idEmpleado, String usuario, String contrasena, String nombre, String apellido,
                String email, double salario, LocalDate fechaContratacion, String especialidadCulinaria, int aniosExperiencia) {
        super(idEmpleado, usuario, contrasena, nombre, apellido, email, Rol.CHEF, salario, fechaContratacion);
        this.especialidadCulinaria = especialidadCulinaria;
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getEspecialidadCulinaria() {
        return especialidadCulinaria;
    }

    public void setEspecialidadCulinaria(String especialidadCulinaria) {
        this.especialidadCulinaria = especialidadCulinaria;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
}
