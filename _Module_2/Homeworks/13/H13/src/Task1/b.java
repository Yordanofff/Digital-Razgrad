package Task1;

import java.util.Arrays;

public class b {
    //Напишете метод, който приема масив, създава и връща нов масив със
    //същата дължина като първия по следния начин: всеки елемент от
    //новия масив с индекс n е равен на сбора от елементите от оригиналния
    //масив с индекси n-1 и n+1. Първият и последният елемент от новия
    //масив остават равни на оригиналните.
    //Примерен вход: {1, 2, 3, 4} Примерен изход: {1, 4, 6, 4}
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4};
        System.out.println(Arrays.toString(newArraySumsOfPreviousAndNext(input)));
    }

    public static int[] newArraySumsOfPreviousAndNext(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || i == arr.length - 1) {
                newArr[i] = arr[i];
            } else {
                newArr[i] = arr[i - 1] + arr[i + 1];
            }
        }
        return newArr;
    }
}
