package presentation;

import business.OrderService;
import model.Order;
import model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderUI {

    private final OrderService service;
    private final Scanner scan;

    public OrderUI(OrderService service) {
        this.service = service;
        this.scan = new Scanner(System.in);
    }

    public void registerOrder() {

        try {

            System.out.println("\n=== REGISTRO DE PEDIDO ===");

            System.out.print("ID: ");
            int orderId = scan.nextInt();
            scan.nextLine();

            System.out.print("Cliente: ");
            String customerName = scan.nextLine();

            List<Product> products = new ArrayList<>();

            String answer;

            do {

                System.out.println("\nProducto");

                System.out.print("ID producto: ");
                int productId = scan.nextInt();
                scan.nextLine();

                System.out.print("Nombre: ");
                String productName = scan.nextLine();

                System.out.print("Precio: ");
                BigDecimal price = scan.nextBigDecimal();

                System.out.print("Cantidad: ");
                int quantity = scan.nextInt();

                System.out.print("Existencia: ");
                int stock = scan.nextInt();
                scan.nextLine();

                Product product =
                        new Product(
                                productId,
                                productName,
                                price,
                                quantity,
                                stock
                        );

                products.add(product);

                System.out.print("¿Agregar otro producto? (s/n): ");
                answer = scan.nextLine();

            } while (answer.equalsIgnoreCase("s"));

            Order order =
                    new Order(orderId, customerName, products);

            service.registerOrder(order);

            System.out.println("\nPedido registrado correctamente.");
            waitForEnter();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
            waitForEnter();
        }
    }

    public void searchOrder() {

        try {

            System.out.print("ID del pedido: ");

            int id = scan.nextInt();
            scan.nextLine();

            System.out.println(service.searchOrder(id));
            waitForEnter();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
            waitForEnter();
        }
    }

    public void listOrders() {

        List<Order> orders = service.listOrders();

        if (orders.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            waitForEnter();
            return;
        }

        for (Order order : orders) {
            System.out.println(order);
            waitForEnter();
        }
    }

    public void waitForEnter() {
        System.out.println("Presione enter para continuar...");
        scan.nextLine();
    }
}