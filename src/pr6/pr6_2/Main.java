package pr6.pr6_2;

// Интерфейс для создания продуктов первого типа
interface AbstractProductA {
    void operate();
}

// Конкретные реализации продуктов первого типа
class ConcreteProductA1 implements AbstractProductA {
    @Override
    public void operate() {
        System.out.println("Operating ConcreteProductA1");
    }
}

class ConcreteProductA2 implements AbstractProductA {
    @Override
    public void operate() {
        System.out.println("Operating ConcreteProductA2");
    }
}

// Интерфейс для создания продуктов второго типа
interface AbstractProductB {
    void operate();
}

// Конкретные реализации продуктов второго типа
class ConcreteProductB1 implements AbstractProductB {
    @Override
    public void operate() {
        System.out.println("Operating ConcreteProductB1");
    }
}

class ConcreteProductB2 implements AbstractProductB {
    @Override
    public void operate() {
        System.out.println("Operating ConcreteProductB2");
    }
}

// Интерфейс абстрактной фабрики
interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}

// Конкретные реализации абстрактной фабрики
class ConcreteFactory1 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ConcreteProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ConcreteProductB1();
    }
}

class ConcreteFactory2 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ConcreteProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ConcreteProductB2();
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        AbstractFactory factory1 = new ConcreteFactory1();
        AbstractProductA productA1 = factory1.createProductA();
        AbstractProductB productB1 = factory1.createProductB();
        productA1.operate();
        productB1.operate();

        AbstractFactory factory2 = new ConcreteFactory2();
        AbstractProductA productA2 = factory2.createProductA();
        AbstractProductB productB2 = factory2.createProductB();
        productA2.operate();
        productB2.operate();
    }
}
