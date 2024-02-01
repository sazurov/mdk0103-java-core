package base.fillArray;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

class Solution {
    public void fillArray (int[] arr) {
        Random rnd = new Random();
        for (int i = 0 ; i < arr.length ; i++) {
            arr[i] = rnd.nextInt(50 + 1);
        }
    }

    public void fillArrayWithEvenNums(int[] arr) {
        Random rnd = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextInt(25) * 2;
        }
    }

    public int findAbsoluteNum(int[] arr, boolean isMax) {
        int temp = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (isMax) {
                if (arr[i] > temp) {
                    temp = arr[i];
                }
            } else {
                if (arr[i] < temp) {
                    temp = arr[i];
                }
            }
        }
        return temp;
    }

    public float findAvg(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Invalid Array!");
        }
        int sum = Arrays.stream(arr).reduce(0, Integer::sum);
        return (float) sum / arr.length;
    }
}

public class Fill {
    public static void main(String[] args) {
        int[] array = new int[10];
        int[] evenNumsArr = new int[10];
        Solution solution = new Solution();

        solution.fillArray(array);
        solution.fillArrayWithEvenNums(evenNumsArr);

        int max = solution.findAbsoluteNum(array, true);
        int min = solution.findAbsoluteNum(array, false);
        float average = solution.findAvg(array);
        String avgRes = new DecimalFormat("#0.00").format(average);

        System.out.printf("\nМаксимальное значение: %d \nМинимальное значение: %d \nСреднее значение: %s", max, min, avgRes);
    }
}
