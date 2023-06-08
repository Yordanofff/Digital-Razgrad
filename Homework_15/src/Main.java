import java.io.IOException;
import java.io.RandomAccessFile;
//import Task_3;

public class Main {
    public static void main(String[] args) throws IOException {

        String text = "Hello Digital Razgrad";
        int numWords = Task_1A_countNumberOfWords(text);
        System.out.println(numWords);

        int numWordsInFile = Task_1A_countNumberOfWordsInFile("some_text_file.txt");
        System.out.println(numWordsInFile);

        Task_2(3, true);
        Task_2(6, true);
        Task_2(7, true);

        new Task_3();
        Task_3.main("students_list_task3.txt", "output_file_task3.csv");
        }

    public static int Task_1A_countNumberOfWords(String text) {
        //Като използвате метод напишете програма, която позволява
        //на потребителя да въведе текст и програмата отпечатва
        //колко думи съдържа текстът въведен от потребителя
        //(трябва да има възможност да са повече от 1 дума).
        //б) Да се добави нов метод, в който се прочита съдържанието
        //на .txt файл и методът връща като резултат колко думи се
        //съдържат в текста.

        // "//s" = " " - will split by a single space
        // "//s+" or " +" - will not count multiple spaces as a word.
        return text.split(" +").length;
    }
    public static int Task_1A_countNumberOfWordsInFile(String fileName) {
        String str;
        int wordsCounter = 0;

        try {
            RandomAccessFile file = new RandomAccessFile(fileName, "r");

            while ((str = file.readLine()) != null) {

                // don't count empty rows
                if (str.equals("")){
                    continue;
                }

                int wordsOnCurrentRow = Task_1A_countNumberOfWords(str);
                wordsCounter += wordsOnCurrentRow;
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsCounter;
    }

    // Task_2 copied from previous homework
    public static void Task_2(int num, boolean isFrameMid) {
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


