package data;

import model.Order;

public interface OrderRepository {
    Order save(Order order);
    Order searchById(int id);
    void findAll();
}
