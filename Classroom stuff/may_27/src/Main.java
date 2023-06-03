import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] origMatrix = {1, 2, 3, 4, 5};
//        int[] result = matrixPlusReversed(origMatrix);
//        System.out.println(Arrays.toString(result));

//        System.out.println(Arrays.toString(matrixTask3(origMatrix)));

        int[] mirrored = {1, 2, 3, 3, 2, 1};
        System.out.println(isMirrored(mirrored));

        int a, b;
        for (int i = 0; i < 5; i++) {
            a = getFibonaci(i);
            b = getFibonaci()
            System.out.println();
        }
    }
    public static int[] matrixTask3(int[] matrix){
        int[] newMatrix = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            if ((i == 0) || (i == matrix.length -1)){
                newMatrix[i] = matrix[i];
            } else {
                newMatrix[i] = matrix[i-1] + matrix[i] + matrix[i+1];
            }
        }
        return newMatrix;
    }

    public static boolean isMirrored(int[] matrix){
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i] != matrix[matrix.length - i -1]){
                return false;
            }
        }
        return true;
    }

    public static int getFibonaci(int num) {
        int zero = 0;
        int one = 1;
        if (num == 0) {
            return num;
        }
        else if (num == 1) {
            return num;
        }
        else {
            for (int i = 0; i < num-1; i++) {

                int[] newArray =  
            }
        }
    }
    public static int[] matrixPlusReversed(int[] matrix) {
        int[] newMatrix = new int[matrix.length * 2];

        for (int i = 0; i < matrix.length; i++) {
            newMatrix[i] = matrix[i];
        }

        int x = 0;
        for (int i = matrix.length; i < newMatrix.length; i++) {
            newMatrix[i] = matrix[matrix.length - 1 - x];
            x += 1;
        }

        return newMatrix;
    }
}