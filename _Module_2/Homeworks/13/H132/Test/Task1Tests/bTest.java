package Task1Tests;

import Task1.b;
import org.junit.Assert;
import org.junit.Test;

public class bTest {
    @Test
    public void testOneDigitArrayThenReturnTrue() {
        int[] arr = {1};
        int[] expected = {1};
        int[] result = b.newArraySumsOfPreviousAndNext(arr);
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testTwoDigitArrayThenReturnTrue() {
        int[] arr = {1, 2};
        int[] expected = {1, 2};
        int[] result = b.newArraySumsOfPreviousAndNext(arr);
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testThreeDigitArrayThenReturnTrue() {
        int[] arr = {1, 2, 3};
        int[] expected = {1, 4, 3};
        int[] result = b.newArraySumsOfPreviousAndNext(arr);
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testFourDigitArrayThenReturnTrue() {
        int[] arr = {1, 2, 3, 4};
        int[] expected = {1, 4, 6, 4};
        int[] result = b.newArraySumsOfPreviousAndNext(arr);
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testFiveDigitArrayThenReturnTrue() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 4, 6, 8, 5};
        int[] result = b.newArraySumsOfPreviousAndNext(arr);
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testSixDigitArrayThenReturnTrue() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] expected = {1, 4, 6, 8, 10, 6};
        int[] result = b.newArraySumsOfPreviousAndNext(arr);
        Assert.assertArrayEquals(expected, result);
    }
}
