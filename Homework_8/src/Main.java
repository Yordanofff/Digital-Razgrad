import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Task_1("Hello", 5);
//        Task_2(17, 64);
//        Task_3(45);
//        Task_4();

        Task_4_wider_range_and_more_options();

    }

    public static void Task_1(String text, int n) {
        //Като използвате метод, който получава 2 параметъра: текст
        //txt и цяло число n. Методът отпечатва n на брой пъти текстът
        //txt. Извикайте метода 2 пъти с различни аргументи.

        for (int i = 0; i < n; i++) {
            System.out.println(text);
        }
    }

    public static void Task_2(int p, int q) {
        //Като използвате метод напишете програма, която приема 2
        //цели числа p, q и отпечатва всички числа между p и q, които
        //се делят на 5 без остатък. (ако p > q също трябва да
        //отпечатаме числата между p и q, които се делят на 5)

        int smaller = p;
        int bigger = q;

        if (p > q) {
            smaller = q;
            bigger = p;
        }

        // Няма да включва числото 'p' нито 'q' в отговорите.
        for (int i = smaller+1; i < bigger; i++) {
            if (i % 5 == 0){
                System.out.println(i);
            }
        }
    }

    public static void Task_3(int n) {
        //Като използвате метод напишете програма, която приема
        //цяло число n и отпечатва всички числа от 1 до n, които се
        //делят на 4 или на 7.

        for (int i = 1; i < n; i++) {
            if ((i % 4 == 0) || (i % 7 == 0)) {
                System.out.println(i);
            }
        }
    }

    public static void Task_4() {
        //Напишете програма, която изкарва на конзолата всички
        //щастливи четирицифрени числа.
        //Щастливи числа са, когато сборът на първите две цифри на
        //дадено четирицифрено число (ABCD) е равен на сбора на
        //последните две (A+B == C+D).

        for (int i = 1000; i < 10000 ; i++) {

            int a = i / 1000;
            int b = i / 100 % 10;
            int c = i / 10 % 10;
            int d = i % 10;

            if (a + b == c + d) {
                System.out.println(i);
            }
        }
    }



    // Task 4 - заигравка с повече опции.


    public static int getDigitAtPosition(int number,int position) {
        // This method will return the value at a specified position.
        //    Example with 1234:
        //    int a = i / 1000 % 10; ==> 1
        //    int b = i / 100 % 10;  ==> 2
        //    int c = i / 10 % 10;   ==> 3
        //    int d = i / 1 % 10;    ==> 4
        int length = String.valueOf(number).length();
        if (position > length) {
            System.out.println("Error - number not long enough");
            System.exit(1);
        }
        return (number / (int)Math.pow(10, length - position)) % 10;
    }


    public static void printTempSeparator(int currentLength, int temporaryTotal) {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("Total " + currentLength + " digit(s) long numbers: " + temporaryTotal);
        System.out.println("-".repeat(80) + "\n");
    }

    public static void Task_4_wider_range_and_more_options() {
        // Тази програма ще изкара всички щастливи числа.
        // Началното число трябва да е поне двуцифрено. Няма лимит за колко да е голямо голямото число.
        //Щастливи числа са, когато сборът на първите Х цифри на
        //дадено число (ABCD) е равен на сбора на последните Х числа..

        System.out.println("""
                 _ _                                            _                \s
                | | | ___  ___  ___  _ _      ._ _  _ _ ._ _ _ | |_  ___  _ _  ___
                |   |<_> || . \\| . \\| | |     | ' || | || ' ' || . \\/ ._>| '_><_-<
                |_|_|<___||  _/|  _/`_. |     |_|_|`___||_|_|_||___/\\___.|_|  /__/
                          |_|  |_|  <___'                                        \s
                """);
        Scanner scanner = new Scanner(System.in);

        int begin = 0;
        while (begin < 10) {
            System.out.println("Въведете начално число (поне 2 цифри):");
            begin = scanner.nextInt();
            if (begin < 10) {
                System.out.println("Грешка. Въведеното число е по-малко от 10.");
            }
        }

        int end = 0;
        while (end < begin) {
            System.out.println("Въведете крайно число (по-голямо от " + begin + ". Може да бъде всякаква дължина.)");
            end = scanner.nextInt();
            if (end < begin) {
                System.out.println("Грешка. Въведеното число е по-малко от " + begin);
            }
        }

        int temporaryTotal = 0;
        int theTotal = 0;
        int currentLength = String.valueOf(begin).length();

        for (int i = begin; i < end ; i++) {
            // if number len = 4 ---> compare first 2 and last 2 ---> ab_cd   ---> a+b == c+d
            // if number len = 5 ---> compare first 2 and last 2 ---> ab_c_de ---> a+b == d+e (ignore c)
            // if number len = 6 ---> compare first 3 and last 3 ---> abc_def ---> a+b+c == d+e+f
            // The range might start with 4 digits but end with 6 or more.
            // Need to check the length of the number on every iteration.

            int length = String.valueOf(i).length();

            int numbersToCompare = 0;

            // Find the largest divisor - if the number is 6 => 3, if 5 => 2 (ignore mid) etc...
            if (length % 2 == 0) {
                numbersToCompare = length/2;
            } else {
                numbersToCompare = (length-1)/2;
            }

            // Sum of the first few numbers
            int sum_beginning = 0;
            for (int j = 1; j < numbersToCompare + 1; j++) {
                sum_beginning += getDigitAtPosition(i, j);
            }

            // Sum of the last few numbers
            int sum_end = 0;
            for (int j = length; j >= length - numbersToCompare + 1; j--) {
                sum_end += getDigitAtPosition(i, j);
            }

            if (sum_beginning == sum_end) {

                // print different lengths separated by ----
                if (currentLength != length) {
                    printTempSeparator(currentLength, temporaryTotal);
                    currentLength = length;
                    temporaryTotal = 0;

                }

                System.out.print(i + ", ");
                temporaryTotal += 1;
                theTotal += 1;
            }
        }

        // This will print the temp total for the numbers in the last range (x num of digits) as that won't change and it won't be triggered above.
        printTempSeparator(currentLength, temporaryTotal);

        System.out.println("\n\nTotal number of happy numbers: " + theTotal);
    }
}