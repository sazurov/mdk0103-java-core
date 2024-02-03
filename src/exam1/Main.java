package exam1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class FinancialCalculator {
   private Scanner scanner = new Scanner(System.in);
   private Map<Integer , BigDecimal> expenses = new HashMap<>();
   private Map<String , BigDecimal> bank = new HashMap<>();

   private int PICK = 5;

   public void init() {
       while (PICK !=0) {
           switch (PICK) {
               case 1:
                   pushExpense();
                   break;
               case 2:
                   displayAllExpenses();
                   break;
               case 3:
                   displayMaxExpenses();
                   break;
               case 4:
                   addCurrency();
                    converter();
                   break;
               case 5:
                   System.out.println("Меню:");
                   System.out.println("1 – Ввести расходы за определенный день");
                   System.out.println("2 – Траты за месяц");
                   System.out.println("3 – Самая большая сумма расхода за месяц");
                   System.out.println("4 - Конвертация всех трат за месяц в др. валюту");
                   System.out.println("0 – Выход");
                   PICK = scanner.nextInt();
                   break;
               case 0:
                   System.out.println("До свидания!");
                   System.exit(0);
                   break;
               default:
                   System.out.println("Выберите пункт из текущего списка функций");
                   PICK = 5;
           }

       }

   }


   private void pushExpense () {
       System.out.println("Введите расходы за определенный день:");
       String dayStr = getDataFromUser("Введите день (от 1 до 30)" , true);
       int day = Integer.parseInt(dayStr);

       while (day < 1 || day > 30) {
           System.out.println("День должен быть в диапозоне от 1 до 30! \nПовторите попытку ввода!");
           day = scanner.nextInt();
       }
           if (expenses.containsKey(day)) {
               String response = getDataFromUser(
                       "Вы хотите перезаписать сумму или оставить предыдущую?" +
                       "\n Введите Y если хотите оставить " +
                       "\n Введите N если хотите перезаписать" ,
                       true);

               switch (response) {
                   case "Y":
                       PICK = 5;
                       break;
                   case "N":
                       BigDecimal money = getDataFromUser("Введите сумму пополнения: " , false);
                       expenses.replace(day , money);
                       PICK = 5;
                       break;
                   default:
                       System.out.println("Неккоректные данные!");
                       PICK = 5;
                       return;
               }
       }
       BigDecimal expenseToPush = getDataFromUser("Введите сумму трат за этот день: " , false);
       expenses.put(day , expenseToPush);

       goToMenu();
   }

   private void displayAllExpenses () {
       for (Map.Entry<Integer , BigDecimal> exp : expenses.entrySet()) {
           System.out.println(exp.getKey() + " день - " + exp.getValue() + " руб");
       }

       goToMenu();
   }

   private void displayMaxExpenses () {
       BigDecimal maxValue = BigDecimal.ZERO;
       int day = 0;
       for (Map.Entry<Integer , BigDecimal> exp : expenses.entrySet()) {
           if (exp.getValue().compareTo(maxValue) > 0) {
               maxValue = exp.getValue();
               day = exp.getKey();
           }
       }
       System.out.println("Самая большая сумма расхода за месяц: ");
       System.out.println(day + " день - " + maxValue + " руб");

       goToMenu();
   }

   private void converter() {
        Set<String> curs = bank.keySet();
        System.out.println("Доступные валюты для конвертации: ");
        curs.forEach(System.out::println);

        String currency = getDataFromUser("Введите валюту, в которую хотите перевести все траты за месяц", true);
        BigDecimal multiplicator = bank.get(currency);
        BigDecimal multiplicatorRU = bank.get("RUB");

        for (Map.Entry<Integer, BigDecimal> exp : expenses.entrySet()) {
            BigDecimal convertedSum = (exp.getValue().multiply(multiplicatorRU)).divide(multiplicator, 2 , RoundingMode.HALF_UP );
            System.out.println(exp.getKey() + " день - " + convertedSum + " " + currency);
        }

        goToMenu();
    }

    private void addCurrency() {
        bank.put("USD", BigDecimal.valueOf(1.0));
        bank.put("EUR", BigDecimal.valueOf(1.08));
        bank.put("CNY", BigDecimal.valueOf(1.0 / 7.0));
        bank.put("RUB", BigDecimal.valueOf(1.0 / 91.01));
    }

    private void goToMenu() {
        String res = "";
         while (!res.equals("Y")) {
            res = getDataFromUser("Введите Y, чтобы выйти в главное меню: ", true);
            if (!res.equals("Y")) {
                System.out.println("Некорректное значение!");
            }
        };
        PICK = 5;
    }

    private <T> T getDataFromUser(String text , boolean isString) {
        System.out.println(text);
        return isString ? (T) scanner.next() : (T) scanner.nextBigDecimal();
    }
}

public class Main {
    public static void main(String[] args) {
        FinancialCalculator sol = new FinancialCalculator();
        sol.init();
    }
}