package base.game;

import java.util.Random;
import java.util.Scanner;

class Solution {
    public void guessNumber () {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int secretNumber = random.nextInt(9) + 1;

        int attempts = 3;

        System.out.println("Компьютер загадал число от 1 до 9. Имеется всего 3 попытки.");

        while (attempts > 0) {
            System.out.print("Введите ваше число: ");
            int guessedNumber = scanner.nextInt();

            if (guessedNumber == secretNumber) {
                System.out.println("Вы угадали число!");
                break;
            } else if (guessedNumber < secretNumber) {
                System.out.println("Загаданное число больше!");
            } else {
                System.out.println("Загаданное число меньше!");
            }

            attempts--;
        }

        if (attempts == 0) {
            System.out.println("Попытки закончились! Загаданное число было: " + secretNumber);
        }

        scanner.close();
    }
}

public class Main {
    public static void main (String[] args) {
    Solution sol = new Solution();
    sol.guessNumber();
    }
}
