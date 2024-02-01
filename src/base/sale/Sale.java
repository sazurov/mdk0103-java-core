package base.sale;

import java.util.Scanner;

class Solution {
    Scanner scanner = new Scanner(System.in);

   private Sales[] arr = {
            new Sales(4525 , 30) , new Sales(6424 , 20), new Sales(7012, 20) ,
            new Sales(7647 ,10) , new Sales(9011 , 10) , new Sales(6612 , 10)
    };

    public int getData (String text) {
        System.out.println("Введите " + text + ":");
        return scanner.nextInt();
    }

    public void defineDiscount ( double price ,int promocode) {
      double discount = 0.0;

        for (Sales promo : arr) {
            if (promocode == promo.getName()) {
                discount = price * promo.getDiscount() / 100.0;
                break;
            }
            else {
                throw new Error("Промокод не существует!");
            }
        }
        double totalPrice = price - discount;
        System.out.println("Сумма покупки со скидкой составляет: " + totalPrice);
    }
}

class Sales {
   private int name;
   private int discount;

    public Sales (int name , int discount) {
        this.name = name;
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public int getName() {
        return name;
    }
}

public class Sale {
    public static void main (String[] args) {
        Solution sol = new Solution();

        int promo = sol.getData("промокод");
        int price = sol.getData("цену товара");
        sol.defineDiscount( price ,promo);
    }
}
