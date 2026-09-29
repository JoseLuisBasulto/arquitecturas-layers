package data;

import model.Order;
import model.OrderState;
import model.Product;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryFile implements OrderRepository {

    private static final String FILE_NAME = "src/data/orders.txt";

    @Override
    public Order save(Order order) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            writer.write("ORDER");
            writer.newLine();

            writer.write(String.valueOf(order.getId()));
            writer.newLine();

            writer.write(order.getCustomerName());
            writer.newLine();

            writer.write(order.getOrderState().name());
            writer.newLine();

            writer.write(order.getSubtotal().toString());
            writer.newLine();

            writer.write(order.getDiscount().toString());
            writer.newLine();

            writer.write(order.getTaxes().toString());
            writer.newLine();

            writer.write(order.getTotal().toString());
            writer.newLine();

            writer.write(
                    String.valueOf(
                            order.getProductList().size()
                    )
            );
            writer.newLine();

            for (Product product : order.getProductList()) {

                writer.write(
                        product.getId() + ";" +
                                product.getName() + ";" +
                                product.getPrice() + ";" +
                                product.getQuantity() + ";" +
                                product.getStock()
                );

                writer.newLine();
            }

            writer.write("END");
            writer.newLine();

        } catch (IOException e) {

            throw new RuntimeException("Error al guardar pedido.", e);
        }

        return order;
    }

    @Override
    public Order searchById(int id) {

        List<Order> orders = findAll();

        for (Order order : orders) {

            if (order.getId() == id) {
                return order;
            }
        }

        return null;
    }

    @Override
    public List<Order> findAll() {

        List<Order> orders = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return orders;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (!line.equals("ORDER")) {
                    continue;
                }

                int id = Integer.parseInt(reader.readLine());

                String customerName = reader.readLine();

                OrderState state = OrderState.valueOf(reader.readLine());

                BigDecimal subtotal = new BigDecimal(reader.readLine());

                BigDecimal discount = new BigDecimal(reader.readLine());

                BigDecimal taxes = new BigDecimal(reader.readLine());

                BigDecimal total = new BigDecimal(reader.readLine());

                int productCount = Integer.parseInt(reader.readLine());

                List<Product> products = new ArrayList<>();

                for (int i = 0; i < productCount; i++) {

                    String[] productData =
                            reader.readLine()
                                    .split(";");

                    Product product =
                            new Product(
                                    Integer.parseInt(productData[0]),
                                    productData[1],
                                    new BigDecimal(productData[2]),
                                    Integer.parseInt(productData[3]),
                                    Integer.parseInt(productData[4])
                            );

                    products.add(product);
                }

                reader.readLine();

                Order order = new Order(id, customerName, products);

                order.setOrderState(state);
                order.setSubtotal(subtotal);
                order.setDiscount(discount);
                order.setTaxes(taxes);
                order.setTotal(total);

                orders.add(order);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al leer pedidos.", e);
        }

        return orders;
    }
}