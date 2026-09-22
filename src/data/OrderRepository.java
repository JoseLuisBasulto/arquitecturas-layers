package data;

import model.Order;

public interface OrderRepository {
    int save(Order order);
    Order searchById(int id);
}
