import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Task 1
//        printResult1toNifDevidedBy3or5(10);
//        printResult1toNifDevidedBy3or5(40);

        // Task 2
//        printNumsFromXto1(5);

        // Task 3
        printPlayerNames();

    }

    // Task 1
    //Направете метод, който намира сумата на всички числа от 1 до n, които се
    //делят на 3 или на 5. Извикайте метода 2 пъти с различни аргументи.
    public static void printResult1toNifDevidedBy3or5(int n) {
        System.out.println("Всички числа в диапазона (1-" + n + "), които се делят пълно на 3 и 5: " + getListOfNums1toNifDevidedBy3or5(n) + " => " + sum1ToNifDevidedBy3or5(n));
        System.out.println("(Втори вариант) Всички числа в диапазона (1-" + n + "), които се делят пълно на 3 и 5: " + getListOfNums1toNifDevidedBy3or5(n) + " => " + sumNumbersInList(getListOfNums1toNifDevidedBy3or5(n)));
    }

    public static Integer sum1ToNifDevidedBy3or5(int n) {

        int sum = 0;

        for (int i=0; i <= n; i++){
            if ((i % 3 == 0) || (i % 5 == 0)) {
                sum += i;
            }
        }

        return sum;
    }

    public static Integer sumNumbersInList(List<Integer> listOfNums) {
        // Втори вариант, без повторна проверка за делене. Връща сумата на всички числа в лист.
        int sum = 0;
        for(int i = 0; i < listOfNums.size(); i++)
            sum += listOfNums.get(i);
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
    //Направете метод, който отпечатва числата от х към 1.
    public static void printNumsFromXto1(int x) {
        for (int i=x; i>=1; i--){
            if (i == 1) {
                System.out.println(i);
            } else {
                System.out.print(i + ", ");
            }
        }
    }

    // Task 3
    // Направете метод, който приема като параметър име на спорт и отпечатва
    //списък с номерирани играчи:
    //player 1
    //player 2
    //player 3
    //…
    //player N
    //Възможните спортове са: футбол - 11 играча, волейбол - 6 играча,
    //баскетбол - 5 играча.
    //Пример за баскетбол:
    //player 1
    //player 2
    //player 3
    //player 4
    //player 5
    public static void printPlayerNames() {
        System.out.println("Въведете номера на спорта за който желаете да принтирате иметната на футболистите: \n" +
                "1: Футбол \n2: Волейбол \n3: Баскетбол");

        Scanner scanner = new Scanner(System.in);
        int result = scanner.nextInt();

        switch (result) {
            case 1: printNumberOfPlayers(11); break;
            case 2: printNumberOfPlayers(6); break;
            case 3: printNumberOfPlayers(5); break;
            default:
                System.out.println("Wrong input. Try again.");
                printPlayerNames();
//                break;
        }
    }

    public static void printNumberOfPlayers(int n) {
        System.out.println("Имената на играчите са: ");
        for (int i=1; i<=n; i++){
            System.out.println("Player " + i);
        }
    }
}