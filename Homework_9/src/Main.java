import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        Task_1();

//        Task_2(452);

//        Task_3(5);

//        System.out.println(Task_4("Some long text", 'n'));

        Task_5();

    }

    public static void Task_1() {
        //Да се напише метод, който отпечатва таблицата за умножение от 1
        //до 10 в следния формат:
        //1 х 1 = 1
        //2 х 1 = 2
        //2 х 2 = 4
        //3 х 1 = 3
        //3 х 2 = 6
        //3 х 3 = 9
        //…
        //10 х 8 = 80
        //10 х 9 = 90
        //10 х 10 = 100

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.println(i + " x " + j + " = " + i * j);
            }
            System.out.println();
        }
    }

    public static void Task_2(int n) {
        //Да се състави програма , която по въведено естествено
        //число от интервала [100..30000] извежда последователно на
        //всеки ред: цифра от числото, и до нея толкова знака '*',
        //колкото е съответната цифра (хистограма).
        //Цифрите на числото се извеждат отдясно-наляво
        //Пример: 452
        //Изход:
        //2**
        //5*****
        //4****

        String nString = String.valueOf(n);
        int nLen = nString.length();

        for (int i = nLen; i > 0; i--) {
            // Convert char to int (char - '0')
            int currentDigit = nString.charAt(i - 1) - '0';
            System.out.println(currentDigit + "*".repeat(currentDigit));
        }
    }

    public static void Task_3(int n) {
        //Напишете програма която отпечатва триъгълник със страна
        //n.
        //Пример:
        //за n = 3
        //  о
        // ооо
        //ооооо
        //
        //за n = 4
        //   о
        //  ооо
        // ооооо
        //ooooooo

        for (int i = 1; i <= n; i++) {
            int num_spaces = n - i;
            int num_o = 2 * i - 1;
            System.out.println(" ".repeat(num_spaces) + "o".repeat(num_o) + " ".repeat(num_spaces));
        }

    }

    public static boolean Task_4(String text, char ch) {
        int textLen = text.length();
        for (int i = 0; i < textLen; i++) {
            char currentChar = text.charAt(i);
            if (currentChar == ch) {
                return true;
            }
        }
        return false;
    }

    public static void Task_5() {
        //Напишете прост калкулатор за събиране, който работи
        //по следния начин:
        //- Очаква въвеждане на първото число в конзолата;
        //- Очаква въвеждане на второто число в конзолата;
        //- Извежда в конзолата резултата - сбора от двете числа;
        //- Ако второто въведено число е 0, прекратява
        //изпълнението, след като изведе резултата,
        //иначе очаква да се въведат две числа отново.

        Scanner scanner = new Scanner(System.in);
        int numTimesExecuted = 0;

        while (true) {
            // Show this only once.
            if (numTimesExecuted == 0) {
                System.out.println("\n" + "*".repeat(18) + "  Прост калкулатор  " + "*".repeat(18));
                System.out.println("Сумира две цели числа. Ако 2-рото е 0 - спира програмата.");
            }

            System.out.print("\nВъведете 1-вото число: ");
            int a = scanner.nextInt();

            System.out.print("Въведете 2-рото число: ");
            int b = scanner.nextInt();

            int result = a + b;

            System.out.println(a + " + " + b + " = " + result);

            numTimesExecuted += 1;

            if (b == 0) {
                System.exit(0);
            }
        }

    }
}