package com.mixturaperuana.pe.service;


import com.mixturaperuana.pe.enums.MetodoPago;
import com.mixturaperuana.pe.exception.AccesoDenegadoException;
import com.mixturaperuana.pe.model.Empleado;
import com.mixturaperuana.pe.model.Pedido;
import com.mixturaperuana.pe.repository.PedidoRepository;

public class PagoServiceImpl implements IPagoService {

    private PedidoRepository pedidoRepository;

    public PagoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }


    @Override
    public void procesarPago(Pedido pedido, MetodoPago metodoPago, double montoPagado, Empleado empleado) throws AccesoDenegadoException {

    }
}
