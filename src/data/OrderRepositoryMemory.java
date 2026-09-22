package data;

import model.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepositoryMemory implements OrderRepository{
    private final Map<Integer, Order> database = new HashMap<>();

    @Override
    public int save(Order order) {
        if(order == null){
            return 0;
        }

        database.put(order.getId(), order);
        return 1;
    }

    @Override
    public Order searchById(int id) {
        return database.get(id);
    }
}
