import business.OrderService;
import data.OrderRepository;
import data.OrderRepositoryMemory;
import model.OrderState;
import presentation.MenuUI;
import presentation.OrderUI;

public class Main {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepositoryMemory();
        OrderService service = new OrderService(orderRepository);
        MenuUI menu = new MenuUI(service);

        menu.start();
    }
}