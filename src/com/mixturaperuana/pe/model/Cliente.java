package com.mixturaperuana.pe.model;

import java.time.LocalDate;

public class Cliente extends Usuario {

    private static int contadorClientes = 1;
    private int idCliente;
    private String direccion;
    private String telefono;
    private LocalDate fechaRegistro;
    private int puntosAcumulados;

    public Cliente(int idCliente, String usuario, String contrasena, String nombre, String apellido,
                   String email, String direccion, String telefono) {
        super(usuario, contrasena, nombre, apellido, email);
        this.idCliente = contadorClientes++;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaRegistro = LocalDate.now();
        this.puntosAcumulados = 0;
    }

    public static int obtenerSiguienteId() {
        return contadorClientes++;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }
}
