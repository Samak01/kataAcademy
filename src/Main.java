import java.util.*;

class Main {
    private static final Map<String, Integer> ROMAN_NUMERALS = new HashMap<>();

    static {
        ROMAN_NUMERALS.put("I", 1);
        ROMAN_NUMERALS.put("II", 2);
        ROMAN_NUMERALS.put("III", 3);
        ROMAN_NUMERALS.put("IV", 4);
        ROMAN_NUMERALS.put("V", 5);
        ROMAN_NUMERALS.put("VI", 6);
        ROMAN_NUMERALS.put("VII", 7);
        ROMAN_NUMERALS.put("VIII", 8);
        ROMAN_NUMERALS.put("IX", 9);
        ROMAN_NUMERALS.put("X", 10);
        ROMAN_NUMERALS.put("XI", 11);
        ROMAN_NUMERALS.put("XII", 12);
        ROMAN_NUMERALS.put("XIII", 13);
        ROMAN_NUMERALS.put("XIV", 14);
        ROMAN_NUMERALS.put("XV", 15);
        ROMAN_NUMERALS.put("XVI", 16);
        ROMAN_NUMERALS.put("XVII", 17);
        ROMAN_NUMERALS.put("XVIII", 18);
        ROMAN_NUMERALS.put("XIX", 19);
        ROMAN_NUMERALS.put("XX", 20);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Введите выражение через пробель (a + b): ");
                String input = scanner.nextLine();


                String result = calc(input);
                System.out.println("Результь: " + result);
            } catch (IllegalArgumentException e) { // Java.lang.IllegalArgumentException — это исключение, которое возникает в языке программирования Java, когда переданный аргумент методу или конструктору является недопустимым или некорректным. Такое исключение может возникнуть, когда аргумент имеет неправильное значение, неверный формат или не отвечает ожидаемым требованиям.
                System.out.println("Ошибка: " + e.getMessage());
                break;
            }
        }

        System.out.println("Программа завершена.");
    }

    static String calc(String input) {
        String[] tokens = input.split("\\s+");
        boolean isRoman = isRomanNumber(tokens[0]) && isRomanNumber(tokens[2]);
        int num1 = parseNumber(tokens[0], isRoman);
        int num2 = parseNumber(tokens[2], isRoman);
        String operator = tokens[1];
        int result;

        if ((num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) && !isRoman) {
            throw new IllegalArgumentException("Допустимы только числа от 1 до 10 включительно.");
        }

        if ((num1 >= 1 && num1 <= 10 && num2 >= 1 && num2 <= 10) && isRoman) {
            throw new IllegalArgumentException("Нельзя выполнять операции между арабскими и римскими числами одновременно.");
        }

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2; // целочисленное деление
                } else {
                    throw new IllegalArgumentException("Ошибка: деление на ноль");
                }
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция");
        }

        if (isRoman && result <= 0) {
            throw new IllegalArgumentException("Результат работы с римскими числами должен быть положительным.");
        }

        return isRoman ? toRoman(result) : String.valueOf(result);
    }

    private static boolean isRomanNumber(String input) {
        return ROMAN_NUMERALS.containsKey(input.toUpperCase());
    }

    private static int parseNumber(String input, boolean isRoman) {
        try {
            if (isRoman) {
                return ROMAN_NUMERALS.get(input.toUpperCase()); //Метод Java String toUpperCase() преобразует строку в верхний регистр.
            } else {
                int number = Integer.parseInt(input);
                if (number < 1 || number > 10) {
                    throw new IllegalArgumentException("Допустимы только числа от 1 до 10 включительно.");
                }
                return number;
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Не удалось распознать число: " + input);
        }
    }

    private static String toRoman(int number) {
        if (number < 1 || number > 10) {
            throw new IllegalArgumentException("Допустимы только числа от 1 до 10 включительно.");
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : ROMAN_NUMERALS.entrySet()) {
            while (number >= entry.getValue()) {
                result.append(entry.getKey());
                number -= entry.getValue();
            }
        }
        return result.toString();
    }
}
