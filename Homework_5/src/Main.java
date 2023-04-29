// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        System.out.println(Task1(1));
//        System.out.println(Task2(4, 7));
//        System.out.println(Task3(10,5,2));
//            Task4('a');
//        System.out.println(Task5(2020));
    }


    public static String Task1(int dayNum) {
        //Да се напише метод, който приема
        //цяло число между 1 и 7 и връща
        //съответния ден от седмицата (на
        //английски език). При въведено число,
        //което не е в този интервал, да се
        //връща “Maybe on another planet”.

        return switch (dayNum) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Maybe on another planet";
        };
    }

    public static Boolean Task2(int num1, int num2) {
        //Да се напише метод, който проверява
        //дали остатъкът при деление на 3 на
        //две числа е един и същ

        int rem1 = num1 % 3;
        int rem2 = num2 % 3;

        return rem1 == rem2;
    }

    public static Boolean Task3(int num1, int num2, int num3) {
        //Да се напише метод, който приема
        //три числа и определя дали първото
        //се дели без остатък едновременно на
        //второто и третото
        return num1 % num2 == 0 && num1 % num3 == 0;
    }

    public static void Task4(char n) {
        //Да се напише метод, който прoверява
        //дали един символ е гласна буква. Да
        //не се отчита дали въведената буква е
        //главна или малка.
        //Символът се получава като
        //параметър, а резултатът да се
        //отпечата в метода.
        switch (n) {
            case 'a', 'e', 'i', 'o', 'u' -> System.out.println(n + " е гласна (EN)");
            case 'а', 'ъ', 'о', 'у', 'е', 'и' -> System.out.println(n + " е гласна (BG)");
            default -> System.out.println(n + " е съгласна");
        }
    }

    public static Boolean Task5(int year) {
        //Да се напише метод, който приема
        //цяло число, което ще приемаме за
        //година, и прoверява дали тя е била
        //високосна или не.
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }

}