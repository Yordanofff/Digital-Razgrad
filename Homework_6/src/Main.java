import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_YELLOW = "\u001B[33m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static void main(String[] args) {


//        Task1(4);  // Will also work with other values

//        System.out.println(Task2("Test"));

//        Task3();

        Task4();

    }

    public static void Task1(int num) {
        //Да се напише метод, който връща като резултат правоъгълен триъгълник
        //от ‘@’, който има 4 реда и изглежда по този начин:
        //      @
        //    @ @
        //  @ @ @
        //@ @ @ @

        int j = (num * 2) -3;
        String symbols_to_print = " @";

        for (int i=1; i<=num; i++){

            System.out.print(" ".repeat(j));

            if (j>2) {
                j-=2;
            } else {
                j=0;
            }

            String to_print = symbols_to_print.repeat(i);

            // remove the space in the beginning of the string on the last row.
            if (i==num) {
                to_print = to_print.trim();
            }

            System.out.println(to_print);

        }
    }

    public static String Task2(String str) {
        //Да се напише метод, който получава като вход текст от 2 символа и връща
        //като резултат обърнатия текст. Без да използвате готова функция reverse().
        //3
        //Примерен вход Примерен изход
        //BG GB
        //az za

        String newStr="";
        char ch;

        for (int i=0; i<str.length(); i++)
        {
            ch = str.charAt(i);
            newStr = ch + newStr;
        }
        return newStr;
    }

    // Task 3 below

    public static double calculateKelvinToCelsius(double K) {
        return K - 273.15;
    }

    public static double calculateKelvinToFahrenheit(double K) {
        return K * 9/5 - 459.67;
    }

    public static void Task3() {
        //Като използвате методи напишете програма, която чете един символ и
        //десетично число (в тази последователност). Десетичното число са градуси
        //в Келвини, а символът да е "C" или "F" (Целзий или Фаренхайт). Символът
        //трябва да е главна буква. При въвеждане на "C" се изчисляват градусите в
        //Целзий, а при "F" във Фаренхайт. Ако се въведе различен от тези два
        //символа -> принтирайте “Invalid input”.
        //Напишете два метода ‘calculateKelvinToCelsius’ и ‘calculateKelvinToFahrenheit’ -
        //един за изчисляване на Целзий от Келвини, а другия за Фаренхайт от
        //Келвини и ги използвайте. Принтирайте резултатът на конзолата така:
        //K(<келвини>) -> <C/F>(<резултат>)
        //Пример с Целзий: K(274.15) -> C(1.0)
        //Подсказки:
        //F = K * 9/5 - 459.67
        //C = K - 273.15
        //*не е нужно да форматирате резултатът до 2 цифри след десетичната запетая

        Scanner scanner = new Scanner(System.in);

        System.out.print("Въведете градуси в Келвин: ");

        double degreesKelvin = scanner.nextDouble();

        if (degreesKelvin < -273.15) {
            System.out.println("Въведената температура е невалидна.");
        } else {
            System.out.print("Въведете \"C\" за Целзий или \"F\" за Фаренхайт: ");
            String toConvert = scanner.next();
            if (toConvert.equalsIgnoreCase("c")) {
                System.out.println("K(" + degreesKelvin + ") -> C(" + calculateKelvinToCelsius(degreesKelvin) + ")");
            } else if (toConvert.equalsIgnoreCase("f")) {
                System.out.println("K(" + degreesKelvin + ") -> F(" + calculateKelvinToFahrenheit(degreesKelvin) + ")");
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    // Task 4 below

    public static Integer reverseInt(int num) {
        int reverse = 0;
        while(num != 0)
        {
            int remainder = num % 10;
            reverse = (reverse * 10) + remainder;
            num = num/10;
        }
        return reverse;
    }

    public static double getAvgOfTwoNums(double a, double b) {
        return (a+b)/2;
    }

    public static double solveLinearEquation(double a, double b) {
        // a * x + b = 0
        // a * x = -b;
        // x = -b/a;
        return -b/a;
    }

    public static void Task4() {
        //Напишете програма, която решава следните задачи:
        //1) Обръща последователността на цифрите на двуцифрено число.
        //2) Пресмята средното аритметично на две числа.
        //3) Решава линейното уравнение a * x + b = 0.
        //Създайте подходящи методи за всяка една от задачите.
        //Напишете програмата така, че на потребителя да му бъде изведено
        //текстово меню, от което да избира коя задача да решава.
        //Направете проверка на входните данни:
        //за 1): двуцифреното число трябва да е положително
        //за 2): двете числа трябва да са по-големи от 0
        //за 3): коефициентът ‘a’ трябва да е
        // различен от 0
        //Ако се въведе различно число от 1, 2 или 3 -> принтирайте “Invalid input”.
        //Ако има грешка при валидирането на входните данни -> принтирайте
        //“Invalid input”.

        System.out.println("Изберете една от трите опции: ");
        System.out.println("1) Обръща последователността на цифрите на двуцифрено число.");
        System.out.println("2) Пресмята средното аритметично на две числа.");
        System.out.println("3) Решава линейното уравнение a * x + b = 0.");

        double userChoice = scanner.nextInt();
        if (userChoice == 1) {
            Task41();
        } else if (userChoice == 2) {
            Task42();
        } else if (userChoice == 3) {
            Task43();
        } else {
            printError("Грешен избор. Въведете 1,2 или 3.");
            Task4();
        }
    }

    public static void reTask4() {
        scanner.nextLine();
        System.out.println(COLOR_YELLOW + "\nНатисни Enter за да се върнеш в менюто. Всичко друго за Exit." + COLOR_RESET);
        String input = scanner.nextLine();
        while (true) {
            if (input.equals("")) {
                Task4();
            } else {
                printError("Exiting the app..");
                System.exit(0);
            }
        }
    }

    public static void printError(String error) {
        System.out.println(COLOR_RED + "\n\t" + error + "\n" + COLOR_RESET);
    }

    public static void printAns(String answer) {
        System.out.println(COLOR_GREEN + "\t" + answer + COLOR_RESET);
    }

    public static void Task41() {
        // will be called when option 1 is selected in task 4.
        // Created so that it can be called directly when there is Invalid input.
        System.out.print("Въведете число, което искате да обърнете: ");
        int num = scanner.nextInt();
        if (num <= 0) {
            printError("Invalid input. Въведеното число е отрицателно. Моля въведете положително число!");
            Task41();
        }
        printAns("Обърнатото число е: " + reverseInt(num));

        reTask4();
    }

    public static void Task42() {
        // will be called when option 2 is selected in task 4.
        // Created so that it can be called directly when there is Invalid input.
        System.out.print("Въведете първото число: ");
        double num1 = scanner.nextDouble();
        System.out.print("Въведете второто число: ");
        double num2 = scanner.nextDouble();
        if (num1 < 0 || num2 < 0) {
            printError("Invalid input. Поне едно от въведените числа е отрицателно. Моля въведете положителни числа.");
            Task42();
        }
        printAns("Средната аритметична стойност на двете числа е: " + getAvgOfTwoNums(num1, num2));

        reTask4();
    }

    public static void Task43() {
        // will be called when option 3 is selected in task 4.
        // Created so that it can be called directly when there is Invalid input.
        System.out.print("Въведете a: ");
        double a = scanner.nextDouble();

        if (a == 0) {
            printError("Invalid input. А не може да бъде нула. Пробвай пак.");
            Task43();
        }

        System.out.print("Въведете b: ");
        double b = scanner.nextDouble();

        System.out.println("Задачата е: \"" + a + "х + " + b + " = 0\"");
        printAns("Отговор: х = " + solveLinearEquation(a, b));

        reTask4();
    }

}