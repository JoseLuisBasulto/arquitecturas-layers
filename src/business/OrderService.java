package business;

import data.OrderRepository;
import model.Order;
import model.OrderState;
import model.Product;

import java.math.BigDecimal;
import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public void validateOrder(Order order){
        if(order == null){
            throw new IllegalArgumentException("El pedido no existe.");
        }

        if(order.getCustomerName() == null){
            throw new IllegalArgumentException("El pedido no cuenta con cliente.");
        }

        if(order.getProductList() == null || order.getProductList().isEmpty()){
            throw new IllegalArgumentException("El pedido no cuenta con productos.");
        }
    }

    public void validateProducts(List<Product> productList){
        for(Product product : productList){
            if(product.getQuantity() > product.getStock()){
                throw new IllegalStateException("La cantidad solicitada es mayor a la disponible.");
            }

            if(product.getQuantity() < 0){
                throw new IllegalArgumentException("La cantidad solicitada de un producto debe ser mayor que 0.");
            }
        }
    }

    public Order searchOrder(int id){
        Order order = orderRepository.searchById(id);

        if(order == null){
            throw new IllegalArgumentException("Pedido no encontrado con ID: " + id);
        }

        return order;
    }

    public Order registerOrder(Order order){
        int orderId = order.getId();

        validateOrder(order);
        validateProducts(order.getProductList());

        calculateSubtotal(orderId);
        calculateDiscount(orderId);
        calculateTaxes(orderId);
        calculateTotal(orderId);

        order.setOrderState(OrderState.PROCESSED);
        orderRepository.save(order);

        return orderRepository.searchById(orderId);
    }

    public void listOrders(){
        System.out.println("Pedidos realizados:");
        orderRepository.listOrders();
    }

    public void calculateSubtotal(int id){
        Order order = searchOrder(id);
        List<Product> productList = order.getProductList();

        BigDecimal subtotal = BigDecimal.ZERO;
        for(Product product : productList){
            subtotal = product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()));
        }

        order.setSubtotal(subtotal);
    }

    public void calculateDiscount(int id){
        Order order = searchOrder(id);
        int amount = 1000;
        BigDecimal decimalDiscount = BigDecimal.valueOf(0.1);

        if(order.getSubtotal().compareTo(BigDecimal.valueOf(amount)) > 0){
            order.setDiscount(order.getSubtotal().multiply(decimalDiscount));
        }
    }

    public void calculateTaxes(int id){
        Order order = searchOrder(id);
        double iva = 0.16;

        order.setTaxes(order.getSubtotal().subtract(order.getDiscount()).multiply(BigDecimal.valueOf(iva)));
    }

    public void calculateTotal(int id){
        Order order = searchOrder(id);
        order.setTotal(order.getSubtotal().subtract(order.getDiscount()).add(order.getTaxes()));
    }
}
