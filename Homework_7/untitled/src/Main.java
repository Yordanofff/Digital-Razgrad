import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Task 1
//        printResult1toNifDevidedBy3or5(10);
//        printResult1toNifDevidedBy3or5(40);

        // Task 2
//        printNumsFromXto1(5);

        // Task 3
//        printPlayerNames();

        // Task 4
//        playGame();

        // Task 5
//        System.out.println("Въведете число което искате да проверите дали е перфектно: ");
//        int isPerfectNumberToCheck = scanner.nextInt();
//        if (isPerfect(isPerfectNumberToCheck)) {
//            System.out.println("It is perfect!");
//        } else {
//            System.out.println(isPerfectNumberToCheck + " is not perfect");
//        }

        // Task 6
//        task6();
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
        System.out.println("""
                Въведете номера на спорта за който желаете да принтирате иметната на футболистите:\s
                1: Футбол\s
                2: Волейбол\s
                3: Баскетбол""");

        int result = scanner.nextInt();

        switch (result) {
            case 1 -> printNumberOfPlayers(11);
            case 2 -> printNumberOfPlayers(6);
            case 3 -> printNumberOfPlayers(5);
            default -> {
                System.out.println("Wrong input. Try again.");
                printPlayerNames();
            }
        }
    }

    public static void printNumberOfPlayers(int n) {
        System.out.println("Имената на играчите са: ");
        for (int i=1; i<=n; i++){
            System.out.println("Player " + i);
        }
    }


    // Task 4
    //Напишете играта “Отгатни числото”. Програмата кара компютъра да си
    //намисли едно случайно число между 1 и 20, след което пита потребителя
    //да въведе предположение. Ако предположението му не е вярно,
    //програмата го моли да въведе ново число. Играта свършва, когато
    //потребителят познае числото.
    //Принтирайте “You guessed right, <number> is the correct number”, когато играта
    //свърши.
    //Създайте метод playGame, където ще извършвате цикъла.
    //Hint: Как генерираме случайни числа?
    //Random rand = new Random();
    //int n = rand.nextInt(20) + 1;

    //Модифицирайте задача 1, извеждайки насоки след всеки опит на
    //потребителя - ако числото, което сме въвели, е по-голямо от намисленото,
    //извеждаме “Too high, try again!”, ако пък е по-малко - “Too low, try again!”.

    public static void playGame() {

        // Range for the random number
        int start_number = 1;
        int end_number = 20;

        // Get a random number
        Random rand = new Random();
        int numberToGuess = rand.nextInt(end_number) + 1;

        // Try to guess it - play the game
        if (numberCheck(start_number, end_number, numberToGuess, 0)) {
            if (anotherGame()) {
                playGame();
            }
        }
    }

    public static boolean anotherGame() {
        // Ask the user if they want to play another game. True/False/Rerun.

        System.out.println("Искате ли още една игра? [Y/N]");
        String ans = scanner.next();

        if (ans.equalsIgnoreCase("y")) {
            return true;
        } else if (ans.equalsIgnoreCase("n")) {
            System.out.println("Bye bye :)");
            return false;
        } else {
            System.out.println("Грешен отговор. Моля въведете y/n.");
            return anotherGame();
        }
    }

    public static boolean numberCheck(int lowest, int highest, int randomNumber, int attempts) {

        // Print number of tries after the first attempt.
        if (attempts > 1) {
            System.out.println("Пробвахте " + attempts + " пъти. Въведете число от " + lowest + " до " + highest + ":");
        } else {
            System.out.println("Въведете число от " + lowest + " до " + highest + ":");
        }
        attempts += 1;

        int guess = scanner.nextInt();

        if (guess > randomNumber) {

            // update the highest number so that the new print statement shows the new range
            if (guess < highest) {
                highest = guess;
            }

            System.out.println("Too high, try again!");
            return numberCheck(lowest, highest, randomNumber, attempts);

        } else if (guess < randomNumber) {

            // update the lowest number so that the new print statement shows the new range
            if (guess > lowest) {
                lowest = guess;
            }

            System.out.println("Too low, try again!");
            return numberCheck(lowest, highest, randomNumber, attempts);

        } else {
            System.out.println("You guessed it right after " + attempts + " attempts. \"" + randomNumber + "\" is the correct number.");
            return true;
        }
    }


    // Task 5
    //Напишете програма, която проверява дали едно число е “съвършено”.
    //Едно число е съвършено, ако е равно на сумата от всичките си делители
    //(без самото число, разбира се).
    //Ако числото е съвършено принтирайте “It is perfect”
    //Ако не е съвършено - принтирайте “<числото> is not perfect”
    //Създайте метод isNumberPerfect, който определя дали едно число е
    //перфектно.
    //Пример за перфектно число:
    //всички делители на 6 са 1, 2 и 3
    //съответно 6 е съвършено число,
    //защото 1+2+3=6

    public static boolean isPerfect(int number) {
        int sum = 0;

        // Find all dividers and add them to the integer "sum"
        for (int i=1; i<=number-1; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }

        return sum == number;
    }


    // Task 6 (творческа)
    // Направете аналогия между цикъл while/do-while и
    //училище. Постарайте се аналогията да е всеобхватна,
    //т.е. да прави съпоставка между възможно най-много
    //характеристики на циклите и училище. Описанието
    //направете с текст - цели изречения.
    //Задачата опишете в текстов файл (.docx / .txt)

    public static void task6() {
        System.out.println("Разликата между do-while и while циклите е, че do-while ще се изпълни поне един път, дори и условието да е невалидно още в самото начало.");
    }

}