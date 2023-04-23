import org.jetbrains.annotations.NotNull;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//            Task1();
//            Task2();
//            Task3();
//            Task4();
//            Task5();
            Task6();
//            Task7();
        }
    static Scanner scanner = new Scanner(System.in);
    public static @NotNull String readString(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    public static @NotNull Integer readInt(String question) {
        System.out.print(question);
        return scanner.nextInt();
    }

    public static @NotNull Float readFloat(String question) {
        System.out.print(question);
        return scanner.nextFloat();
    }

    public static void Task1() {
        //Напишете програма, която изчислява реалните корени на
        //квадратно уравнение по дадени коефициенти a, b и c. Прочетете
        //коефициентите от конзолата (цели числа).
        //Как се намират корените на квадратно уравнение:
        //1. Намираме дискриминатата D = b*b - 4*a*c
        //a. ако D>0 - продължаваме с 2.
        //b. ако D=0 имаме два еднакви корена, продължаваме с 2.
        //c. ако D<0 нямаме реални корени решения
        //2. Намираме двата корена по формулата
        //x1 = (-b + Math.sqrt(D))/(2 * a) ; x2 = (-b - Math.sqrt(D))/(2 * a)
        //Ако няма реални корени - принирайте “There are not real roots”.
        //Първо принтирайте резултатът за х1, после за х2.

        int a = readInt("Въведете A: ");
        int b = readInt("Въведете B: ");
        int c = readInt("Въведете C: ");

        int d = b*b - 4*a*c;

        if (d<0) {
            System.out.println("There are not real roots");
        } else {
            double x1 = (-b + Math.sqrt(d))/(2 * a);
            double x2 = (-b - Math.sqrt(d))/(2 * a);
            System.out.println("X1 = " + x1);
            System.out.println("X2 = " + x2);
        }
    }

    public static void Task2(){
        //Прочетете числото (цяло) n. Принтирайте абсолютната стойност на
        //разликата на n и 21. Освен ако х (резултатът от разликата им) е поголямо от 21, тогава
        // принтирайте абсолютната им разлика по две.
        //(19) → 2 19 - 21 = -2 -> |-2| = 2
        //(10) → 11 10 - 21 = -11 -> |-11| = 11
        //(21) → 0 21 - 21 = 0 -> |0| = 0
        //(43) → 44 43 - 21 = 22 -> 22 * 2 = 44
        //(-100) → 121 -100 - 21 = -121 -> |-121| = 121

        int n = readInt("Въведете цяло число: ");

        int diff = n - 21 ;
        int diff_abs = Math.abs(diff);

        if (diff > 21) {
            System.out.println("Резултат: " + diff_abs * 2);
        } else {
            System.out.println("Резултат: " + diff_abs);
        }

        Task2();
    }

    public static void Task3() {
        //Напишете програма, която прилага бонус точки към дадени точки в
        //интервала [1..9] чрез прилагане на следните правила:
        //-     Ако точките са между 1 и 3, програмата ги умножава по 10.
        //-     Ако точките са между 4 и 6, ги умножава по 100.
        //-     Ако точките са между 7 и 9, ги умножава по 1000.
        //-     Ако точките са 0 или повече от 9, се отпечатва съобщение за грешка.
        //Прочетете първоначалните от конзолата (цяло число между 1 и 9), изчислете
        //бонус точките, прибавете ги към първоначалните и принтирайте резултатът.

        int n = readInt("Въведете броят точки [1..9]: ");

        switch (n) {
            case 1, 2, 3:
                System.out.println("Резултат: " + n * 10); break;
            case 4, 5, 6:
                System.out.println("Резултат: " + n * 100); break;
            case 7, 8, 9:
                System.out.println("Резултат: " + n * 1000); break;
            default:
                System.out.println("Грешен избор. Моля въведете число между 1 и 9.");
        }

        Task3();
    }

    public static void Task4() {
        //Дадени са ни две цели числа a и b. Имаме и число n, което се въвежда от
        //конзолата, след а и b (отново цяло число), то е номера на избраната опция.
        //Опциите са:
        //1. а + b; 3. b – 1; 5. a / b;
        //2. a – b; 4. a * b; 6. b / a;
        //Напишете програма, която при избирането на опция да изкарва на конзолата
        //резултата от нея.
        //Ако се избере несъществуваща опция - принтирайте “Invalid option”.

        int a = 5;
        int b = 8;

        System.out.println("1. " + a + " + " + b);
        System.out.println("2. " + a + " - " + b);
        System.out.println("3. " + b + " - 1");
        System.out.println("4. " + a + " * " + b);
        System.out.println("5. " + a + " / " + b);
        System.out.println("6. " + b + " / " + a);

        int n = readInt("Изберете операцията която искате да извършите: ");

        // No need for break statement with the arrow syntax
        switch (n) {
            case 1 -> System.out.println("Резултат: " + (a + b));
            case 2 -> System.out.println("Резултат: " + (a - b));
            case 3 -> System.out.println("Резултат: " + (b - 1));
            case 4 -> System.out.println("Резултат: " + (a * b));
            case 5 -> System.out.println("Резултат: " + ((float)a / b));
            case 6 -> System.out.println("Резултат: " + ((float)b / a));
            default -> System.out.println("Invalid option");
        }

        Task4();
    }

    public static void Task5() {
        //Да се напише програма, която по въведена оценка в училище (от 2 до 6)
        //отпечатва на екрана съответната оценка с думи. Ако въведеното число не е
        //валидна оценка - да се отпечата подходящо съобщение за грешка.

        int score = readInt("Въведете оценка [2..6]: ");

        switch (score) {
            case 2 -> System.out.println("Въведохте: Двойка");
            case 3 -> System.out.println("Въведохте: Тройка");
            case 4 -> System.out.println("Въведохте: Четворка");
            case 5 -> System.out.println("Въведохте: Петица");
            case 6 -> System.out.println("Въведохте: Шестица");
            default -> System.out.println("Невалидна оценка. Трябва да бъде между 2 и 6 (в България).");
        }

        Task5();
    }

    public static String getSingles(int n) {
        String to_return = "";

        switch (n) {
            case 0 -> to_return = "Zero";
            case 1 -> to_return = "One";
            case 2 -> to_return = "Two";
            case 3 -> to_return = "Three";
            case 4 -> to_return = "Four";
            case 5 -> to_return = "Five";
            case 6 -> to_return = "Six";
            case 7 -> to_return = "Seven";
            case 8 -> to_return = "Eight";
            case 9 -> to_return = "Nine";
        }
        return to_return;
    }
    public static String getDoubles(int n){
        // Solves 100/200.. etc.
        if (n == 0) {
            return "";
        }

        int len = Integer.toString(n).length();
        if (len == 1) {
            // len will be 1 when the number ends on 01,02 etc... (101,102..)
            return getSingles(n);
        }

        String to_return = "";

        int n_1 = Integer.parseInt(Integer.toString(n).substring(0, 1));
        int n_2 = Integer.parseInt(Integer.toString(n).substring(1, 2));

        if (n_1 == 1) {

            switch (n) {
                case 10 -> to_return = "Ten";
                case 11 -> to_return = "Eleven";
                case 12 -> to_return = "Twelve";
                case 13 -> to_return = "Thirteen";
                case 15 -> to_return = "Fifteen";
                default -> to_return = getSingles(n_2) + "teen";
            }
        } else {
            switch (n_1) {
                case 2 -> to_return = "Twenty";
                case 3 -> to_return = "Thirty";
                case 5 -> to_return = "Fifty";
                case 8 -> to_return = "Eighty";
                default -> to_return = getSingles(n_1) + "ty";
            }

            if (n_2 != 0) {
                to_return += " " + getSingles(n_2).toLowerCase();
            }

        }
        return to_return;

    }

    public static void Task6() {
        //Напишете програма, която преобразува дадено число в интервала [0..999] в текст,
        //съответстващ на английското произношение.
        //Примери:
        //● 0 -> "zero"
        //● 273 -> "two hundred and seventy three"
        //● 400 -> "four hundred"
        //● 501 -> "five hundred and one"
        //● 711 -> "seven hundred and eleven“
        //Ако се въведе число извън интервала - принтирайте “Invalid number”.

        int n = readInt("Въедете число [0..999]: ");

        int n_str = Integer.toString(n).length();

        if (n < 0) {
            System.out.println("Програмата работи само с положителни числа. Моля въведете число в диапазона [0 - 999]");
            Task6();
        }

        if (n_str == 1) {
            System.out.println(getSingles(n));
        } else if (n_str == 2) {
            System.out.println(getDoubles(n));
        } else if (n_str == 3) {
            int n_hundreds = Integer.parseInt(Integer.toString(n).substring(0, 1));
            int n_doubles = Integer.parseInt(Integer.toString(n).substring(1, 3));
            String doubles = getDoubles(n_doubles);
            if (doubles.equalsIgnoreCase("")) {
                System.out.println(getSingles(n_hundreds) + " hundred");
            } else {
                System.out.println(getSingles(n_hundreds) + " hundred and " + getDoubles(n_doubles).toLowerCase());
            }
        } else {
            System.out.println("Грешен избор. Моля въведете число в диапазона [0 - 999]..");
        }
        Task6();
    }

    public static void Task7() {
        //Попитайте ваш близък какви критерии има, за да определи една кола (или телефон
        //или лаптоп) като добър, като лош и в останалите случаи казваме, че е със средна
        //оценка. Трябва да има поне 2 критерия. Напишете програма, която по въведените
        //критерии изписва оценка: добър / среден / лош.
        //Програмата трябва при всякакви входни данни да отпечатва резултат
    }

}