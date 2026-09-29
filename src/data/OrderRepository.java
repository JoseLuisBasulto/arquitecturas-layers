package data;

import model.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);
    Order searchById(int id);
    List<Order> findAll();
}
