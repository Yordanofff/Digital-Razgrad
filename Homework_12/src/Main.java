import java.awt.image.RasterOp;
import java.util.Arrays;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

//        int[] arrayTask1 = {1, 2, 3, 1, 5, 8, 5};
//        int[] resultTask1 = Task_1(arrayTask1);
//        System.out.println(Arrays.toString(resultTask1));


//        int[][] matrixTask2 = {
//                {2, 4, 5, 1},
//                {3, 7, 8, 5},
//                {9, 5, 0, 4}};
//        int[] resultTask2 = Task_2(matrixTask2);
//        System.out.println(Arrays.toString(resultTask2));


//        int[][] matrix1Task3 = {
//                {1, 2, 3},
//                {4, 5, 6}};
//        int[][] matrix2Task3 = {
//                {7, 8},
//                {9, 10},
//                {11, 12}};
//        int[][] resultTask3 = Task_3(matrix1Task3, matrix2Task3);
//        for (int i = 0; i < resultTask3.length; i++) {
//            System.out.println(Arrays.toString(resultTask3[i]));
//        }


        String[][] board = {
                {" _ ", " _ ", " _ "},
                {" _ ", " _ ", " _ "},
                {" _ ", " _ ", " _ "}
        };
        playGame(board);
    }

    public static int[] Task_1(int[] array) {
        //Напишете метод, който приема едномерен масив като
        //аргумент и отпечатва всички повтарящи се елементи в масива.
        //Пример: 1 2 3 1 5 8 5
        //Резултат: 1 5

        // Count how many numbers are repeating
        int totalMoreThanOnce = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    totalMoreThanOnce += 1;
                }
            }
        }

        // Create and populate the array
        int[] repeatingItems = new int[totalMoreThanOnce];
        int writtenCount = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    repeatingItems[writtenCount] = array[i];
                    writtenCount += 1;
                }
            }
        }

        return repeatingItems;
    }

    public static int[] Task_2(int[][] matrix) {
        //Напишете метод, който приема матрица като аргумент и
        //връща едномерен масив, съдържащ всички четни числа от
        //матрицата.
        //Пример:
        //2 4 5 1
        //3 7 8 5
        //9 5 0 4
        //Резултат:
        //[2, 4, 8, 0, 4]

        int counter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] % 2 == 0) {
                    counter += 1;
                }
            }
        }

        int[] result = new int[counter];
        int writingCounter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] % 2 == 0) {
                    result[writingCounter] = matrix[i][j];
                    writingCounter += 1;
                }
            }
        }
        return result;
    }

    public static int[][] Task_3(int[][] matrix1, int[][] matrix2) {
        //Напишете метод, който приема две матрици и връща
        //матрицата, получена от умножението им.
        //*приемаме, че подадените матрици са с подходящи
        //размери
        //*как се умножават матрици:
        //https://www.mathsisfun.com/algebra/matrix-multiplying.html

        // The number of columns of the 1st matrix must equal the number of rows of the 2nd matrix.
        //And the result will have the same number of rows as the 1st matrix, and the same number of columns as the 2nd matrix.

        int[][] result = new int[matrix1.length][matrix2[0].length];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix2.length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;

    }


    // Task 4 below:
    public static void printBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean isWin(String[][] board) {

        // check rows
        for (int i = 0; i < 3; i++) {
            if ((board[i][0]).equals((board[i][1])) && (board[i][0]).equals((board[i][2])) && !(board[i][0]).equals(" _ ")) {
                System.out.println("T4");
                return true;
            }
        }

        // check cols
        for (int i = 0; i < 3; i++) {
            if ((board[0][i]).equals((board[1][i])) && (board[0][i]).equals((board[2][i])) && !(board[0][i]).equals(" _ ")) {
                System.out.println("T3"+ i + (board[0][i]) + (board[1][i]) + (board[2][i]));
                return true;
            }
        }

        // check diagonal "\"
        if ((board[0][0]).equals((board[1][1])) && ((board[0][0]).equals((board[2][2])) && !(board[0][0]).equals(" _ "))) {
            System.out.println("T1");
            return true;
        }

        // check diagonal "/"
        if ((board[0][2]).equals((board[1][1])) && ((board[0][2]).equals((board[2][0])) && !(board[0][2]).equals(" _ "))) {
            System.out.println("T2");
            return true;
        }
        return false;
    }

    public static boolean isBoardFull(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[1].length; j++) {
                if ((board[i][j]).equals(" _ ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isPossible(String[][] board, int a, int b) {
        if (a < 1 || a > 3 || b < 1 || b > 3) {
            System.out.println("Позицията която избрахте е извън фигурата. Пробвай пак!");
            return false;
        } else if (!(board[a - 1][b - 1]).equals(" _ ")) {
            System.out.println("Позицията вече е заета. Пробвай пак!");
            return false;
        }
        return true;
    }

    public static void playGame(String[][] board) {
        //Бонус задача - не е задължителна
        //Реализирайте играта “морски шах”:
        //Имаме матрица 3х3.
        //Играта се играе от 2ма играчи, които се редуват да избират
        //координатите, на които искат да поставят своя знак (х или о).
        //Първи започва о.
        //Печели този, който първи запълни ред, колона или диагонал.
        //След всеки ход дъската се визуализира в конзолата, като на
        //празните места има символи .
        //Когато играч спечели, в конзолата се изписва: Player X wins! (където Х
        //е 1 или 2)
        Scanner scanner = new Scanner(System.in);
        System.out.println("МОРСКИ ШАХ");
        String symbol;

        while (true) {
            if (isWin(board) || isBoardFull(board)) {
                break;
            }
            int player = 0;
            symbol = " o ";
            int hasChanged = 0;
            while (true) {

                if (player % 2 == 1) {
                    symbol = " x ";
                }

                printBoard(board);
                System.out.print("Играч " + (player + 1) + " [" + symbol + "]: Въведете две числа - ред и колона, където да поставите символ:");

                String userInput = scanner.nextLine();

                int a = Integer.parseInt(userInput.split(" ")[0]);
                int b = Integer.parseInt(userInput.split(" ")[1]);

                if (isPossible(board, a, b)) {
                    board[a - 1][b - 1] = symbol;
                    if (isWin(board)) {
                        System.out.println("Player " + (player + 1) + " WON the game!!");
                        break;
                    }
                    player += 1;
                    hasChanged += 1;
                }

                if (symbol.equals(" x ") && hasChanged == 2) {
                    break;
                }
                if (isBoardFull(board)) {
                    System.out.println("Няма свободни позиции. Никой не печели играта.");
                    break;
                }
            }
        }
    }
}