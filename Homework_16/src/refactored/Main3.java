package refactored;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        String userInput = getUserInput();
        printReversed(userInput);
    }

    public static String reverse(String str) {
        char[] charArr = str.toCharArray();
        char[] newCharArr = new char[charArr.length];

        for (int i = 0; i < charArr.length; i++) {
            newCharArr[charArr.length - i - 1] = charArr[i];
        }

        return String.valueOf(newCharArr);
    }

    public static String getUserInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string : ");
        return input.nextLine();
    }

    public static void printReversed(String str) {
        System.out.println(reverse(str));
    }
}

