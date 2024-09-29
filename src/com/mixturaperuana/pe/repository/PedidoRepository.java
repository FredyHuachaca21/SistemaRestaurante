package com.mixturaperuana.pe.repository;


import com.mixturaperuana.pe.enums.EstadoPedido;
import com.mixturaperuana.pe.exception.PedidoNoEncontradoException;
import com.mixturaperuana.pe.model.Empleado;
import com.mixturaperuana.pe.model.Pedido;

import java.util.List;

public interface PedidoRepository {
    void save(Pedido pedido);
    Pedido findById(String idPedido) throws PedidoNoEncontradoException;
    List<Pedido> findAll();
    List<Pedido> findByEstado(EstadoPedido estado);
    List<Pedido> findByRegistradoPor(Empleado empleado);
}
