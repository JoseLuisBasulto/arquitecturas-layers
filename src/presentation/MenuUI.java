package presentation;

import business.OrderService;

import java.util.Scanner;

public class MenuUI {

    private final Scanner scanner;
    private final OrderUI orderUI;

    public MenuUI(OrderService service) {

        scanner = new Scanner(System.in);
        orderUI = new OrderUI(service);
    }

    public void start() {

        int selection;

        do {

            System.out.println("\n===== SISTEMA DE PEDIDOS =====");
            System.out.println("1. Registrar pedido");
            System.out.println("2. Consultar pedido");
            System.out.println("3. Listar pedidos");
            System.out.println("4. Salir");

            System.out.print("\nSelección: ");
            selection = scanner.nextInt();

            switch (selection) {

                case 1: orderUI.registerOrder(); break;
                case 2: orderUI.searchOrder(); break;
                case 3: orderUI.listOrders(); break;
                case 4: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción inválida.");
            }

        } while (selection != 4);
    }
}