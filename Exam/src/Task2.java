import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2 {
    static Scanner scanner = new Scanner(System.in);
    static int PRICE_PER_LETTER = 1; // leva

    static String[] SPECIAL_LETTERS = {"a", "e", "i", "o", "u"};
    static double SPECIAL_LETTERS_PRICE_MULTIPLIER = 1.2;  // 20%

    static DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public static void main(String[] args) {
        // Task 2:
        runApp();
    }

    public static void runApp() {
        int target = getTarget();
        List<String> allDishes = getDishes();

        double moneyMade = 0;
        for (String dish : allDishes) {
            double currentDishPrice = getDishPrice(dish);
            moneyMade += currentDishPrice;
        }
        double profitFormatted = convertToDoubleWithTwoSymbolsAfterDot(moneyMade - target);
        double protitNeededToTargetFormatted = convertToDoubleWithTwoSymbolsAfterDot(target - moneyMade);

        if (moneyMade >= target) {
            System.out.println("You reached your target and are " + (addZeroToEndOfDoubleIfSingleDigit(profitFormatted)) + "lv above!");
        } else {
            System.out.println("You did not reach your target! You need " + (addZeroToEndOfDoubleIfSingleDigit(protitNeededToTargetFormatted)) + "lv to reach the target.");
        }
    }

    public static String addZeroToEndOfDoubleIfSingleDigit(double num) {
        String numToString = String.valueOf(num);
        String coinsPart = numToString.split("\\.")[1];
        if (coinsPart.length() == 1) {
            coinsPart = coinsPart + "0";
        }
        return numToString.split("\\.")[0] + "." + coinsPart;
    }

    public static double convertToDoubleWithTwoSymbolsAfterDot(double num) {
        String numberFormatted = decimalFormat.format(num);
        return Double.parseDouble(numberFormatted);
    }

    public static int getTarget() {
        System.out.print("Въведете вашата цел в лева: ");
        int target = scanner.nextInt();
        scanner.nextLine();
        return target;
    }

    public static double getDishPrice(String dishName) {
        double price = getLenOfString(dishName) * PRICE_PER_LETTER;
        if (isDishStartingWithSpecialLetter(dishName)) {
            price *= SPECIAL_LETTERS_PRICE_MULTIPLIER;
        }

        return convertToDoubleWithTwoSymbolsAfterDot(price);
    }

    public static boolean isDishStartingWithSpecialLetter(String dishName) {
        for (String letter : SPECIAL_LETTERS) {
            if (dishName.toLowerCase().startsWith(letter)) {
                return true;
            }
        }
        return false;
    }

    public static int getLenOfString(String word) {
        return word.length();
    }

    public static List<String> getDishes() {
        List<String> dishes = new ArrayList<>();

        while (true) {
            System.out.print("Въведете ястие: ");
            String cmd = scanner.nextLine();
            if (cmd.equalsIgnoreCase("end")) {
                break;
            }
            dishes.add(cmd);
        }
        return dishes;
    }

}
