package pr1;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        // Пример строки для обработки
        String inputString = "examplestring";

        // Создание экземпляра класса, реализующего интерфейс Consumer
        Consumer<String> replaceEveryThirdChar = str -> {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                if ((i + 1) % 3 == 0) {
                    result.append(Character.toUpperCase(str.charAt(i)));
                } else {
                    result.append(str.charAt(i));
                }
            }
            System.out.println(result.toString());
        };

        // Применение созданного Consumer к входной строке
        replaceEveryThirdChar.accept(inputString);
    }
}
