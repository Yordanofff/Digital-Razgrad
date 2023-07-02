import java.util.Arrays;

public class Task4 {
    public static void main(String[] args) {
        // Task 4
        runApp();
    }

    public static void runApp() {
        int[][] matrix = {{7, 11, 2}, {2, 9, 18}, {9, 13, 15}};

        printLowestSum(matrix);
        printHighestSum(matrix);
    }

    public static void printLowestSum(int[][] matrix) {
        int[] col = getSmallestSumAndPositionColumns(matrix);
        int[] row = getSmallestSumAndPosition(matrix);
        int colOrRowNum = row[0];
        int value = row[1];
        String rowOrCol = "row";
        if (col[1] < row[1]) {
            rowOrCol = "column";
            colOrRowNum = col[0];
            value = col[1];
        }

        System.out.println("Lowest sum is from " + rowOrCol + " " + colOrRowNum + ": " + value);
    }

    public static void printHighestSum(int[][] matrix) {
        int[] col = getBiggestSumAndPositionColumns(matrix);
        int[] row = getBiggestSumAndPosition(matrix);
        int colOrRowNum = row[0];
        int value = row[1];
        String rowOrCol = "row";
        if (col[1] > row[1]) {
            rowOrCol = "column";
            colOrRowNum = col[0];
            value = col[1];
        }

        System.out.println("Highest sum is from " + rowOrCol + " " + colOrRowNum + ": " + value);
    }

    public static int[] getBiggestSumAndPosition(int[][] matrix) {
        int currentMin = Integer.MIN_VALUE;
        int rowNum = Integer.MIN_VALUE;
        int[] minRowAndSum = new int[2];

        for (int i = 0; i < matrix.length; i++) {

            int sumRow = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                sumRow += matrix[i][j];
            }

            if (sumRow > currentMin) {
                currentMin = sumRow;
                rowNum = i;
            }
        }
        minRowAndSum[0] = rowNum + 1;
        minRowAndSum[1] = currentMin;
        return minRowAndSum; // [2, 37]
    }

    public static int[] getBiggestSumAndPositionColumns(int[][] matrix) {
        int currentMin = Integer.MIN_VALUE;
        int colNum = Integer.MIN_VALUE;
        int[] minColAndSum = new int[2];

        for (int i = 0; i < matrix.length; i++) {
            int sumCol = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                sumCol += matrix[j][i];
            }
            if (sumCol > currentMin) {
                currentMin = sumCol;
                colNum = i;
            }
        }
        minColAndSum[0] = colNum + 1;
        minColAndSum[1] = currentMin;
        return minColAndSum; // [3, 35]
    }

    public static int[] getSmallestSumAndPosition(int[][] matrix) {
        int currentMin = Integer.MAX_VALUE;
        int rowNum = Integer.MAX_VALUE;
        int[] maxRowAndSum = new int[2];

        for (int i = 0; i < matrix.length; i++) {

            int sumRow = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                sumRow += matrix[i][j];
            }

            if (sumRow < currentMin) {
                currentMin = sumRow;
                rowNum = i;
            }
        }
        maxRowAndSum[0] = rowNum + 1;
        maxRowAndSum[1] = currentMin;
        return maxRowAndSum; // [2, 37]
    }

    public static int[] getSmallestSumAndPositionColumns(int[][] matrix) {
        int currentMin = Integer.MAX_VALUE;
        int colNum = Integer.MAX_VALUE;
        int[] minColAndSum = new int[2];

        for (int i = 0; i < matrix.length; i++) {
            int sumCol = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                sumCol += matrix[j][i];
            }
            if (sumCol < currentMin) {
                currentMin = sumCol;
                colNum = i;
            }
        }
        minColAndSum[0] = colNum + 1;
        minColAndSum[1] = currentMin;
        return minColAndSum; // [3, 35]
    }
}