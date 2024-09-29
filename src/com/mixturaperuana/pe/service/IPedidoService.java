package com.mixturaperuana.pe.service;


import com.mixturaperuana.pe.enums.EstadoPedido;
import com.mixturaperuana.pe.enums.MetodoPago;
import com.mixturaperuana.pe.exception.AccesoDenegadoException;
import com.mixturaperuana.pe.exception.PedidoNoEncontradoException;
import com.mixturaperuana.pe.model.*;

import java.util.List;
import java.util.Map;

public interface IPedidoService {
    Pedido crearPedido(Cliente cliente, List<Plato> platos, Empleado registradoPor, boolean paraLlevar, String direccion, int tiempoEntrega);
    void actualizarEstadoPedido(Pedido pedido, EstadoPedido nuevoEstado, Empleado empleado) throws AccesoDenegadoException;
    Pedido obtenerPedidoPorId(String idPedido) throws PedidoNoEncontradoException;
    List<Pedido> obtenerTodosPedidos();
    List<Pedido> obtenerPedidosPorMesero(Mesero mesero);
    List<Pedido> obtenerPedidosPendientes();
    double calcularTotalVentas();
    Map<String, Integer> obtenerPlatosMasVendidos();
    Map<MetodoPago, Long> obtenerEstadisticasMetodosPago();
    Map<Empleado, Long> obtenerEmpleadosConMasPedidos();
    double calcularTiempoEntregaPromedio();

}
