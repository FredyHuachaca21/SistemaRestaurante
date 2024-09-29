package com.mixturaperuana.pe.service;


import com.mixturaperuana.pe.enums.MetodoPago;
import com.mixturaperuana.pe.exception.AccesoDenegadoException;
import com.mixturaperuana.pe.model.Empleado;
import com.mixturaperuana.pe.model.Pedido;

public interface IPagoService {
    void procesarPago(Pedido pedido, MetodoPago metodoPago, double montoPagado, Empleado empleado) throws AccesoDenegadoException;

}
