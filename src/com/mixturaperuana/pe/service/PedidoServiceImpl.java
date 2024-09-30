package com.mixturaperuana.pe.service;


import com.mixturaperuana.pe.enums.EstadoPedido;
import com.mixturaperuana.pe.enums.MetodoPago;
import com.mixturaperuana.pe.exception.AccesoDenegadoException;
import com.mixturaperuana.pe.exception.PedidoNoEncontradoException;
import com.mixturaperuana.pe.model.*;
import com.mixturaperuana.pe.repository.PedidoRepository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PedidoServiceImpl implements IPedidoService {
    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }


    @Override
    public Pedido crearPedido(Cliente cliente, List<Plato> platos, Empleado registradoPor, boolean paraLlevar, String direccion, int tiempoEntrega) {
        Pedido pedido = new Pedido(cliente, platos, registradoPor, paraLlevar, direccion, tiempoEntrega);
        pedidoRepository.save(pedido);
        return pedido;
    }

    @Override
    public void actualizarEstadoPedido(Pedido pedido, EstadoPedido nuevoEstado, Empleado empleado) throws AccesoDenegadoException {
        if (!puedeActualizarEstado(empleado, pedido.getEstado(), nuevoEstado)) {throw new AccesoDenegadoException("No tiene permiso para actualizar el estado del pedido.");}
        pedido.setEstado(nuevoEstado);    pedidoRepository.save(pedido);
    }

    private boolean puedeActualizarEstado(Empleado empleado, EstadoPedido estadoActual, EstadoPedido nuevoEstado) {
        if (empleado instanceof Administrador) {
            return true;
        }
        switch (estadoActual) {
            case REGISTRADO:
                return (empleado instanceof Mesero) && (nuevoEstado == EstadoPedido.CONFIRMADO || nuevoEstado == EstadoPedido.CANCELADO);
            case CONFIRMADO:
                return (empleado instanceof Cajero) && nuevoEstado == EstadoPedido.PAGADO;
            case PAGADO:
                return (empleado instanceof AsistenteCocina || empleado instanceof Chef) && nuevoEstado == EstadoPedido.EN_PREPARACION;
            case EN_PREPARACION:
                return (empleado instanceof AsistenteCocina || empleado instanceof Chef) && nuevoEstado == EstadoPedido.LISTO_PARA_RECOGER;
            case LISTO_PARA_RECOGER:
                return (empleado instanceof Mesero) && nuevoEstado == EstadoPedido.EN_CAMINO || nuevoEstado == EstadoPedido.ENTREGADO;
            case EN_CAMINO:
                return (empleado instanceof Mesero) && nuevoEstado == EstadoPedido.ENTREGADO;
            default:
                return false;
        }
    }

    @Override
    public Pedido obtenerPedidoPorId(String idPedido) throws PedidoNoEncontradoException {
        return pedidoRepository.findById(idPedido);
    }

    @Override
    public List<Pedido> obtenerTodosPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public List<Pedido> obtenerPedidosPorMesero(Mesero mesero) {
        return pedidoRepository.findByRegistradoPor(mesero);
    }

    @Override
    public List<Pedido> obtenerPedidosPendientes() {
        return pedidoRepository.findAll().stream()
                .filter(p -> p.getEstado() != EstadoPedido.ENTREGADO && p.getEstado() != EstadoPedido.CANCELADO)
                .collect(Collectors.toList());
    }

    @Override
    public double calcularTotalVentas() {
        return pedidoRepository.findAll().stream()
                .filter(p -> p.getEstado() == EstadoPedido.ENTREGADO)
                .mapToDouble(Pedido::getTotal)
                .sum();
    }

    @Override
    public Map<String, Integer> obtenerPlatosMasVendidos() {
        Map<String, Integer> platosVendidos = new HashMap<>();
        pedidoRepository.findAll().stream()
                .filter(p -> p.getEstado() == EstadoPedido.ENTREGADO)
                .forEach(p -> p.getPlatos().forEach(plato -> {
                    platosVendidos.merge(plato.getNombre(), 1, Integer::sum);}));
        return platosVendidos.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public Map<MetodoPago, Long> obtenerEstadisticasMetodosPago() {
        return pedidoRepository.findAll().stream()
                .filter(p -> p.getMetodoPago() != null)
                .collect(Collectors.groupingBy(Pedido::getMetodoPago, Collectors.counting()));
    }

    @Override
    public Map<Empleado, Long> obtenerEmpleadosConMasPedidos() {
        return pedidoRepository.findAll().stream()
                .collect(Collectors.groupingBy(Pedido::getRegistradoPor, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Empleado, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public double calcularTiempoEntregaPromedio() {
        List<Pedido> pedidosParaLlevar = pedidoRepository.findAll().stream()
                .filter(Pedido::isParaLlevar)
                .toList();

        if (pedidosParaLlevar.isEmpty()) {
            return 0;

        }return pedidosParaLlevar.stream()
                .mapToInt(Pedido::getTiempoEntrega)
                .average()
                .orElse(0);
    }
}
