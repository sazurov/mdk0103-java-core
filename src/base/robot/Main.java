package base.robot;

import java.time.LocalTime;

class Solution {

    LocalTime currentTime = LocalTime.now();


    public void sayHello () {
        int currentHour = currentTime.getHour();

        if (currentHour >= 4 && currentHour < 12) {
            System.out.println("Доброе утро!");
        }
        else if (currentHour >= 12 && currentHour < 18) {
            System.out.println("Добрый день!");
        }
        else if (currentHour >= 18 && currentHour < 23) {
            System.out.println("Добрый вечер!");
        }
        else {
            System.out.println("Доброй ночи! Приятных сновидений!");
        }

    }
}


public class Main {
    public static void main (String[] args) {
        Solution sol = new Solution();
        sol.sayHello();
    }
}
