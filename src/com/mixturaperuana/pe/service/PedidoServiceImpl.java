package com.mixturaperuana.pe.service;


import com.mixturaperuana.pe.enums.EstadoPedido;
import com.mixturaperuana.pe.enums.MetodoPago;
import com.mixturaperuana.pe.exception.AccesoDenegadoException;
import com.mixturaperuana.pe.exception.PedidoNoEncontradoException;
import com.mixturaperuana.pe.model.*;
import com.mixturaperuana.pe.repository.PedidoRepository;

import java.util.List;
import java.util.Map;

public class PedidoServiceImpl implements IPedidoService {
    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }


    @Override
    public Pedido crearPedido(Cliente cliente, List<Plato> platos, Empleado registradoPor, boolean paraLlevar, String direccion, int tiempoEntrega) {
        return null;
    }

    @Override
    public void actualizarEstadoPedido(Pedido pedido, EstadoPedido nuevoEstado, Empleado empleado) throws AccesoDenegadoException {
        if (!puedeActualizarEstado(empleado, pedido.getEstado(), nuevoEstado)) {throw new AccesoDenegadoException("No tiene permiso para actualizar el estado del pedido.");}
        pedido.setEstado(nuevoEstado);    pedidoRepository.save(pedido);
    }

    private boolean puedeActualizarEstado(Empleado empleado, EstadoPedido estadoActual, EstadoPedido nuevoEstado) {
        if (empleado instanceof Administrador) {return true;}
        switch (estadoActual) {
            case REGISTRADO:
                return (empleado instanceof Mesero) && (nuevoEstado == EstadoPedido.CONFIRMADO || nuevoEstado == EstadoPedido.CANCELADO);
            case CONFIRMADO:
                return (empleado instanceof Cajero) && nuevoEstado == EstadoPedido.PAGADO;
            case PAGADO:
                return (empleado instanceof AsistenteCocina || empleado instanceof Chef) &&nuevoEstado == EstadoPedido.EN_PREPARACION;
            case EN_PREPARACION:
                return (empleado instanceof AsistenteCocina || empleado instanceof Chef) &&nuevoEstado == EstadoPedido.LISTO_PARA_RECOGER;
            case LISTO_PARA_RECOGER:
                return (empleado instanceof Mesero) && nuevoEstado == EstadoPedido.EN_CAMINO || nuevoEstado == EstadoPedido.ENTREGADO;
            case EN_CAMINO:
                return (empleado instanceof Mesero) && nuevoEstado == EstadoPedido.ENTREGADO;
            default:
                return false;}}

    @Override
    public Pedido obtenerPedidoPorId(String idPedido) throws PedidoNoEncontradoException {
        return null;
    }

    @Override
    public List<Pedido> obtenerTodosPedidos() {
        return List.of();
    }

    @Override
    public List<Pedido> obtenerPedidosPorMesero(Mesero mesero) {
        return List.of();
    }

    @Override
    public List<Pedido> obtenerPedidosPendientes() {
        return List.of();
    }

    @Override
    public double calcularTotalVentas() {
        return 0;
    }

    @Override
    public Map<String, Integer> obtenerPlatosMasVendidos() {
        return Map.of();
    }

    @Override
    public Map<MetodoPago, Long> obtenerEstadisticasMetodosPago() {
        return Map.of();
    }

    @Override
    public Map<Empleado, Long> obtenerEmpleadosConMasPedidos() {
        return Map.of();
    }

    @Override
    public double calcularTiempoEntregaPromedio() {
        return 0;
    }
}
