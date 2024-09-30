package com.mixturaperuana.pe.controller;

import com.mixturaperuana.pe.enums.EstadoPedido;
import com.mixturaperuana.pe.enums.MetodoPago;
import com.mixturaperuana.pe.exception.*;
import com.mixturaperuana.pe.model.*;
import com.mixturaperuana.pe.service.IEmpleadoService;
import com.mixturaperuana.pe.service.IPagoService;
import com.mixturaperuana.pe.service.IPedidoService;
import com.mixturaperuana.pe.service.IPlatoService;
import com.mixturaperuana.pe.utils.DateUtils;
import com.mixturaperuana.pe.utils.ValidationUtils;
import com.mixturaperuana.pe.view.ConsoleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SistemaGestionPedidos {
    private final ConsoleView view;
    private final IEmpleadoService empleadoService;
    private final IPedidoService pedidoService;
    private final IPlatoService platoService;
    private final IPagoService pagoService;

    public SistemaGestionPedidos(ConsoleView view, IEmpleadoService empleadoService,
                                 IPedidoService pedidoService, IPlatoService platoService,
                                 IPagoService pagoService) {
        this.view = view;
        this.empleadoService = empleadoService;
        this.pedidoService = pedidoService;
        this.platoService = platoService;
        this.pagoService = pagoService;
    }

    public void iniciar() {
        boolean sistemaActivo = true;

        while (sistemaActivo) {
            try {
                Empleado empleadoActual = login();
                if (empleadoActual != null) {
                    manejarSesionEmpleado(empleadoActual);
                }
            } catch (UsuarioNoEncontradoException e) {
                view.mostrarMensaje("Error: " + e.getMessage());
            }

            String respuesta = view.solicitarEntrada("¿Desea salir del sistema? (s/n): ");
            if (respuesta.equalsIgnoreCase("s")) {
                sistemaActivo = false;
            }
        }
        view.mostrarMensaje("¡Gracias por usar el Sistema de Gestión de Pedidos!");
    }

    private void mostrarListaPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerTodosPedidos();
        view.mostrarListaPedidos(pedidos);
    }

    private Empleado login() throws UsuarioNoEncontradoException {
        view.mostrarMensaje("\n--- 🔐 Inicio de Sesión ---");
        String usuario = view.solicitarEntrada("Usuario: ");
        String contrasena = view.solicitarEntrada("Contraseña: ");
        Empleado empleado = empleadoService.login(usuario, contrasena);
        view.mostrarMensaje("✅ Inicio de sesión exitoso. Bienvenido, " + empleado.getNombre() + " " + empleado.getApellido() + ".");
        return empleado;
    }

    private void manejarSesionEmpleado(Empleado empleado) {
        boolean sesionActiva = true;

        while (sesionActiva) {
            view.mostrarMenu(empleado.getRol());
            int opcion = Integer.parseInt(view.solicitarEntrada("Seleccione una opción: "));

            try {
                switch (empleado.getRol()) {
                    case MESERO:
                        manejarOpcionesMesero(empleado, opcion);
                        break;
                    case ASISTENTE_COCINA:
                    case CHEF:
                        manejarOpcionesCocina(empleado, opcion);
                        break;
                    case CAJERO:
                        manejarOpcionesCajero(empleado, opcion);
                        break;
                    case ADMINISTRADOR:
                        manejarOpcionesAdministrador(empleado, opcion);
                        break;
                }
                if (opcion == 0) {
                    sesionActiva = false;
                    view.mostrarMensaje("Sesión cerrada. ¡Hasta pronto, " + empleado.getNombre() + "!");
                }
            } catch (NumberFormatException e) {
                view.mostrarMensaje("Error: Por favor, ingrese un número válido.");

            }catch (AccesoDenegadoException | OpcionNoValidaException e) {
                view.mostrarMensaje("Error: " + e.getMessage());
            }
        }
    }

    private void manejarOpcionesMesero(Empleado empleado, int opcion) throws AccesoDenegadoException, OpcionNoValidaException {
        switch (opcion) {
            case 1:
                registrarPedido(empleado);
                break;
            case 2:
                confirmarOCancelarPedido((Mesero) empleado);
                break;
            case 3:
                actualizarEstadoPedido(empleado);
                break;
            case 4:
                consultarPedidos(empleado);
                break;
            case 0:
                break;
            default:
                throw new OpcionNoValidaException("Opción no válida.");
        }
    }

    private void manejarOpcionesCocina(Empleado empleado, int opcion) throws AccesoDenegadoException, OpcionNoValidaException {
        switch (opcion) {
            case 1:
                actualizarEstadoPedido(empleado);
                break;
            case 2:
                consultarPedidos(empleado);
                break;
            case 0:
                break;
            default:
                throw new OpcionNoValidaException("Opción no válida.");
        }
    }

    private void manejarOpcionesCajero(Empleado empleado, int opcion) throws AccesoDenegadoException, OpcionNoValidaException {
        switch (opcion) {
            case 1:
                consultarPedidos(empleado);
                break;
            case 2:
                procesarPago(empleado);
                break;
            case 0:
                break;
            default:
                throw new OpcionNoValidaException("Opción no válida.");
        }
    }

    private void manejarOpcionesAdministrador(Empleado empleado, int opcion) throws AccesoDenegadoException, OpcionNoValidaException {
        switch (opcion) {
            case 1:
                registrarPedido(empleado);
                break;
            case 2:
                actualizarEstadoPedido(empleado);
                break;
            case 3:
                consultarPedidos(empleado);
                break;
            case 4:
                procesarPago(empleado);
                break;
            case 5:
                verEstadisticas();
                break;
            case 0:
                break;
            default:
                throw new OpcionNoValidaException("Opción no válida.");
        }
    }

    private void confirmarOCancelarPedido(Mesero mesero) {
        mostrarListaPedidos();
        try {
            String idPedido = view.solicitarEntrada("ID del pedido a confirmar o cancelar: ");
            Pedido pedido = pedidoService.obtenerPedidoPorId(idPedido);

            if (pedido.getEstado() != EstadoPedido.REGISTRADO) {
                view.mostrarMensaje("Este pedido no puede ser confirmado o cancelado en su estado actual.");
                return;
            }

            view.mostrarMensaje("1. Confirmar pedido");
            view.mostrarMensaje("2. Cancelar pedido");
            int opcion = Integer.parseInt(view.solicitarEntrada("Seleccione una opción: "));

            EstadoPedido nuevoEstado = (opcion == 1) ? EstadoPedido.CONFIRMADO : EstadoPedido.CANCELADO;
            pedidoService.actualizarEstadoPedido(pedido, nuevoEstado, mesero);
            view.mostrarMensaje("Pedido " + nuevoEstado.toString().toLowerCase() + " exitosamente.");

        } catch (PedidoNoEncontradoException | AccesoDenegadoException e) {
            view.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private void registrarPedido(Empleado empleado) {
        try {
            List<Plato> platosDisponibles = platoService.obtenerPlatosDisponibles();
            view.mostrarPlatosDisponibles(platosDisponibles);

            int idCliente = Cliente.obtenerSiguienteId();
            view.mostrarMensaje("ID del cliente: " + idCliente);

            String nombreCliente;
            do {
                nombreCliente = view.solicitarEntrada("Nombre del cliente: ");
                if (!ValidationUtils.isValidName(nombreCliente)) {
                    view.mostrarMensaje("Nombre inválido. Debe contener al menos 2 letras.");
                }
            } while (!ValidationUtils.isValidName(nombreCliente));

            String apellidoCliente;
            do {
                apellidoCliente = view.solicitarEntrada("Apellido del cliente: ");
                if (!ValidationUtils.isValidName(apellidoCliente)) {
                    view.mostrarMensaje("Apellido inválido. Debe contener al menos 2 letras.");
                }
            } while (!ValidationUtils.isValidName(apellidoCliente));

            String emailCliente;
            do {
                emailCliente = view.solicitarEntrada("Email del cliente: ");
                if (!ValidationUtils.isValidEmail(emailCliente)) {
                    view.mostrarMensaje("Email inválido. Por favor, ingrese un email válido.");
                }
            } while (!ValidationUtils.isValidEmail(emailCliente));

            String telefonoCliente;
            do {
                telefonoCliente = view.solicitarEntrada("Teléfono del cliente: ");
                if (!ValidationUtils.isValidPhoneNumber(telefonoCliente)) {
                    view.mostrarMensaje("Número de teléfono inválido. Debe contener 9 dígitos.");
                }
            } while (!ValidationUtils.isValidPhoneNumber(telefonoCliente));

            Cliente cliente = new Cliente(idCliente, nombreCliente, apellidoCliente,
                    nombreCliente.toLowerCase(), "password", emailCliente, "", telefonoCliente);

            boolean paraLlevar = view.solicitarEntrada("¿El pedido es para llevar? (s/n): ").equalsIgnoreCase("s");
            String direccion = "";
            int tiempoEntrega = 0;

            if (paraLlevar) {
                direccion = view.solicitarEntrada("Dirección de entrega: ");
                int distancia = Integer.parseInt(view.solicitarEntrada("Distancia en km: "));
                tiempoEntrega = distancia * 5; // 5 minutos por kilómetro
            }

            List<Plato> platosSeleccionados = new ArrayList<>();
            boolean agregarPlatos = true;

            while (agregarPlatos) {
                try {
                    int idPlato = Integer.parseInt(view.solicitarEntrada("ID del plato a agregar: "));
                    Plato plato = platoService.obtenerPlatoPorId(idPlato);
                    platosSeleccionados.add(plato);

                    agregarPlatos = view.solicitarEntrada("¿Desea agregar otro plato? (s/n): ").equalsIgnoreCase("s");
                } catch (NumberFormatException e) {
                    view.mostrarMensaje("Error: Ingrese un número válido para el ID del plato.");
                } catch (PlatoNoEncontradoException e) {
                    view.mostrarMensaje("Error: " + e.getMessage());
                }
            }

            Pedido pedido = pedidoService.crearPedido(cliente, platosSeleccionados, empleado, paraLlevar, direccion, tiempoEntrega);
            view.mostrarMensaje("Pedido registrado con éxito. ID del pedido: " + pedido.getIdPedido());
            view.mostrarMensaje("Fecha y hora del pedido: " + DateUtils.formatDateTime(pedido.getFechaPedido()));

        } catch (NumberFormatException e) {
            view.mostrarMensaje("Error: Ingrese un número válido.");
        }
    }

    private void actualizarEstadoPedido(Empleado empleado) {
        mostrarListaPedidos();
        try {
            String idPedido = view.solicitarEntrada("ID del pedido a actualizar: ");
            Pedido pedido = pedidoService.obtenerPedidoPorId(idPedido);

            view.mostrarMensaje("Estado actual: " + pedido.getEstado());
            EstadoPedido nuevoEstado = solicitarNuevoEstado(pedido, empleado);

            if (nuevoEstado != null) {
                pedidoService.actualizarEstadoPedido(pedido, nuevoEstado, empleado);
                view.mostrarMensaje("Estado del pedido actualizado con éxito a: " + nuevoEstado);
            } else {
                view.mostrarMensaje("No se ha realizado ningún cambio en el estado del pedido.");
            }
        } catch (PedidoNoEncontradoException | AccesoDenegadoException e) {
            view.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private EstadoPedido solicitarNuevoEstado(Pedido pedido, Empleado empleado) {
        List<EstadoPedido> estadosPermitidos = new ArrayList<>();
        String mensajeRol = "";

        switch (pedido.getEstado()) {
            case REGISTRADO:
                if (empleado instanceof Mesero || empleado instanceof Administrador) {
                    estadosPermitidos.add(EstadoPedido.CONFIRMADO);
                    estadosPermitidos.add(EstadoPedido.CANCELADO);
                } else {
                    mensajeRol = "Este estado solo puede ser cambiado por un Mesero o Administrador.";
                }
                break;
            case CONFIRMADO:
                if (empleado instanceof Cajero || empleado instanceof Administrador) {
                    estadosPermitidos.add(EstadoPedido.PAGADO);
                } else {
                    mensajeRol = "El siguiente paso es el pago. Debe ser realizado por un Cajero o Administrador.";
                }
                break;
            case PAGADO:
                if (empleado instanceof AsistenteCocina || empleado instanceof Chef || empleado instanceof Administrador) {
                    estadosPermitidos.add(EstadoPedido.EN_PREPARACION);
                } else {
                    mensajeRol = "Este estado solo puede ser cambiado por un Asistente de Cocina, Chef o Administrador.";
                }
                break;
            case EN_PREPARACION:
                if (empleado instanceof AsistenteCocina || empleado instanceof Chef || empleado instanceof Administrador) {
                    estadosPermitidos.add(EstadoPedido.LISTO_PARA_RECOGER);
                } else {
                    mensajeRol = "Este estado solo puede ser cambiado por un Asistente de Cocina, Chef o Administrador.";
                }
                break;
            case LISTO_PARA_RECOGER:
                if (empleado instanceof Mesero || empleado instanceof Administrador) {
                    if (pedido.isParaLlevar()) {
                        estadosPermitidos.add(EstadoPedido.EN_CAMINO);
                    }
                    estadosPermitidos.add(EstadoPedido.ENTREGADO);
                } else {
                    mensajeRol = "Este estado solo puede ser cambiado por un Mesero o Administrador.";
                }
                break;
            case EN_CAMINO:
                if (empleado instanceof Mesero || empleado instanceof Administrador) {
                    estadosPermitidos.add(EstadoPedido.ENTREGADO);
                } else {
                    mensajeRol = "Este estado solo puede ser cambiado por un Mesero o Administrador.";
                }
                break;
            case ENTREGADO:
            case CANCELADO:
                mensajeRol = "Este pedido ya ha sido " + pedido.getEstado().toString().toLowerCase() + " y no puede cambiar de estado.";
                break;
        }

        if (estadosPermitidos.isEmpty()) {
            view.mostrarMensaje(mensajeRol);
            return null;
        }

        view.mostrarMensaje("Estados disponibles:");
        for (int i = 0; i < estadosPermitidos.size(); i++) {
            view.mostrarMensaje((i + 1) + ". " + estadosPermitidos.get(i));
        }
        view.mostrarMensaje("0. No cambiar el estado");

        int opcion = Integer.parseInt(view.solicitarEntrada("Seleccione el nuevo estado (0 para no cambiar): "));
        if (opcion == 0) {
            view.mostrarMensaje("No se ha realizado ningún cambio en el estado del pedido.");
            return null;
        } else if (opcion > 0 && opcion <= estadosPermitidos.size()) {
            return estadosPermitidos.get(opcion - 1);
        } else {
            view.mostrarMensaje("Opción no válida.");
            return null;
        }
    }

    private void consultarPedidos(Empleado empleado) {
        List<Pedido> pedidos;
        if (empleado instanceof Administrador) {
            pedidos = pedidoService.obtenerTodosPedidos();
        } else if (empleado instanceof Mesero) {
            pedidos = pedidoService.obtenerPedidosPorMesero((Mesero) empleado);
        } else {
            pedidos = pedidoService.obtenerPedidosPendientes();
        }
        view.mostrarPedidosDetallados(pedidos);
    }

    private void procesarPago(Empleado empleado) {
        mostrarListaPedidos();
        try {
            String idPedido = view.solicitarEntrada("ID del pedido a pagar: ");
            Pedido pedido = pedidoService.obtenerPedidoPorId(idPedido);

            if (pedido.getEstado() == EstadoPedido.CANCELADO) {
                view.mostrarMensaje("El pedido tiene estado CANCELADO. No se puede procesar el pago.");
                return;
            }

            if (pedido.isPagado()) {
                view.mostrarMensaje("Este pedido ya ha sido pagado.");
                return;
            }

            view.mostrarMensaje("Total a pagar: S/ " + String.format("%.2f", pedido.getTotal()));

            MetodoPago metodoPago = view.solicitarMetodoPago();

            double montoPagado = 0;
            if (metodoPago == MetodoPago.EFECTIVO) {
                montoPagado = Double.parseDouble(view.solicitarEntrada("Monto con el que paga: S/ "));
                if (montoPagado < pedido.getTotal()) {
                    view.mostrarMensaje("El monto pagado es insuficiente.");
                    return;
                }
            } else {
                montoPagado = pedido.getTotal();
            }

            pagoService.procesarPago(pedido, metodoPago, montoPagado, empleado);

            view.mostrarMensaje("Pago procesado con éxito.");
            if (metodoPago == MetodoPago.EFECTIVO && montoPagado > pedido.getTotal()) {
                double vuelto = montoPagado - pedido.getTotal();
                view.mostrarMensaje("Vuelto: S/ " + String.format("%.2f", vuelto));
            }

            view.mostrarDetallePago(pedido);

        } catch (PedidoNoEncontradoException | IllegalArgumentException | AccesoDenegadoException | IllegalStateException e) {
            view.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private void verEstadisticas() {
        double totalVentas = pedidoService.calcularTotalVentas();
        Map<String, Integer> platosVendidos = pedidoService.obtenerPlatosMasVendidos();
        Map<MetodoPago, Long> estadisticasMetodosPago = pedidoService.obtenerEstadisticasMetodosPago();
        Map<Empleado, Long> empleadosConMasPedidos = pedidoService.obtenerEmpleadosConMasPedidos();
        double tiempoEntregaPromedio = pedidoService.calcularTiempoEntregaPromedio();

        view.mostrarEstadisticas(totalVentas, platosVendidos, estadisticasMetodosPago, empleadosConMasPedidos, tiempoEntregaPromedio);
    }

}
