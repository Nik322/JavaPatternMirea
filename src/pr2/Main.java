package pr2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Human {
    private int age;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int weight;

    public Human(int age, String firstName, String lastName, LocalDate birthDate, int weight) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Human{" +
                "age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", weight=" + weight +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        // Создание списка людей
        List<Human> humans = new ArrayList<>();
        humans.add(new Human(25, "Nik", "Shkabara", LocalDate.of(2005, 3, 5), 70));
        humans.add(new Human(18, "Alice", "legenda228", LocalDate.of(2005, 8, 20), 55));
        humans.add(new Human(30, "Genry", "Hananyev", LocalDate.of(1992, 12, 10), 125));
        humans.add(new Human(22, "Nikita", "Petrovich", LocalDate.of(2000, 3, 25), 60));

        // Вывод списка до сортировки
        System.out.println("Список до сортировки:");
        humans.forEach(System.out::println);

        // Сортировка списка по возрасту
        humans.sort(Comparator.comparingInt(Human::getAge));
        System.out.println("\nСписок после сортировки по возрасту:");
        humans.forEach(System.out::println);

        // Фильтрация по возрасту меньше 20
        List<Human> under20 = humans.stream()
                .filter(human -> human.getAge() < 20)
                .collect(Collectors.toList());
        System.out.println("\nФильтрация по возрасту меньше 20:");
        under20.forEach(System.out::println);

        // Фильтрация по имени, содержащему 'е'
        List<Human> nameContainsE = humans.stream()
                .filter(human -> human.getFirstName().contains("e"))
                .collect(Collectors.toList());
        System.out.println("\nФильтрация по имени, содержащему 'e':");
        nameContainsE.forEach(System.out::println);

        // Конкатенация первых букв имен
        String concatenatedInitials = humans.stream()
                .map(human -> String.valueOf(human.getFirstName().charAt(0)))
                .collect(Collectors.joining());
        System.out.println("\nКонкатенация первых букв имен: " + concatenatedInitials);
    }
}
