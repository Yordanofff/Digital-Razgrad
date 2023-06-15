import java.util.Scanner;

public class Main3 {
    static void reverse(String str) {

        char result = str.charAt(0);
        char result1 = str.charAt(1);
        System.out.print("Enter a string : " + result1+result);


    }


    public static void main(String args[]){

        Scanner input=new Scanner(System.in);
        System.out.print("Enter a string : ");
        String  str=input.nextLine();
        reverse(str);

    }
}
