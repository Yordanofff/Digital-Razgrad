package to_refactor;

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        String str = "tova e programa";

        char[] charArr = str.toCharArray();

        for (int X = charArr.length - 1; X >= 0; X--) {

            System.out.print(charArr[X]);
        }
    }
}