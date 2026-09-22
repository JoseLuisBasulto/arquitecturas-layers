import business.OrderService;
import data.OrderRepository;
import data.OrderRepositoryMemory;
import presentation.OrderUI;

public class Main {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepositoryMemory();
        OrderService orderService = new OrderService(orderRepository);
        OrderUI orderUI= new OrderUI(orderService);

        // Funciones de la clase orderUI
    }
}