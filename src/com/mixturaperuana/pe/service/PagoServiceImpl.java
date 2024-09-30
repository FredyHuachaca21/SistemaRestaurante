package com.mixturaperuana.pe.service;


import com.mixturaperuana.pe.enums.EstadoPedido;
import com.mixturaperuana.pe.enums.MetodoPago;
import com.mixturaperuana.pe.exception.AccesoDenegadoException;
import com.mixturaperuana.pe.model.Administrador;
import com.mixturaperuana.pe.model.Cajero;
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
        if (!(empleado instanceof Cajero) && !(empleado instanceof Administrador)) {
            throw new AccesoDenegadoException("Solo los cajeros y administradores pueden procesar pagos.");
        }

        if (pedido.getEstado() != EstadoPedido.CONFIRMADO &&
                pedido.getEstado() != EstadoPedido.LISTO_PARA_RECOGER &&
                pedido.getEstado() != EstadoPedido.EN_CAMINO) {
            throw new IllegalStateException("El pedido debe estar confirmado, listo para recoger o en camino para ser pagado.");
        }

        pedido.realizarPago(metodoPago, montoPagado);
        pedido.setEstado(EstadoPedido.PAGADO);
        pedidoRepository.save(pedido);
    }
}
