package pr7.pr7_1;

// Интерфейс сервиса
interface Service {
    void operation();
}

// Конкретная реализация сервиса
class RealService implements Service {
    @Override
    public void operation() {
        System.out.println("RealService: Performing operation");
    }
}

// Прокси для сервиса
class Proxy implements Service {
    private RealService realService;

    @Override
    public void operation() {
        if (realService == null) {
            realService = new RealService();
        }
        realService.operation();
    }
}

// Пример использования
public class ProxyExample {
    public static void main(String[] args) {
        Service service = new Proxy();
        service.operation();
    }
}
