import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] wordsForTask1 = {"this", "is", "my", "test", "sentence"};
        Task_1(wordsForTask1);

        int[] numbersBefore = {1, 2, 3, 4, 5};
        Task_2(numbersBefore);
    }

    public static void Task_1(String[] words) {
        //Напишете метод, който получава като параметър масив от
        //думи и отпечатва думите отзад напред.
        //Примерен вход: String[] words = {“this”, “is”, “my”, “test”,
        //“sentence”};
        //Примерен изход: sentence test my is this
        for (int i = 0; i < words.length; i++) {
            if (i != words.length - 1) {
                System.out.print(words[i] + " ");
            } else {
                System.out.println(words[i]);
            }
        }
    }

    public static void Task_2(int[] nums){
        //Създайте програма, която по подаден масив да създава
        //нов, но елементите му да са умножени по съответния
        //индекс в масива.
        //Пример:
        //int[] numbersBefore = {1, 2, 3, 4, 5};
        //int[] numbersAfter = {0, 2, 6, 12, 20};
        int[] numbersAfter = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
//            System.out.print((nums[i] *= i) + " ");
            numbersAfter += nums[i] * i;
        }

    }




}