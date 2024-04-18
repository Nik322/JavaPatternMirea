package pr8.pr8_2;

// Обработчик запроса
interface Handler {
    void handleRequest(int request);
    void setNextHandler(Handler nextHandler);
}

// Конкретные обработчики
class ConcreteHandler1 implements Handler {
    private Handler nextHandler;

    @Override
    public void handleRequest(int request) {
        if (request < 10) {
            System.out.println("Request " + request + " handled by ConcreteHandler1");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

class ConcreteHandler2 implements Handler {
    private Handler nextHandler;

    @Override
    public void handleRequest(int request) {
        if (request >= 10 && request < 20) {
            System.out.println("Request " + request + " handled by ConcreteHandler2");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

class ConcreteHandler3 implements Handler {
    private Handler nextHandler;

    @Override
    public void handleRequest(int request) {
        if (request >= 20) {
            System.out.println("Request " + request + " handled by ConcreteHandler3");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();

        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        handler1.handleRequest(5);
        handler1.handleRequest(15);
        handler1.handleRequest(25);
    }
}