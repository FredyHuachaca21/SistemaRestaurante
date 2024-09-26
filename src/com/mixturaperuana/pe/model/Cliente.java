package com.mixturaperuana.pe.model;

public class Cliente extends Usuario{
    private int idCliente;
    private String nombreCliente;

    public Cliente(String usuario, String contrasena, int idCliente, String nombreCliente) {
        super(usuario, contrasena);
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }
}
