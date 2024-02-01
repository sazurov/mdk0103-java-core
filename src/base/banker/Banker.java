package base.banker;

import java.util.Scanner;

class Solution {
    private Scanner scanner;

    private int getDataFromUser(String text) {
        int res;
        System.out.println("Введите " + text + ":");
        res = scanner.nextInt();
        return res;
    }

    private static double calcTime(int startCapital, int target, double percentage) {
        double year = 0;
        while (startCapital < target) {
            startCapital += (int) (startCapital * percentage);
            year++;
        }
        return year;
    }

    public void countYears() {
        scanner = new Scanner(System.in);

        int startCapital = getDataFromUser("сумму вклада");
        int target = getDataFromUser("желаемую сумму");
        double percents = getDataFromUser("процент годовых") / 100.0;
        double year = calcTime(startCapital, target, percents);

        System.out.println("Чтобы достичь желаемой суммы, потребуется: " + year + " лет");

    }
}

public class Banker {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.countYears();
    }
}
