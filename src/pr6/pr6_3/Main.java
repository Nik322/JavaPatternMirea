package pr6.pr6_3;

// Продукт, который строится
class Product {
    private String part1;
    private String part2;

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public void show() {
        System.out.println("Part1: " + part1);
        System.out.println("Part2: " + part2);
    }
}

// Интерфейс строителя
interface Builder {
    void buildPart1();
    void buildPart2();
    Product getResult();
}

// Конкретная реализация строителя
class ConcreteBuilder implements Builder {
    private Product product = new Product();

    @Override
    public void buildPart1() {
        product.setPart1("Part1 built");
    }

    @Override
    public void buildPart2() {
        product.setPart2("Part2 built");
    }

    @Override
    public Product getResult() {
        return product;
    }
}

// Руководитель строительства
class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildPart1();
        builder.buildPart2();
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
        Product product = builder.getResult();
        product.show();
    }
}

