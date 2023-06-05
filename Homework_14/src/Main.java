import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Task_1();

//        System.out.println(Task_2("Heleh"));
//        System.out.println(Task_2("Helleh"));
//        System.out.println(Task_2("Hhelleh"));
//        System.out.println(Task_2("jHhelleh"));

//        System.out.println(Task_3("fez day", true));
//        System.out.println(Task_3("day fyyyz", true));
//        System.out.println(Task_3("Dad fez", true));
//        System.out.println(Task_3("Yellowy fez day", true));
//        System.out.println(Task_3("YellowY fez day", true));

//        Task_4(5, true, "triangle.txt");
//        Task_4(5, true, "triangle");

//        Task_5(1);
//        Task_5(2);
//        Task_5(4);
//        Task_5(6);

//        Task_6(3, true);
//        Task_6(6, true);
//        Task_6(7, true);
    }

    public static void Task_1() {
        System.out.println("Say hello to my github: https://github.com/Yordanofff");
    }

    public static String Task_2(String word) {
        //При въведен текст от потребителя, кажете дали започва и
        //свършва по огледален начин. Например
        //Akara - да, започва и свършва с “а”
        //Ba123ab - да, започва с “ba” и свършва с “ab”
        //A5c21b - не, започва с “a”, а завършва с “b”
        //Принтирайте какъв е повтарящият се участък, но нека бъде само
        //малки букви. Т.е. Изведете “a” за първия пример или “ba” за
        //втория.
        //Ако няма част, която да е огледална - принтирайте “No mirrored
        //part”.
        //Подсказка:
        //Използвайте методите от string.equalsIgnoreCase и

        int halfLen = word.length() / 2;
        int counter = 0;
        String repeating = "";
        for (int i = 0; i < word.length(); i++) {
            char currentFront = word.charAt(i);
            char currentBack = word.charAt(word.length() - i - 1);
            if (i == halfLen) {
                break;
            }
            if (String.valueOf(currentFront).equalsIgnoreCase(String.valueOf(currentBack))) {
                repeating += currentFront;
                counter += 1;
            } else {
                break;
            }
        }
        if (counter == 0) {
            return "No mirrored part";
        } else {

            if (repeating.length() == halfLen && word.length() % 2 == 1) {
                // if word is abcba --> return abc (c is mirrored)
                return (repeating + word.charAt(word.length() / 2)).toLowerCase();
            }

            return repeating.toLowerCase();
        }
    }

    public static int Task_3(String words, boolean ignoreCase) {
        //При подаден стринг, пребройте думите завършващи на y и
        //на z.
        //Например:
        //fez day - 2
        //day fyyyz - 2
        //Dad fez - 1
        //Yellowy fez day - 3

        String[] splitWords = words.split(" ");
        int counter = 0;
        for (int i = 0; i < splitWords.length; i++) {

            // If ignoreCase == False
            String wordToCompare = splitWords[i];
            if (ignoreCase) {
                wordToCompare = splitWords[i].toLowerCase();
            }

            if (wordToCompare.endsWith("y") || wordToCompare.endsWith("z")) {
                counter += 1;
            }
        }
        return counter;
    }

    public static void Task_4(int n, boolean saveToFile, String fileName) {
        //Да се напише програма, която чете число n, въведено от
        //потребителя, и печата триъгълник от удивителни знаци (!)
        //като в примерите:
        //За n = 2 За n = 4
        //! !
        //! ! ! !
        //! ! !
        //! ! ! !
        //б) направете така че триъгълникът да се записва и във .txt файл

        for (int i = 1; i <= n; i++) {
            String row = "!".repeat(i);
            System.out.println(row);

            if (saveToFile) {
                // Add .txt to the filename if it's not already.
                if (!fileName.endsWith(".txt")) {
                    fileName = fileName + ".txt";
                }
                appendToFile(fileName, row);
            }
        }
    }

    public static void appendToFile(String fileName, String word) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(word + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing " + word);
            e.printStackTrace();
        }
    }

    public static void Task_5(int n) {
        //Напишете програма, която чете цяло положително число n,
        //въведено от потребителя, и чертае на конзолата квадратна
        //рамка с размер n * n като в примерите по-долу:
        //  3
        //  ? - ?
        //  | - |
        //  ? - ?
        //
        //  6
        //  ? - - - - ?
        //  | - - - - |
        //  | - - - - |
        //  | - - - - |
        //  | - - - - |
        //  ? - - - - ?

        if (n <= 1) {
            System.out.println("\nError - N needs to be bigger than 1.\n");
            return;
        }
        System.out.println("N = " + n);
        for (int i = 0; i < n; i++) {
            String sides = "|";
            if ((i == 0) || i == n - 1) {
                sides = "?";
            }
            int lenDashes = n - 2;
            System.out.println(sides + " " + "- ".repeat(lenDashes) + sides);
        }
        System.out.println();
    }

    public static void Task_6(int num, boolean isFrameMid) {
        //Напишете програма, която чете число n (3 ≤ n ≤ 100) и рисува очила с големина n. Примери:
        //n = 3:
        //******   ******
        //*////*|||*////*
        //******   ******
        //
        //n = 4:
        //********    ********
        //*//////*||||*//////*
        //*//////*    *//////*
        //********    ********

        System.out.println("N = " + num + ", isFrameMid = " + isFrameMid);
        for (int i = 0; i < num; i++) {
            if (i == 0 || i == num - 1) {
                System.out.println("*".repeat(num * 2) + " ".repeat(num) + "*".repeat(num * 2));
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
                int numbl = num * 2 - 2;
                System.out.println("*" + "/".repeat(numbl) + "*" + midSymbol.repeat(num) + "*" + "/".repeat(numbl) + "*");
            }
        }
        System.out.println();
    }
}