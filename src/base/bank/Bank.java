package base.bank;

import java.util.Scanner;

class Customer  {
    private String name;
    private String docs;

    public Customer (String name , String docs) {
        this.name = name;
        this.docs = docs;
    }

    public String getName() {
        return name;
    }

    public String getDocs() {
        return docs;
    }
}

class Solution {
    private Scanner scan = new Scanner(System.in);
    private int customersCount = 0;
    private Customer[] customers;

    private String getData(String text) {
        System.out.println(text);
        return scan.nextLine();
    }

    private boolean validate(Customer[] arr) {
        return arr.length == customersCount;
    }

    public void fillArr() {
        customersCount = Integer.parseInt(getData("Введите количество участников в сделке"));
        customers = new Customer[customersCount];
        System.out.println("Внесите документы, удостоверяющие личность участника: ");

        for (int i = 0; i < customersCount; i++) {
            String name = getData("Имя:");
            String docs = getData("Документ, удостоверяющий личность:");
            customers[i] = new Customer(name, docs);
        }

        if (validate(customers)) {
            System.out.println("Данные успешно занесены:");
            for (Customer doc : customers) {
                System.out.println(doc.getName() + ": " + doc.getDocs());
            }
        } else {
            System.out.println("Ошибка при вводе данных участников сделки!");
        }
    }
}


public class Bank {
    public static void main (String[] args) {
        Solution sol = new Solution();
        sol.fillArr();
    }
}
