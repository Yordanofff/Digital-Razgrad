package Test;

import Task1.ArrayChecker;
import org.junit.Assert;
import org.junit.Test;

public class ArrayCheckerTest {
    @Test
    public void testArrayWithZeroLengthThenReturnTrue() {
        int[] arr = {};
        boolean result = ArrayChecker.isMirroredArray(arr);
        Assert.assertTrue(result);
    }

    @Test
    public void testArrayWithLengthOneThenReturnTrue() {
        int[] arr = {};
        boolean result = ArrayChecker.isMirroredArray(arr);
        Assert.assertTrue(result);
    }

    @Test
    public void testArrayWithEvenLengthThenReturnTrue() {
        int[] arr = {1, 2, 3, 3, 2, 1};
        boolean result = ArrayChecker.isMirroredArray(arr);
        Assert.assertTrue(result);
    }

    @Test
    public void testArrayWithOddLengthThenReturnTrue() {
        int[] arr = {1, 2, 3, 2, 1};
        boolean result = ArrayChecker.isMirroredArray(arr);
        Assert.assertTrue(result);
    }

    @Test
    public void testArrayWithOddLengthThenReturnFalse() {
        int[] arr = {1, 3, 3, 2, 1};
        boolean result = ArrayChecker.isMirroredArray(arr);
        Assert.assertFalse(result);
    }

    @Test
    public void testArrayWithEvenLengthThenReturnFalse() {
        int[] arr = {1, 3, 3, 2, 2, 1};
        boolean result = ArrayChecker.isMirroredArray(arr);
        Assert.assertFalse(result);
    }

}
