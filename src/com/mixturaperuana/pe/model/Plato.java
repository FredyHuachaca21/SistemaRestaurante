package com.mixturaperuana.pe.model;

public class Plato {
    private int idPlato;
    private String nombre;
    private double precio;
    private String descripcion;

    public Plato(int idPlato, String nombre, double precio, String descripcion) {
        this.idPlato = idPlato;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
