package refactored;

public class Main2 {
    public static void main(String[] args) {
        char monkeyATriangle = '@';
        printTriangleWithSameCharacter(monkeyATriangle, 4);
    }

    public static void printTriangleWithSameCharacter(char ch, int numRows) {
        for (int i = 1; i <= numRows; i++) {
            System.out.println(" ".repeat(numRows - i) + String.valueOf(ch).repeat(i));
        }
    }
}

