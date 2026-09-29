import business.OrderService;
import data.OrderRepository;
import data.OrderRepositoryFile;
import data.OrderRepositoryMemory;
import presentation.MenuUI;

public class Main {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepositoryFile();
        OrderService service = new OrderService(orderRepository);
        MenuUI menu = new MenuUI(service);

        menu.start();
    }
}