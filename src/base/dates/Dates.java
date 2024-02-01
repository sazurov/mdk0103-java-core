package base.dates;

import java.time.Year;
import java.util.Scanner;

class Solution {

    private int getDateFromUser () {
        Scanner scannerDate = new Scanner(System.in);
        System.out.print("Введите год, начиная с 15 года и по сегодняшний день :");
        return scannerDate.nextInt();
    }

    public String validateYear () {
        int year =  this.getDateFromUser();

        int currentYear = Year.now().getValue();

       if (year > currentYear || year < 15) {
           return "Неккорекнтое значение! Год должен быть больше 15 и не больше " + currentYear ;
       }

       int vek = (year - 1) / 100 + 1;

       return String.format("%d год принадлежит %d веку" , year , vek);
    }
}

public class Dates {
    public static void main (String[] args) {
        Solution solution = new Solution();

        String str = solution.validateYear();

        System.out.print(str);

    }
}
