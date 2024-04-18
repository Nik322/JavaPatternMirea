package pr8.pr8_1;

// Интерфейс состояния
interface State {
    void handle();
}

// Конкретные реализации состояний
class ConcreteState1 implements State {
    @Override
    public void handle() {
        System.out.println("Handling in ConcreteState1");
    }
}

class ConcreteState2 implements State {
    @Override
    public void handle() {
        System.out.println("Handling in ConcreteState2");
    }
}

// Класс, который содержит состояние
class Context {
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public void request() {
        state.handle();
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        Context context = new Context();

        State state1 = new ConcreteState1();
        context.setState(state1);
        context.request();

        State state2 = new ConcreteState2();
        context.setState(state2);
        context.request();
    }
}