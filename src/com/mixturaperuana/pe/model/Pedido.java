package com.mixturaperuana.pe.model;

import com.mixturaperuana.pe.enums.EstadoPedido;

import java.util.List;

public class Pedido {

    private static int contadorPedidos = 1;

    private int idPedido;
    private Cliente cliente;
    private List<Plato> platos;
    private EstadoPedido estado;
    private Empleado registradoPor;
    private boolean paraLlevar;
    private String direccion;
    private int tiempoEntrega;

    public Pedido(Cliente cliente, List<Plato> platos, Empleado registradoPor, boolean paraLlevar, String direccion, int tiempoEntrega) {
        this.idPedido = contadorPedidos++;
        this.cliente = cliente;
        this.platos = platos;
        this.estado = EstadoPedido.REGISTRADO;
        this.registradoPor = registradoPor;
        this.paraLlevar = paraLlevar;
        this.direccion = direccion;
        this.tiempoEntrega = tiempoEntrega;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(List<Plato> platos) {
        this.platos = platos;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Empleado getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(Empleado registradoPor) {
        this.registradoPor = registradoPor;
    }

    public boolean isParaLlevar() {
        return paraLlevar;
    }

    public void setParaLlevar(boolean paraLlevar) {
        this.paraLlevar = paraLlevar;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(int tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }
}
