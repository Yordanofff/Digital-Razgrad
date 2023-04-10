// Да се напише програма, която отпечатва квадрат

public class zadacha_2 {

    public static void main(String[] args) {
        String topBottom = "—"; // "—" "-"
        String sides = "|";

        // Change for different square size
        int squareSize = 9;

        // This might need to be changed if different symbols are used
        int newNumberToMakeItLookBetter = (int)Math.round(0.5 * squareSize) + (squareSize * 2);

        // print top
        System.out.println(" " + topBottom.repeat(newNumberToMakeItLookBetter));

        for (byte i = 1; i <= squareSize; i++) {
            System.out.println(sides + " ".repeat(newNumberToMakeItLookBetter) + sides);
        }

        // print bottom
        System.out.println(" " + topBottom.repeat(newNumberToMakeItLookBetter));
    }
}
