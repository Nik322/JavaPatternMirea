package pr7.pr7_2;

import java.util.ArrayList;
import java.util.List;

// Компонент
interface Component {
    void operation();
}

// Листовой узел
class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("Leaf: " + name + " - Performing operation");
    }
}

// Контейнерный узел
class Composite implements Component {
    private List<Component> children = new ArrayList<>();

    public void add(Component component) {
        children.add(component);
    }

    public void remove(Component component) {
        children.remove(component);
    }

    @Override
    public void operation() {
        System.out.println("Composite - Performing operation on children:");
        for (Component component : children) {
            component.operation();
        }
    }
}

// Пример использования
public class CompositeExample {
    public static void main(String[] args) {
        Leaf leaf1 = new Leaf("Leaf 1");
        Leaf leaf2 = new Leaf("Leaf 2");
        Leaf leaf3 = new Leaf("Leaf 3");

        Composite composite1 = new Composite();
        composite1.add(leaf1);
        composite1.add(leaf2);

        Composite composite2 = new Composite();
        composite2.add(leaf3);

        Composite composite = new Composite();
        composite.add(composite1);
        composite.add(composite2);

        composite.operation();
    }
}