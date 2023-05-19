import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Task 1
        String[] wordsForTask1 = {"this", "is", "my", "test", "sentence"};
        String reversedWords = Task_1(wordsForTask1);
        System.out.println("\n1. Reversed words in " + Arrays.toString(wordsForTask1) + " ==> " + reversedWords);

        // Task 2
        int[] numbersBefore = {1, 2, 3, 4, 5};
        int[] numbersAfter = Task_2(numbersBefore);
        System.out.println("\n2. " + Arrays.toString(numbersBefore) + " * [position] ==> " + Arrays.toString(numbersAfter));

        // Task 3
        int[] numberArray = {7, 0, 6, 4, 5};
        int sum = Task_3(numberArray);
        System.out.println("\n3. The sum of all numbers in the array: " + Arrays.toString(numberArray) + " ==> " + sum);

        // Task 4
        int[] numberArrayOne = {1, 2, 3, 4, 5, 1};
        int[] numberArrayTwo = {4, 10, 2, 6, 20};
        int[] result = Task_4(numberArrayOne, numberArrayTwo);
        System.out.println("\n4. The common numbers in " + Arrays.toString(numberArrayOne) + " and " + Arrays.toString(numberArrayTwo) + " ==> " + Arrays.toString(result));

    }

    public static String Task_1(String[] words) {
        //Напишете метод, който получава като параметър масив от
        //думи и отпечатва думите отзад напред.
        //Примерен вход: String[] words = {“this”, “is”, “my”, “test”,
        //“sentence”};
        //Примерен изход: sentence test my is this

        String result = "";
        for (int i = words.length - 1; i >= 0; i--) {
            result += words[i];
            // Don't append " " on the last word.
            if (i != 0) {
                result = result + " ";
            }
        }
        return result;
    }

    public static int[] Task_2(int[] array) {
        //Създайте програма, която по подаден масив да създава
        //нов, но елементите му да са умножени по съответния
        //индекс в масива.
        //Пример:
        //int[] numbersBefore = {1, 2, 3, 4, 5};
        //int[] numbersAfter = {0, 2, 6, 12, 20};

        int[] numbersAfter = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            numbersAfter[i] = array[i] * i;
        }
        return numbersAfter;
    }

    public static int Task_3(int[] array) {
        //Напишете програма, която намира сума от елементите на
        //масив.
        //Примерен вход: int[] numberArray = {7, 0, 6, 4, 5};
        //Примерен изход: 22 //(7 + 0 + 6 + + 4 + 5)

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    public static int[] Task_4(int[] array1, int[] array2) {
        //Създайте програма, която по подадени два масива да
        //създава нов, но само от общите елементи на двата масива.
        //Пример:
        //int[] numberArrayOne= {1, 2, 3, 4, 5, 1};
        //int[] numberArrayTwo = {4, 10, 2, 6, 20};
        //int[] result = {2, 4};

        // Get the count of all numbers between the two arrays that match.
        int matchingNumbers = 0;
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if (array1[i] == array2[j]) {
                    matchingNumbers += 1;
                }
            }
        }

        // Create a new array to write all those numbers
        int[] result = new int[matchingNumbers];

        // Populate the new array
        int position = 0;
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if (array1[i] == array2[j]) {
                    result[position] = array1[i];
                    position += 1;
                }
            }
        }

        return result;

    }

}