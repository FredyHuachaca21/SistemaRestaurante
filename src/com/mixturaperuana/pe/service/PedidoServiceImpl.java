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
        return null;
    }

    @Override
    public void actualizarEstadoPedido(Pedido pedido, EstadoPedido nuevoEstado, Empleado empleado) throws AccesoDenegadoException {

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
        return List.of();
    }

    @Override
    public double calcularTotalVentas() {
        return 0;
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
