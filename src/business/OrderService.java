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

        validateProducts(order.getProductList());
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
        validateOrder(order);

        processOrderCalculations(order);
        order.setOrderState(OrderState.PROCESSED);

        return orderRepository.save(order);
    }

    public void listOrders(){
        orderRepository.findAll();
    }

    public void processOrderCalculations(Order order){
        // Calcular subtotal
        BigDecimal subtotal = BigDecimal.ZERO;
        for(Product product : order.getProductList()){
            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()));
            subtotal = subtotal.add(itemTotal);
        }
        order.setSubtotal(subtotal);

        // Calcular Descuento
        if(order.getSubtotal().compareTo(BigDecimal.valueOf(1000)) > 0){
            order.setDiscount(order.getSubtotal().multiply(BigDecimal.valueOf(0.1)));
        }

        // Calcular Impuestos
        BigDecimal netAmount = order.getSubtotal().subtract(order.getDiscount());
        BigDecimal taxes = netAmount.multiply(BigDecimal.valueOf(0.16));
        order.setTaxes(taxes);

        // Calcular Total
        order.setTotal(netAmount.add(taxes));
    }
}
