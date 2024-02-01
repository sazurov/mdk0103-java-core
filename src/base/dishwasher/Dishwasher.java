package base.dishwasher;

import java.util.Scanner;

class Solution {
    Scanner scan = new Scanner(System.in);
    private static int dish = 0;
    private double cleanser;

    private int getData (String text) {
        System.out.println("Введите количество " + text + ":");
        return scan.nextInt();
    }

    public void wash () {
        dish = getData("тарелок");
        cleanser = getData("моющего средства");

        for (int i = 0; i < dish ; i++) {
            cleanser -= 0.5;
            if ( cleanser < 0 ) {
               throw new Error("Закончилось моющее средство, вымыто " + i + " тарелок");
            }
            System.out.println(cleanser);
        }
    }
}

public class Dishwasher {
    public static void main (String[] args) {
    Solution sol = new Solution();
    sol.wash();
    }
}
