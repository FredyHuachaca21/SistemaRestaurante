package com.mixturaperuana.pe.home;

import com.mixturaperuana.pe.controller.SistemaGestionPedidos;
import com.mixturaperuana.pe.repository.*;
import com.mixturaperuana.pe.service.*;
import com.mixturaperuana.pe.view.ConsoleView;

public class Principal {
    public static void main(String[] args) {
        // Inicializar repositorios
        EmpleadoRepository empleadoRepository = new EmpleadoRepositoryImpl();
        PlatoRepository platoRepository = new PlatoRepositoryImpl();
        PedidoRepository pedidoRepository = new PedidoRepositoryImpl();

        // Inicializar servicios
        IEmpleadoService empleadoService = new EmpleadoServiceImpl(empleadoRepository);
        IPlatoService platoService = new PlatoServiceImpl(platoRepository);
        IPedidoService pedidoService = new PedidoServiceImpl(pedidoRepository);
        IPagoService pagoService = new PagoServiceImpl(pedidoRepository);

        // Inicializar vista
        ConsoleView view = new ConsoleView();

        // Inicializar y ejecutar el controlador principal
        SistemaGestionPedidos sistema = new SistemaGestionPedidos(view, empleadoService, pedidoService, platoService, pagoService);
        sistema.iniciar();
    }
}
