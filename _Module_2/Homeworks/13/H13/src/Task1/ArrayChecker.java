package Task1;

public class ArrayChecker {
    public static boolean isMirroredArray(int[] arr) {
        if (arr.length <= 1) {
            return true;
        }
        int arrHalfLength = arr.length/2;  // Will be 2 when 5
        for (int i = 0; i < arrHalfLength; i++) {
            if (arr[i] != arr[arr.length-1-i]) {
                return false;
            }
        }
        return true;
    }
}
