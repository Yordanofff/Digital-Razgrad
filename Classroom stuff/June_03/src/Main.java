import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String a = "es";
        String b = "atest";

        System.out.println(Task_1(a, b));
        System.out.println(Task_1(null, b));
        System.out.println(Task_1(a, null));
        System.out.println(Task_1("", b));
        System.out.println(Task_1(a, ""));

        System.out.println(Arrays.toString(Task_2("This is very long text")));

        String[] arr = {"Иван Иванов", "България", "София", "Ул. Незабравка 12", "1000"};
        Task_3(arr);

        String[] res = Task_4("Java is cool");
        System.out.println(Arrays.toString(res));

        Task_5(3, true);
    }

    public static int Task_1(String a, String b) {
        //Напишете метод, който приема като параметри 2 символни низа и връща
        // първата позиция, на която вторият низ се съдържа в първия.
        //б) Подайте null като стойност на първия параметър и вижте какъв е резултатът.
        //в) Нека методът да връща -5, ако някоя от стойностите на подадените низове е null.
        //г) Нека методът да връща -3, ако първият низ е празен (има дължина 0).
        if (a == null || b == null) {
            return -5;
        } else if (a.length() == 0) {
            return -3;
        }
        return b.indexOf(a);
    }

    public static String[] Task_2(String longText) {
        //Напишете метод, който приема за параметър даден текст и връща масив, съдържащ думите на този текст.
        int numWords = longText.split(" ").length;
        String[] res = new String[numWords];
        for (int i = 0; i < numWords; i++) {
            res[i] = longText.split(" ")[i];
        }
        return res;
    }

    public static void Task_3(String[] data) {
        //Даден е масив, който съдържа адрес за изпращане на писмо.
        // На всяка от позициите има съответно:  [име на получател, държава, град, адрес, пощенски код].
        // Напишете метод, който приема като параметър такъв масив и връща като резултат низ,
        // който съдържа всеки елемент от адреса на отделен ред.
        //Примерен резултат:
        //Иван Иванов
        //България
        //София
        //Ул. Незабравка 12
        //1000
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

    public static String[] Task_4(String longText) {
        //Напишете метод, който приема за параметър даден текст и връща масив,
        // съдържащ думите на този текст.
        //Напр: wordify(“Java is cool”) -> [“Java”, “is”, “cool”]
        int longTextLen = longText.split(" ").length;
        String[] arr = new String[longTextLen];
        for (int i = 0; i < longTextLen; i++) {
            arr[i] = longText.split(" ")[i];
        }
        return arr;
    }

    public static void Task_5(int num, boolean isFrameMid) {
        //Напишете програма, която чете число n (3 ≤ n ≤ 100) и рисува очила с големина n. Примери:
        //n = 3:
        //******   ******
        //*////*|||*////*
        //******   ******
        //n = 4:
        //********    ********
        //*//////*||||*//////*
        //*//////*    *//////*
        //********    ********

        for (int i = 0; i < num; i++) {
            if (i==0 || i == num-1) {
                System.out.println("*".repeat(num*2) + " ".repeat(num)+ "*".repeat(num*2));
            } else {
                String midSymbol = " ";
                int mid = 1;  // when isFrameMid not True
                if (isFrameMid) {
                    mid = num / 2;
                    if (num % 2 == 0) {
                        mid = num / 2 - 1;
                    }
                }
                if (i == mid) {
                    midSymbol = "|";
                }
                int numbl = num*2 - 2;
                System.out.println("*" + "/".repeat(numbl) + "*" + midSymbol.repeat(num) + "*" + "/".repeat(numbl) + "*");
            }
        }
    }
}