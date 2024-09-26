package com.mixturaperuana.pe.data;

public class PedidoData {
    private int idPedido;
    private Cliente cliente;
    private List<Plato> platos;
    private EstadoPedido estado;
    private Empleado registradoPor;
    private boolean paraLlevar;
    private String direccion;
    private int tiempoEntrega;

    public PedidoData(int idPedido, Cliente cliente, List<Plato> platos, EstadoPedido estado, Empleado registradoPor, boolean paraLlevar, String direccion, int tiempoEntrega) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.platos = platos;
        this.estado = estado;
        this.registradoPor = registradoPor;
        this.paraLlevar = paraLlevar;
        this.direccion = direccion;
        this.tiempoEntrega = tiempoEntrega;
    }

    public int getIdPedido() {
        return idPedido;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public EstadoPedido getEstado() {
        return estado;
    }
    public List<Plato> getPlatos() {
        return platos;
    }
    public Empleado getRegistradoPor() {
        return registradoPor;
    }
    public String getDireccion() {
        return direccion;
    }
    public int getTiempoEntrega() {
        return tiempoEntrega;
    }
    public boolean isParaLlevar() {
        return paraLlevar;
    }
}
