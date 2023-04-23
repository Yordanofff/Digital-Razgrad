import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Main {
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

    public static void main(String[] args) {
//        Task1();
//        Task2();
//        Task3();
//        Task4();
//        Task5();
        Task6();
    }
    public static void Task1(){
        //Напишете програма, която по въведени 2 цели числа отпечатва по-голямото
        int a = readInt("Въведете А: ");
        int b = readInt("Въведете Б: ");
        if (a>b) {
            System.out.println("По-голямото число е: А");
        } else {
            System.out.println("По-голямото число е: Б");
        }
    }

    public static void Task2(){
        //Напишете програма, която по въведено дробно число отпечатва дали то е
        // положително, отрицателно или равно на нула
        float num = readFloat("Въведете число: ");
        String common = "Числото " + num + " е ";
        if (num < 0) {
            common += "отрицателно";
        } else if (num > 0) {
            common += "положително";
        } else {
            common += " равно на нула.";
        }

        System.out.println(common);
    }

    public static void Task3(){
        //Напишете програма за валутен калкулатор. Програмата получава като вход от
        // потребителя 2 стойности: сума в лева, която ще се конвертира и валута към която
        // ще се конвертира (USD, EUR, GBP). Ползвайте следните коефициенти:
        //USD: 0.54
        //EUR: 0.51
        //GBP: 0.43
        float amount = readFloat("Въведете сума (лв): ");

        System.out.println("Въведете валутата в която ще конвертирате(USD/EUR/GBP): ");
        String currency = scanner.next();

        if (currency.equalsIgnoreCase("USD")) {
            System.out.println("Сумата от \"" + amount + "\"лв. => " + (0.54*amount) + "USD");
        } else if (currency.equalsIgnoreCase("EUR")){
            System.out.println("Сумата от \"" + amount + "\"лв. => " + (0.51*amount) + "EUR");
        } else if (currency.equalsIgnoreCase("GBP")){
            System.out.println("Сумата от \"" + amount + "\"лв. => " + (0.43*amount) + "GBP");
        } else {
            System.out.println("Валутата не се поддържа. Моля въведете USD/EUR/GBP.");
        }

    }

    public static void Task4() {
        //Напишете програма, която по въведени 3 дробни числа отпечатва най-голямото
        //б) Напишете програма, която по въведени 3 дробни числа отпечатва второто по големина
        float A = readFloat("Въведете число А: ");
        float B = readFloat("Въведете число B: ");
        float C = readFloat("Въведете число C: ");
        float biggest = A;
//        float smallest = A;
        float secondBiggest;

        if (B > biggest) {
            biggest = B;
        }
        if (C > biggest) {
            biggest = C;
        }
        double[] ans = getBigger((double) A, (double) B);
        System.out.println((int)ans[0] + "    " + ans[1]);



//        System.out.println("Най-голямото число е: " + biggest);
//        System.out.println("Второто по големина число е: " + secondBiggest);
    }
    public static double[] getBigger(double a, double b) {
        double[] ans = new double[2];  // position and value
        if (a > b) {
            ans[0] = 0;
            ans[1] = a;
        } else {
            ans[0] = 1;
            ans[1] = b;
        }
        // returning array of elements
        return ans;
    }

    public static void Task5(){
        //Напишете програма, която по въведен месец от потребителя
        // (цяло число от 1 до 12), извежда името на месеца на английски език.
        int month = readInt("Въведете число от 1 до 12: ");
        switch (month){
            case 1:
                System.out.println("Януари"); break;
            case 2:
                System.out.println("Февруари"); break;
            case 3:
                System.out.println("Март"); break;
            case 4:
                System.out.println("Април"); break;
            case 5:
                System.out.println("Май"); break;
            case 6:
                System.out.println("Юни"); break;
            case 7:
                System.out.println("Юли"); break;
            case 8:
                System.out.println("Август"); break;
            case 9:
                System.out.println("Септември"); break;
            case 10:
                System.out.println("Октомври"); break;
            case 11:
                System.out.println("Ноември"); break;
            case 12:
                System.out.println("Декември"); break;
            default:
                System.out.println("Error - 1-12 only."); break;

        }
    }

    public static void Task6() {
        //Да се напише програма, която проверява дали едно четирицифрено число
        // е палиндром - число, което четено отзад напред и отпред назад е едно и също.
        //Примери:
        //1221 - да
        //1231 - не
        //8888 - да
        //9889 - да
        //Опитайте да решите задача по няколко начина

        int num = readInt("Въведете число: ");
        String numStr = Integer.toString(num);
        int len = numStr.length();

//        System.out.println(len);
//        System.out.println(numStr);

//        System.out.println(" ----- ");

//        for (int x = 0; x < len; x++) {
//            System.out.print(numStr.charAt(x));
//        }

//        System.out.println(" ----- ");
        String newnumStr = "";
        for (int x = len-1; x >= 0; x--) {
//            System.out.print(numStr.charAt(x));
            newnumStr += numStr.charAt(x);
        }
//        System.out.println(newnumStr);

        int newnumStrInt = Integer.parseInt(newnumStr);

        if (newnumStrInt == num) {
            System.out.println("Da");
        } else {
            System.out.println("No");
        }
    }
}