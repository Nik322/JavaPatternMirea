package pr6.pr6_4;

// Интерфейс прототипа
interface Prototype {
    Prototype clone();
    void showInfo();
}

// Конкретные реализации прототипа
class ConcretePrototype implements Prototype {
    private String info;

    public ConcretePrototype(String info) {
        this.info = info;
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype(this.info);
    }

    @Override
    public void showInfo() {
        System.out.println("Info: " + info);
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        ConcretePrototype prototype = new ConcretePrototype("Initial");
        prototype.showInfo();

        ConcretePrototype clonedPrototype = (ConcretePrototype) prototype.clone();
        clonedPrototype.showInfo();
    }
}
