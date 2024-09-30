package com.mixturaperuana.pe.view;

import com.mixturaperuana.pe.enums.MetodoPago;
import com.mixturaperuana.pe.enums.Rol;
import com.mixturaperuana.pe.model.Empleado;
import com.mixturaperuana.pe.model.Pedido;
import com.mixturaperuana.pe.model.Plato;
import com.mixturaperuana.pe.utils.DateUtils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;
    private final NumberFormat formatoMoneda;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
        this.formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "PE"));
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String solicitarEntrada(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void mostrarMenu(Rol rol) {
        System.out.println("\n--- 📋 Sistema de Gestión de Pedidos ---");
        switch (rol) {
            case MESERO:
                System.out.println("1️⃣ Registrar Pedido");
                System.out.println("2️⃣ Confirmar o Cancelar Pedido");
                System.out.println("3️⃣ Actualizar Estado de Pedido (En camino/Entregado)");
                System.out.println("4️⃣ Consultar Pedidos");
                break;
            case ASISTENTE_COCINA:
            case CHEF:
                System.out.println("1️⃣ Actualizar Estado de Pedido");
                System.out.println("2️⃣ Consultar Pedidos");
                break;
            case CAJERO:
                System.out.println("1️⃣ Consultar Pedidos");
                System.out.println("2️⃣ Procesar Pago");
                break;
            case ADMINISTRADOR:
                System.out.println("1️⃣ Registrar Pedido");
                System.out.println("2️⃣ Actualizar Estado de Pedido");
                System.out.println("3️⃣ Consultar Pedidos");
                System.out.println("4️⃣ Procesar Pago");
                System.out.println("5️⃣ Ver Estadísticas");
                break;
        }
        System.out.println("0️⃣ Cerrar Sesión");
    }

    public void mostrarPlatosDisponibles(List<Plato> platos) {
        System.out.println("\n--- 🍽️ Platos Disponibles ---");
        System.out.printf("%-5s %-30s %-15s %-40s%n", "ID", "Nombre", "Precio", "Descripción");
        System.out.println("-------------------------------------------------------------------------------------");
        for (Plato plato : platos) {
            System.out.printf("%-5d %-30s %-15s %-40s%n",
                    plato.getIdPlato(),
                    plato.getNombre(),
                    formatoMoneda.format(plato.getPrecio()),
                    plato.getDescripcion());
        }
    }

    public void mostrarPedidosDetallados(List<Pedido> pedidos) {
        System.out.println("\n--- 📦 Pedidos Detallados ---");
        System.out.printf("%-10s %-20s %-15s %-20s %-15s %-10s %-20s %-20s%n",
                "ID", "Cliente", "Total", "Estado", "Registrado Por", "Para Llevar", "Tiempo Entrega (min)", "Fecha");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        for (Pedido pedido : pedidos) {
            System.out.printf("%-10s %-20s %-15s %-20s %-15s %-10s %-20s %-20s%n",
                    pedido.getIdPedido(),
                    pedido.getCliente().getNombre(),
                    formatoMoneda.format(pedido.getTotal()),
                    pedido.getEstado(),
                    pedido.getRegistradoPor().getNombre(),
                    pedido.isParaLlevar() ? "Sí" : "No",
                    pedido.isParaLlevar() ? pedido.getTiempoEntrega() : "N/A",
                    DateUtils.formatDateTime(pedido.getFechaPedido()));

            System.out.println("  Platos:");
            for (Plato plato : pedido.getPlatos()) {
                System.out.printf("  - %-30s %s%n", plato.getNombre(), formatoMoneda.format(plato.getPrecio()));
            }
            if (pedido.isParaLlevar()) {
                System.out.println("  Dirección de entrega: " + pedido.getDireccionEntrega());
            }
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void mostrarEstadisticas(double totalVentas, Map<String, Integer> platosVendidos,
                                    Map<MetodoPago, Long> estadisticasMetodosPago,
                                    Map<Empleado, Long> empleadosConMasPedidos,
                                    double tiempoEntregaPromedio) {
        System.out.println("\n--- 📊 Estadísticas ---");
        System.out.println("Total de Ventas: " + formatoMoneda.format(totalVentas));

        System.out.println("\nPlatos más vendidos:");
        platosVendidos.forEach((plato, cantidad) ->
                System.out.printf("%-30s %d%n", plato, cantidad));

        System.out.println("\nMétodos de pago utilizados:");
        estadisticasMetodosPago.forEach((metodo, cantidad) ->
                System.out.printf("%-20s %d%n", metodo, cantidad));

        System.out.println("\nEmpleados con más pedidos registrados:");
        empleadosConMasPedidos.forEach((empleado, cantidad) ->
                System.out.printf("%-30s %d%n", empleado.getNombre() + " " + empleado.getApellido(), cantidad));

        System.out.printf("\nTiempo de entrega promedio: %.2f minutos%n", tiempoEntregaPromedio);
    }

    public void mostrarDetallePago(Pedido pedido) {
        System.out.println("\n--- Detalle de Pago ---");
        System.out.println("ID Pedido: " + pedido.getIdPedido());
        System.out.println("Total: S/ " + String.format("%.2f", pedido.getTotal()));
        System.out.println("Método de Pago: " + pedido.getMetodoPago());
        System.out.println("Monto Pagado: S/ " + String.format("%.2f", pedido.getMontoPagado()));
        if (pedido.getMetodoPago() == MetodoPago.EFECTIVO) {
            System.out.println("Vuelto: S/ " + String.format("%.2f", pedido.getVuelto()));
        }
        System.out.println("Estado: " + pedido.getEstado());
    }

    public MetodoPago solicitarMetodoPago() {
        System.out.println("Seleccione el método de pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta de Crédito");
        System.out.println("3. Tarjeta de Débito");
        System.out.println("4. Transferencia");
        System.out.println("5. Yape");
        System.out.println("6. Plin");
        System.out.println("7. Tunki");

        int opcion = Integer.parseInt(solicitarEntrada("Ingrese el número de la opción: "));
        switch (opcion) {
            case 1: return MetodoPago.EFECTIVO;
            case 2: return MetodoPago.TARJETA_CREDITO;
            case 3: return MetodoPago.TARJETA_DEBITO;
            case 4: return MetodoPago.TRANSFERENCIA;
            case 5: return MetodoPago.YAPE;
            case 6: return MetodoPago.PLIN;
            case 7: return MetodoPago.TUNKI;
            default: throw new IllegalArgumentException("Opción no válida");
        }
    }

    public void mostrarListaPedidos(List<Pedido> pedidos) {
        System.out.println("\n--- 📋 Lista de Pedidos ---");
        System.out.printf("%-10s %-20s %-15s %-20s %-15s%n", "ID", "Cliente", "Total", "Estado", "Fecha");
        System.out.println("--------------------------------------------------------------------------------");
        for (Pedido pedido : pedidos) {
            System.out.printf("%-10s %-20s %-15s %-20s %-15s%n",
                    pedido.getIdPedido(),
                    pedido.getCliente().getNombre(),
                    formatoMoneda.format(pedido.getTotal()),
                    pedido.getEstado(),
                    pedido.getFechaPedido().toLocalDate());
        }
        System.out.println("--------------------------------------------------------------------------------");
    }
}
