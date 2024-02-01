package base.activity;

import java.util.Arrays;
import java.util.Random;

class Solution {
    private static final int min = 0;
    private static final int max = 1000;

    public static void fillArrayAndDisplay (int[] arr) {
        Random rnd = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextInt(max - min + 1) + min;
            System.out.println(arr[i]);
        }
        int sum = Arrays.stream(arr).reduce(0, Integer::sum);
        System.out.println("Общая сумма трат за неделю: " + sum);
    }

}

public class Activity {
    public static void main (String[] args) {
        int[] expense = new int[7];
        Solution.fillArrayAndDisplay(expense);
    }
}
