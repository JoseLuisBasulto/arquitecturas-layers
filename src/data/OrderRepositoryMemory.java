package data;

import model.Order;

import java.util.*;

public class OrderRepositoryMemory implements OrderRepository{
    private final Map<Integer, Order> database = new HashMap<>();

    @Override
    public Order save(Order order) {
        database.put(order.getId(), order);
        return searchById(order.getId());
    }

    @Override
    public Order searchById(int id) {
        return database.get(id);
    }

    @Override
    public List<Order> findAll() {

        List<Integer> orderedIds =
                new ArrayList<>(database.keySet());

        Collections.sort(orderedIds);

        List<Order> orders = new ArrayList<>();

        for(Integer id : orderedIds) {
            orders.add(database.get(id));
        }

        return orders;
    }
}
