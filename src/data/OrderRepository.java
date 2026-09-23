package data;

import model.Order;

public interface OrderRepository {
    void save(Order order);
    Order searchById(int id);
    void listOrders();
}
