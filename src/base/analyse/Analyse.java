package base.analyse;


import java.math.BigDecimal;
import java.util.Scanner;

public class Analyse {
    static Scanner scan = new Scanner(System.in);
    public static void main (String[] args) {
        BigDecimal income = getDataFromUser("заработной платы");
        BigDecimal transportExpenses = getDataFromUser("расходов на траснпорт");
        BigDecimal foodExpenses = getDataFromUser("расходов на еду");
        BigDecimal savings = getDataFromUser("сбережений");
        analyse(income,transportExpenses,foodExpenses,savings);
    }

    private static void analyse (BigDecimal income , BigDecimal transportExpenses , BigDecimal foodExpenses , BigDecimal savings) {
        BigDecimal allExpenses = transportExpenses.add(foodExpenses);
        BigDecimal balance = income.subtract(allExpenses);
        if (balance.compareTo(BigDecimal.ZERO) > 0 ) {
            System.out.println("Имеется излишек в размере " + balance +"\n");
            System.out.println("Рекомендуется пополнить свои сбережения");
        }
        else
            if (balance.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("Да данный момент, у вас недостаточно средств для накоплений. \n" + "Ваш баланс:" + balance );
                System.out.println("Рекомендуется уменьшить рпсходы");
            }
    }

    private static BigDecimal getDataFromUser (String text) {
        System.out.println("Введите сумму " + text + ":");
        return scan.nextBigDecimal();
    }

}
