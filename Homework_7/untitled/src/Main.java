import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        printResult1toNifDevidedBy3or5(10);
        printResult1toNifDevidedBy3or5(40);
    }

    // Task 1
    public static void printResult1toNifDevidedBy3or5(int n) {
        System.out.println("Всички числа в диапазона (1-" + n + "), които се делят пълно на 3 и 5: " + getListOfNums1toNifDevidedBy3or5(n) + " => " + sum1ToNifDevidedBy3or5(n));
    }

    public static Integer sum1ToNifDevidedBy3or5(int n) {
        //Направете метод, който намира сумата на всички числа от 1 до n, които се
        //делят на 3 или на 5. Извикайте метода 2 пъти с различни аргументи.
        int sum = 0;

        for (int i=0; i <= n; i++){
            if ((i % 3 == 0) || (i % 5 == 0)) {
                sum += i;
            }
        }

        return sum;
    }

    public static List<Integer> getListOfNums1toNifDevidedBy3or5(int n) {
        // will return a list with all numbers in the range 1-n if dividable fully by 3 or 5.
        List<Integer> numbers = new ArrayList<>();

        for (int i=1; i <= n; i++){
            if ((i % 3 == 0) || (i % 5 == 0)) {
                numbers.add(i);
            }
        }

        return numbers;
    }

    // Task 2
    
}