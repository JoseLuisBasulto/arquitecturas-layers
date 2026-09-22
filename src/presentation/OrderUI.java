package presentation;

import business.OrderService;

public class OrderUI {
    private final OrderService orderService;

    public OrderUI(OrderService orderService) {
        this.orderService = orderService;
    }

}
