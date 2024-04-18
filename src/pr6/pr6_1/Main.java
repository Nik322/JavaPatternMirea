package pr6.pr6_1;

// Интерфейс для создания продуктов
interface Product {
    void operate();
}

// Конкретные реализации продуктов
class ConcreteProduct1 implements Product {
    @Override
    public void operate() {
        System.out.println("Operating ConcreteProduct1");
    }
}

class ConcreteProduct2 implements Product {
    @Override
    public void operate() {
        System.out.println("Operating ConcreteProduct2");
    }
}

// Интерфейс фабрики
interface Factory {
    Product createProduct();
}

// Конкретные реализации фабрик
class ConcreteFactory1 implements Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProduct1();
    }
}

class ConcreteFactory2 implements Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProduct2();
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        Factory factory1 = new ConcreteFactory1();
        Product product1 = factory1.createProduct();
        product1.operate();

        Factory factory2 = new ConcreteFactory2();
        Product product2 = factory2.createProduct();
        product2.operate();
    }
}
