package com.mixturaperuana.pe.model;


import com.mixturaperuana.pe.enums.EstadoPedido;
import com.mixturaperuana.pe.enums.MetodoPago;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private static int contadorPedidos = 1;
    private String idPedido;
    private Cliente cliente;
    private List<Plato> platos;
    private EstadoPedido estado;
    private Empleado registradoPor;
    private boolean paraLlevar;
    private String direccionEntrega;
    private int tiempoEntrega;
    private LocalDateTime fechaPedido;
    private double total;
    private MetodoPago metodoPago;
    private boolean pagado;
    private double montoPagado;
    private double vuelto;
    private boolean consumoLocal;

    public Pedido(Cliente cliente, List<Plato> platos, Empleado registradoPor, boolean paraLlevar, String direccionEntrega, int tiempoEntrega) {
        this.idPedido = generarIdUnico(cliente);
        this.cliente = cliente;
        this.platos = platos;
        this.estado = EstadoPedido.REGISTRADO;
        this.registradoPor = registradoPor;
        this.paraLlevar = paraLlevar;
        this.direccionEntrega = direccionEntrega;
        this.tiempoEntrega = tiempoEntrega;
        this.fechaPedido = LocalDateTime.now();
        this.total = calcularTotal();
        this.pagado = false;
        this.consumoLocal = !paraLlevar;
    }

    private String generarIdUnico(Cliente cliente) {
        String iniciales = obtenerIniciales(cliente.getNombre());
        String numeroSecuencia = String.format("%04d", contadorPedidos++);
        return iniciales + numeroSecuencia;
    }

    private String obtenerIniciales(String nombre) {
        StringBuilder iniciales = new StringBuilder();
        for (String parte : nombre.split(" ")) {
            if (!parte.isEmpty()) {
                iniciales.append(parte.charAt(0));
            }
        }
        return iniciales.toString().toUpperCase();
    }

    private double calcularTotal() {
        return platos.stream().mapToDouble(Plato::getPrecio).sum();
    }

    public void realizarPago(MetodoPago metodoPago, double montoPagado) {
        this.metodoPago = metodoPago;
        this.montoPagado = montoPagado;
        this.vuelto = montoPagado - total;
        this.pagado = true;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
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

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public int getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(int tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public double getVuelto() {
        return vuelto;
    }

    public void setVuelto(double vuelto) {
        this.vuelto = vuelto;
    }

    public boolean isConsumoLocal() {
        return consumoLocal;
    }

    public void setConsumoLocal(boolean consumoLocal) {
        this.consumoLocal = consumoLocal;
    }
}
