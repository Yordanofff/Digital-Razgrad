import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

//        double[] array = {1.1, 2.2, 3.3, 4.4, 5.5};
//        Task_1(array, 2);
//        Task_1(array, 6);
//        Task_1(array, 0);
//
//        Task_12(array, 2);
//        Task_12(array, 6);
//        Task_12(array, 0);
//
//
//        Task_2();
//
//
//        Task_3();

    }


    public static void Task_1(double[] array, int position) {
        //Да се напише програма, в която имате масив с 5 елемента - дробни
        //числа. Потребителя въвежда номер на число което да се отпечата
        //(число от 1 до 5) и програмата отпечатва съответното число. Ако
        //потребителя въведе невалиден номер на елемент трябва да хвърляте
        //изключение
        //Направете задачата по 2 начина: чрез try/catch и чрез throw new
        //Exception

        try {
            System.out.println("Позиция [" + position + "] е: " + array[position - 1]);
        } catch (Exception e) {
            System.out.println("Позиция [" + position + "] е невалидна. Изберете елемент от 1 до " + array.length);
        }
    }

    public static void Task_12(double[] array, int position) {
        if (position <= 0 || position >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Избраната позиция е извън обхвата на масива!");
        }
        System.out.println("Позиция [" + position + "] е: " + array[position - 1]);
    }




    public static void Task_2() throws Exception {
        //Да се напише програма, която генерира масив с 10 произволни цели
        //числа в интервала от 1 до 100. Програмата записва тези числа във
        //файлове random_numbers.txt и random_numbers.csv

        int[] list = generateIntArray(10);
        createAndWriteArrayToTxtFile(list);
        createAndWriteArrayToCsvFile(list);

    }

    public static void createAndWriteArrayToTxtFile(int[] array) throws Exception {
        String extension = ".txt";
        String fileName = "random_numbers" + extension;
        String separator = " ";

        try {
            FileWriter myWriter = new FileWriter(fileName);
            System.out.println("File created successfully.");
            writeArrayToFile(myWriter, array, separator);
        } catch (IOException e) {
            System.out.println("Error occurred!");
            e.printStackTrace();
        }
    }

    public static void createAndWriteArrayToCsvFile(int[] array) throws Exception {

        String extension = ".csv";
        String fileName = "random_numbers" + extension;
        String separator = ",";

        try {
            FileWriter myWriter = new FileWriter(fileName);
            System.out.println("File created successfully.");
            writeArrayToFile(myWriter, array, separator);
        } catch (IOException e) {
            System.out.println("Error occurred!");
            e.printStackTrace();
        }
    }

    public static void writeArrayToFile(FileWriter myWriter, int[] array, String separator) throws IOException {
        try {
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
                if (i == array.length - 1) {
                    myWriter.write(array[i]);
                } else {
                    myWriter.write(array[i] + separator);
                }
            }
            myWriter.write("\n");
            myWriter.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int[] generateIntArray(int length) {
        Random rand = new Random();
        int[] list = new int[length];

        for (int i = 0; i < length; i++) {
            int val = rand.nextInt(100) + 1;
            list[i] = val;
        }

        return list;
    }




    public static void Task_3() throws FileNotFoundException {
        //Да се напише метод, който получава като параметър име на .txt файл
        //с текст (подберете текст с дължина поне 20 думи, като думите са
        //разделени с интервал). Методът прочита всяка отделна дума и
        //генерира файл small_words.txt, който съдържа думите с дължите 3 и
        //по-малко символа, като всяка дума е на нов ред. И файл words.txt,
        //който съдържа думи с дължина 4 и повече символа, като всяка дума
        //е отделена с интервал
        String fileName;

        File file = new File("task4.txt");
        Scanner inputFile = new Scanner(file);

        while (inputFile.hasNext()) {
            String word = inputFile.next();

            // Find the words that end with ,.!? and remove the symbol at the end.
            if (word.endsWith(".") || word.endsWith(",") || word.endsWith("!") || word.endsWith("?")) {
                word = word.substring(0, word.length() - 1);
            }

            // Append the words
            if (word.length() <= 3) {
                fileName = "small_words.txt";
                appendToFile(fileName, word);
            } else {
                fileName = "words.txt";
                appendToFileWithSpace(fileName, word);
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

    public static void appendToFileWithSpace(String fileName, String word) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(word + " ");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing " + word);
            e.printStackTrace();
        }
    }

}