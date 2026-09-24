import business.OrderService;
import data.OrderRepository;
import data.OrderRepositoryMemory;
import model.OrderState;
import presentation.OrderUI;

public class Main {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepositoryMemory();
        OrderService orderService = new OrderService(orderRepository);
        OrderUI orderUI= new OrderUI(orderService);

        System.out.println(OrderState.PENDING);

        // Funciones de la clase orderUI
    }
}