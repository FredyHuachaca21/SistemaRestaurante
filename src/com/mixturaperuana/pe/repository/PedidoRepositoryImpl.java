package com.mixturaperuana.pe.repository;


import com.mixturaperuana.pe.data.DataPedidos;
import com.mixturaperuana.pe.enums.EstadoPedido;
import com.mixturaperuana.pe.exception.PedidoNoEncontradoException;
import com.mixturaperuana.pe.model.Empleado;
import com.mixturaperuana.pe.model.Pedido;

import java.util.List;
import java.util.Map;

public class PedidoRepositoryImpl implements PedidoRepository {

    private final Map<String, Pedido> pedidos;

    public PedidoRepositoryImpl() {
        this.pedidos = DataPedidos.inicializar();
    }

    @Override
    public void save(Pedido pedido) {

    }

    @Override
    public Pedido findById(String idPedido) throws PedidoNoEncontradoException {
        return null;
    }

    @Override
    public List<Pedido> findAll() {
        return List.of();
    }

    @Override
    public List<Pedido> findByEstado(EstadoPedido estado) {
        return List.of();
    }

    @Override
    public List<Pedido> findByRegistradoPor(Empleado empleado) {
        return List.of();
    }
}
