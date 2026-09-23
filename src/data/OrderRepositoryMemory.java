package data;

import model.Order;

import java.util.*;

public class OrderRepositoryMemory implements OrderRepository{
    private final Map<Integer, Order> database = new HashMap<>();

    @Override
    public void save(Order order) {
        database.put(order.getId(), order);
    }

    @Override
    public Order searchById(int id) {
        return database.get(id);
    }

    @Override
    public void listOrders() {
        List<Integer> orderedIds = new ArrayList<>(database.keySet());

        Collections.sort(orderedIds);

        for(Integer id : orderedIds){
            System.out.println(database.get(id));
        }
    }
}
