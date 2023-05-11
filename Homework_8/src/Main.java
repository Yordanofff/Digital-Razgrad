import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.join;

public class Main {
    public static void main(String[] args) {
//        Task_1("Hello", 5);
//        Task_2(17, 64);
//        Task_3(45);
//        Task_4();

        runAdvancedTask4(true);
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



    // Task 4 - заигравка с повече опции (извън домашното)



    public static void runAdvancedTask4(boolean userInput) {

        printAppName();

        int begin;
        int end;
        int splitTheNumbersByDashes = 0; // Don't print dashes by default.

        if (userInput){
            int[] userAns = getUserInput();
            begin = userAns[0];
            end = userAns[1];
            splitTheNumbersByDashes = userAns[2];
        } else {
            begin = 10;
            end = 15000;
//            end = 150001234;
        }

        List<Integer> all_happy_numbers = getListOfAllHappyNumbersInRange(begin, end);

        List<List<Integer>> nestedList = splitAllNumbersToLists(all_happy_numbers);

        printNestedLists(nestedList, splitTheNumbersByDashes);
    }

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

    public static int[] getUserInput() {
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

        String ans = "";
        int isSplitByDashes = 3;
        int counter = 0;
        while (!((ans.equalsIgnoreCase("y")) || (ans.equalsIgnoreCase("n")) || (ans.equalsIgnoreCase("x")))) {
            if (counter == 0) {
                System.out.println("Искате ли числата да са разделени с тиренца (ех. 1304 -> 13-04) ?");
                System.out.println("Изберете между [y/n/x] (x - ще принтира и двата варианта)");
                counter += 1;
            }
            ans = scanner.next();
            if (ans.equalsIgnoreCase("y")) {
                isSplitByDashes = 1;
            } else if (ans.equalsIgnoreCase("n")) {
                isSplitByDashes = 0;
            } else if (ans.equalsIgnoreCase("x")) {
                isSplitByDashes = 2;
            } else {
                System.out.println("Грешен отговор. Моля въведете y/n/x.");
            }
        }

        int[] result = new int[3];
        result[0] = begin;
        result[1] = end;
        result[2] = isSplitByDashes; // 1 = true, 0 = false, 2 = both
        return result;
    }

    public static void printAppName(){
        System.out.println("""
                 _ _                                            _                \s
                | | | ___  ___  ___  _ _      ._ _  _ _ ._ _ _ | |_  ___  _ _  ___
                |   |<_> || . \\| . \\| | |     | ' || | || ' ' || . \\/ ._>| '_><_-<
                |_|_|<___||  _/|  _/`_. |     |_|_|`___||_|_|_||___/\\___.|_|  /__/
                          |_|  |_|  <___'                                        \s
                """);
    }

    public static int getLargestDivisor(int numberOfDigits){
        // if 4 --> need to compare first 2 and last 2 --> ab_cd   --> a+b == c+d            --> return 2
        // if 5 --> need to compare first 2 and last 2 --> ab_c_de --> a+b == d+e (ignore c) --> return 2
        // if 6 --> need to compare first 3 and last 3 --> abc_def --> a+b+c == d+e+f        --> return 3
        int numbersToCompare = numberOfDigits/2;

        if (numberOfDigits % 2 != 0) {
            numbersToCompare = (numberOfDigits-1)/2;
        }
        return numbersToCompare;
    }

    public static List<Integer> getListOfAllHappyNumbersInRange(int start, int end) {
        // Will return all happy numbers in the range start-end.
        // Happy numbers are those in which the sum of the first N numbers = the sum of the last N numbers
        // The range might start with 2 digits and end with 6 or more.
        // Need to check the length of the number on every iteration to compare the numbers properly:
        // if number len = 4 ---> compare first 2 and last 2 ---> ab_cd   ---> a+b == c+d
        // if number len = 5 ---> compare first 2 and last 2 ---> ab_c_de ---> a+b == d+e (ignore c)
        // if number len = 6 ---> compare first 3 and last 3 ---> abc_def ---> a+b+c == d+e+f

        List<Integer> allNumbers = new ArrayList<>();

        for (int i = start; i < end ; i++) {

            int length = String.valueOf(i).length();

            // Find the largest divisor - if the number is 6 => 3, if 5 => 2 (ignore mid) etc...
            int numberOfDigitsToCompare = getLargestDivisor(length);

            // Sum of the first few numbers
            int sumBeginning = 0;
            for (int j = 1; j <= numberOfDigitsToCompare; j++) {
                sumBeginning += getDigitAtPosition(i, j);
            }

            // Sum of the last few numbers
            int sumEnd = 0;
            for (int j = length; j > length - numberOfDigitsToCompare; j--) {
                sumEnd += getDigitAtPosition(i, j);
            }

            // Add all happy numbers to the list.
            if (sumBeginning == sumEnd) {
                allNumbers.add(i);
            }
        }

        return allNumbers;
    }

    public static List<List<Integer>> splitAllNumbersToLists(List<Integer> listAllNumbers) {
        // Will add all numbers to a nested List depending on the length of the numbers.
        // Shortest number will be in the first nested list.
        // list(0) ==> 11, 22 , xx....
        // list(1) ==> 111, 222 , xxx.... etc.
        // returns the nested list.

        List<List<Integer>> nestedList = new ArrayList<List<Integer>>();

        int lenFirst = String.valueOf(listAllNumbers.get(0)).length();
        int lenLast = String.valueOf(listAllNumbers.get(listAllNumbers.size()-1)).length();

        // Create a list for every length.
        for (int i = 0; i <= (lenLast-lenFirst); i++) {
            List<Integer> list = new ArrayList<>();
            nestedList.add(list);
        }

        // Add each number to the list that it belongs to.
        for (int i = 0 ; i < listAllNumbers.size(); i++) {
            int currentNumber = listAllNumbers.get(i);  // 99
            int lengthOfCurrentNumber = String.valueOf(currentNumber).length(); // = 2 if 99.

            // Index 2 out of bounds for length 2 - so will be removing the lenFirst to start from 0.
            // nestedList.get(0).add(11);
            nestedList.get(lengthOfCurrentNumber-lenFirst).add(currentNumber);
        }

        return nestedList;
    }

    public static void printNestedLists(List<List<Integer>> nestedList, int splitTheNumbers) {
        int lenFirst = String.valueOf(nestedList.get(0).get(0)).length();
        int lenLast = String.valueOf(nestedList.get(nestedList.size()-1).get(0)).length();

        int counter = 0;

        // Print the different lists
        for (int i = lenFirst; i <= lenLast; i++) {
            int cLen = nestedList.get(i-lenFirst).size();
            counter += cLen;
            List<Integer> cList = nestedList.get(i-lenFirst);
            System.out.println("\n\nБрой " + i + "-цифрени числа: " + cLen);
            // System.out.println(cList);

            if (splitTheNumbers == 1){
                printListCommaSeparated(cList, true);
            } else if (splitTheNumbers == 2) {
                printListCommaSeparated(cList, false);
                printListCommaSeparated(cList, true);
            } else {
                printListCommaSeparated(cList, false);
            }
        }

        System.out.println("\n\nВсички щастливи числа: " + counter);
    }

    public static void printListCommaSeparated(List<Integer> list, boolean splitTheNumbers) {
        String delimiter = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            if (splitTheNumbers) {
                String numWithDashes = splitNumberWithDashesOnDivider(num);
                sb.append(delimiter).append(numWithDashes);
            } else {
                sb.append(delimiter).append(num);
            }
            delimiter = ", ";
        }
        System.out.println(sb);
    }

    public static String splitNumberWithDashesOnDivider(int numberToSplit){
        // 1120   -> 11-20
        // 12030  -> 12-0-30
        // 123231 -> 123-231

        // Convert to String
        String numString = String.valueOf(numberToSplit);

        int numLen = numString.length();  // if 111 = 3

        int divider = getLargestDivisor(numLen); // if 3=>1  4=>2  5=>2  6=>3 etc..

        String sub = numString.substring(0, divider);
        String remainder = numString.substring(divider);;

        if (numLen % 2 != 0){
            //10301 --> 10-3-01
            remainder = numString.charAt(divider) + "-" + numString.substring(divider+1);
        }

        return sub + "-" + remainder;
    }
}