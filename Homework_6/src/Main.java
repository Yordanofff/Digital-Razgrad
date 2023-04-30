import java.util.Scanner;

public class Main {
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
    }

}