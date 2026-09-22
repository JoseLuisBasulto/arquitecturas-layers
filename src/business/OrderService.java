package business;

import data.OrderRepository;
import model.Order;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public void validateOrder(Order order){

    }

    public Order registerOrder(Order order){
        return null;
    }

    public Order searhOrder(int id){
        return null;
    }

}
