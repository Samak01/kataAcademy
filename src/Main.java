import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String expression = scanner.nextLine();

        String result = calc(expression);
        System.out.println(result);
    }

    public static String calc(String input) {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "*", "/"};
        String[] regActions = {"\\+", "-", "\\*", "/"};

        int actionInd = -1;
        for (int i = 0; i < actions.length; i++){
            if (input.contains(actions[i])){
                actionInd = i;
                break;
            }
        }


        if (actionInd == -1){
            return "Неподдерживаемая операция";
        }

        String[] data = input.split(regActions[actionInd]);

        if (data.length > 2){
            return "Не вводите больше двух чисел!!";

        }
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int a, b;
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman){
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            if ((a <= 1 || a >= 10 || b <= 1 || b >= 10) ){
                if (isRoman){
                    return "Вводите числа от I до X!";
                } else {
                    return "Вводите числа от 1 до 10!";
                }
            }

            int result = 0;
            switch (actions[actionInd]){
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b == 0){
                        return "Ошибка: деление на ноль";
                    }
                    result = a / b;
                    break;
                default:
            }
            if (isRoman && result < 0){
                return "Результат римских чисел дольжен быть положительным!!";
            }
            return isRoman ? converter.intToRoman(result) : String.valueOf(result);
        } else {
            return "Вводите числа в одном формате";
        }
    }

    // Класс Converter здесь остается без изменений
    public static class Converter {
        TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
        TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();

        public Converter() {
            romanKeyMap.put('I', 1);
            romanKeyMap.put('V', 5);
            romanKeyMap.put('X', 10);
            romanKeyMap.put('L', 50);
            romanKeyMap.put('C', 100);
            romanKeyMap.put('D', 500);
            romanKeyMap.put('M', 1000);

            arabianKeyMap.put(1000, "M");
            arabianKeyMap.put(900, "CM");
            arabianKeyMap.put(500, "D");
            arabianKeyMap.put(400, "CD");
            arabianKeyMap.put(100, "C");
            arabianKeyMap.put(90, "XC");
            arabianKeyMap.put(50, "L");
            arabianKeyMap.put(40, "XL");
            arabianKeyMap.put(10, "X");
            arabianKeyMap.put(9, "IX");
            arabianKeyMap.put(5, "V");
            arabianKeyMap.put(4, "IV");
            arabianKeyMap.put(1, "I");

        }


        public boolean isRoman(String number) {
            return romanKeyMap.containsKey(number.charAt(0));
        }

        public String intToRoman(int number) {
            String roman = "";
            int arabianKey;
            do {
                arabianKey = arabianKeyMap.floorKey(number);
                roman += arabianKeyMap.get(arabianKey);
                number -= arabianKey;
            } while (number != 0);
            return roman;


        }


        public int romanToInt(String s) {
            int end = s.length() - 1;
            char[] arr = s.toCharArray();
            int arabian;
            int result = romanKeyMap.get(arr[end]);
            for (int i = end - 1; i >= 0; i--) {
                arabian = romanKeyMap.get(arr[i]);

                if (arabian < romanKeyMap.get(arr[i + 1])) {
                    result -= arabian;
                } else {
                    result += arabian;
                }


            }
            return result;

        }

    }
}
