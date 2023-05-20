import java.util.Arrays;
import java.util.Random;

public class Main {
    //в) Намерете броя на оценките над 6 в цялата матрица.
    public static void countHigherRates(int[][] mat, int rate) {
        int count = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] > rate) {
                    count++;
                }
            }
        }
        System.out.println("the count of movie rates above " + rate + " is: " + count);
    }

    public static int[][] printPositionHigherScore(int[][] matrix, int num) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        int[][] newMatrix = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j] > num) {
                    newMatrix[i][j] = 1;
                } else {
                    newMatrix[i][j] = 0;
                }
            }
        }
        return newMatrix;
    }

    public static double[][] getSums(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        double[][] result = new double[numRows][numCols + 1];
        for (int i = 0; i < numRows; i++) {
            double res = averageRates(matrix, i);
            //todo
        }
//        for (int i = 0; i < numRows+1; i++) {
//            for (int j = 0; j < numCols; j++) {
//
//            }
//            for (int j = 0; j < numCols; j++) {
//                result[i][j+1] = matrix[i][j];
//            }
//        }
        return result;
    }


    //б)Намерете каква е средната стойност на оценките,  дадени от рецензент #2
    public static double averageRates(int[][] matrix, int criticIndex) {
        double sum = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            sum = sum + matrix[criticIndex][i];
        }
        return sum / matrix[0].length;
    }

    //Имате 3 критици, всеки от които е дал оценка за 4  филма.
    public static int[][] generateMovieCritics(int rows, int cols) {
        Random rnd = new Random();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rnd.nextInt(10) + 1;
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printMovieCritics2(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] Task1() {
        Random random = new Random();
        int[][] matrix = new int[2][5];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = random.nextInt(11);
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        return matrix;
    }

    public static int[][] Task2() {
        int[][] matrix = new int[3][6];
        int counter = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = counter;
                counter += 1;
            }
        }
        return matrix;
    }

    public static int sumUpAllEven(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] % 2 == 0) {
                    sum += matrix[i][j];
                }
            }
        }
        return sum;
    }

    public static int[][] Task3() {
        int[][] matrix = new int[4][5];
        int num = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                num += 2;
                matrix[i][j] = num;
            }
            num = num * 2 - 2;
        }
        return matrix;
    }

    public static int getSumByRow(int[][] matrix, int rowNumber) {
        int sum = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            sum += matrix[rowNumber][i];
        }
        return sum;
    }

    public static void printMatrixAndSumOfEachRow(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(" Sum: " + getSumByRow(matrix, i));
        }
    }

    public static int[] moveRight(int[] position) {
        int x = position[0];
        int y = position[1];
        return new int[] {x, y+1};
    }
    public static int[] moveLeft(int[] position) {
        int x = position[0];
        int y = position[1];
        return new int[] {x, y-1};
    }
    public static int[] moveDown(int[] position) {
        int x = position[0];
        int y = position[1];
        return new int[] {x+1, y};
    }
    public static int[] moveUp(int[] position) {
        int x = position[0];
        int y = position[1];
        return new int[] {x, y-1};
    }
    public static int[][] Task4(int n) {
        // 00, 0 n-4, 0 n-3, 0 n-2, 0 n-1, 1n-1, 2n-1, 3n-1, 4n-1, 3 n-1, 2 n-1 ,1 n-1, 0 n-1
        // n-n n-(n-1), n-(n-2),


        int[][] matrix = new int[n][n];


        //print first row
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                System.out.print(i + "," + j + " - ");
//            }
//            System.out.println();
//        }
        int counter = 0;
        int draw_at = matrix.length; // 5

//        for (int i = 0; i < draw_at; i++) {
//            matrix[0][i] = counter;
//            counter += 1;
//        }

        int numbersToPrint = n;
//        while (counter != n*n) {
        int[] matrixPosition = {0, 0};

        for (int i = 0; i < numbersToPrint; i++) {
            int x, y;
            x = matrixPosition[0];
            y = matrixPosition[1];
            matrix[x][y] = counter;
            if (y == numbersToPrint-1) {
                break;
            }
            matrixPosition = moveRight(matrixPosition);

            System.out.println(x + " - " + y);
                counter += 1;
        }

        numbersToPrint -= 1;

        for (int i = 0; i < numbersToPrint; i++) {
            int x, y;
            x = matrixPosition[0];
            y = matrixPosition[1];
//            matrix[x][y] = counter;
            matrixPosition = moveDown(matrixPosition);

            System.out.println(x + " - " + y);
            counter += 1;
        }

//        }

//        draw_at -= 1; // 4
//        for (int i = 0; i < draw_at; i++) {
//            matrix[i][draw_at] = counter;
//            counter += 1;
//        }
//        System.out.println(counter + "==");
//        for (int i = 0; i <= draw_at; i++) {
//            matrix[draw_at][draw_at - i] = counter;
//            counter += 1;
//        }
//        draw_at -= 1; // 3
//        for (int i = 0; i < draw_at; i++) {
//            matrix[0][draw_at] = counter;
//            counter += 1;
//        }


//        System.out.println(counter + "==");
//        for (int i = 0; i <= draw_at; i++) {
//            matrix[draw_at][draw_at - i] = counter;
//            counter += 1;
//        }


//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                //1x5 //
//                //2x4
//                //2x3
//                //2x2
//                //1x1
//                //1x1
//            }
//        }

//        int row = 0;
//        int col = 0;
//        int numToPrint = n;
//
//        for (int j = 0; j < numToPrint; j++) {
//            matrix[row][col] = counter;
//            counter += 1;
//            col += 1;
//        }
//        numToPrint -= 1;  // 2
//        for (int j = 0; j < numToPrint; j++) {
//
//        }
//
        return matrix;
    }

    public static int[][] T4(int size) {
        int conterToTwo = 1;
        //1x5, 2x4, 2x3, 2x2, 2x1 if size = 5
        int row = 0, col = 0;

        int boundary = size - 1;
        int sizeLeft = size - 1;

        char move = 'r';

        int[][] matrix = new int[size][size];

        for (int i = 1; i < size * size + 1; i++) {

            matrix[row][col] = i;

            switch (move) {
                case 'r' -> col += 1;
                case 'l' -> col -= 1;
                case 'u' -> row -= 1;
                case 'd' -> row += 1;
            }

            if (i == boundary) {
                // Add the left size for the next boundary
                boundary += sizeLeft;

                if (conterToTwo % 2 == 0) {
                    sizeLeft -= 1;
                }
                conterToTwo+=1;

                switch (move) {
                    // if right, rotate to down
                    case 'r' -> move = 'd';
                    // if down, rotate to left
                    case 'd' -> move = 'l';
                    // if left, rotate to up
                    case 'l' -> move = 'u';
                    // if up, rotate to right
                    case 'u' -> move = 'r';
                }
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[3][4];
//
//        int rows = 3;
//        int cols = 4;
//        int[][] movieCritic = generateMovieCritics(rows, cols);
//        printMovieCritics(movieCritic);
//        averageRates(movieCritic, 1);
//        countHigherRates(movieCritic, 7);
//        int[][] positions = printPositionHigherScore(movieCritic, 7);
//        printMovieCritics(positions);
//        getSums(movieCritic);
//        double[][] sum = getSums(movieCritic); // todo
//        printMovieCritics2(sum);

//        int[][] matrixTask1 = Task1();
//        printMatrix(matrixTask1);
//
//        int[][] matrixTask2 = Task2();
//        printMatrix(matrixTask2);
//        int sumEven = sumUpAllEven(matrixTask2);
//        System.out.println("The sum of all even numbers in the matrix is: " + sumEven);
//
//        int[][] matrixTask3 = Task3();
//        printMatrix(matrixTask3);
//        printMatrixAndSumOfEachRow(matrixTask3);
//
//        int[][] matrixTask4 = Task4(5);
        //1 x 6
        //2 x 5
        //2 x 4
        //2 x 3
        //2 x 2
        //1 x 1
        //1 x 1

//        printMatrix(matrixTask4);
//        int[] test = {5, 6};
//        System.out.println(Arrays.toString(moveRight(test)));
            int[][] matrixTask4 = T4(5);
            printMatrix(matrixTask4);
    }

}