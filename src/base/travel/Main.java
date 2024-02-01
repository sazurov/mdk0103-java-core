package base.travel;

import java.util.ArrayList;
import java.util.Scanner;

class User {
    Bank wallet = new Bank();
    private String currentCurrency;
    ArrayList<Currency> vault = wallet.getVault();


    public String getDataDromUser (String textToInput) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(textToInput);
        return scanner.next();
    }

    private void displayWallet() {
       wallet.displayVault();
    }

    public void travel (Country country) {
       currentCurrency = country.getCurrencyName();
       wallet.addNewCurrency(country.getCurrencyName() , 0);
       System.out.println("Текущая валюта: " +  country.getCurrencyName());
       System.out.println("Текущий баланс:");
       displayWallet();
       ArrayList<CurrencyCourse> countryCourses = country.getCourses();

       String currencyFromConvert = null;
       double course = 0; 
            
    for ( CurrencyCourse courses : countryCourses ) {
        if (courses.getName().contains(country.getCurrencyName())) {
            currencyFromConvert = courses.getName().substring(0,3);
        }
        if (courses.getName().equals(currencyFromConvert + " to " + country.getCurrencyName())) {
            course = courses.getValue();
        }
    }


       String SumToPopUp = getDataDromUser("Пополните баланс: ");

        double equivalentInEuro = Double.parseDouble(SumToPopUp) / course;
        wallet.expensesBalance(currencyFromConvert , equivalentInEuro);
        wallet.topUpBalance(country.getCurrencyName() , Double.parseDouble(SumToPopUp));

        displayWallet();
    }

}

class Bank {
    private ArrayList<Currency> vault = new ArrayList<>();

    public Bank() {
        vault.add(new Currency("USD", 10));
        vault.add(new Currency("EUR", 5));
        vault.add(new Currency("JPY", 130));
        vault.add(new Currency("RUB", 35000));
    }

    public void displayVault () {
         for (Currency cur : vault) {
            System.out.println("Валюта: " + cur.getName() + " | Количество: " + cur.getValue());
         }
    }

    public void addNewCurrency (String name , double val) {
        for (Currency cur : vault) {
            if (cur.getName().equals(name) || name.length() > 3) {
                throw new IllegalArgumentException("Invalid currency name");
            }
        }

        Currency newCur = new Currency(name , val);
        vault.add(newCur);
    }

    public void topUpBalance (String currencyName , double value) {
        for (Currency cur : vault) {
            if (cur.getName().equals(currencyName)) {
                cur.setValue(value);
            }
        }
    }

    public void expensesBalance (String currencyName , double value) {
        for (Currency cur : vault) {
            if (cur.getName().equals(currencyName)) {
                cur.setValue(cur.getValue() - value);
                if (cur.getValue() < 0) {
                    throw new Error("Вы привысили сумму пополнения! Недостаточно средств на счету!");
                }
            }
        }
    }

    public ArrayList<Currency> getVault() {
        return vault;
    }
}

class Currency {
    private String name;
    private double value;

    public Currency (String name , double value) {
        this.value = value;
        this.name = name;
    }

    public void setValue (double value) {
        this.value = value;
    }

    public String getName () {
        return this.name;
    }

    public double getValue () {
        return this.value;
    }
}

class CurrencyCourse {
    private String name;
    private double value;

    public CurrencyCourse (String name , double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
}

class Country {
    private String name;

    private ArrayList<CurrencyCourse> courses = new ArrayList<>();
    private String currencyName;

    public Country (String name , String courseName , double courseVal , String currencyName ) {
        this.currencyName = currencyName;
          this.name = name;
          addCource(courseName , courseVal);
    }

    public void addCurrencyName (String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void addCource (String courseName , double courseVal) {
        CurrencyCourse curCourse = new CurrencyCourse(courseName , courseVal);
        courses.add(curCourse);
    }

    public ArrayList<CurrencyCourse> getCourses() {
        return courses;
    }

    public void displayCourses () {
        for (CurrencyCourse course : courses) {
            System.out.println("Название конвертации: " + course.getName() + " | Курс: " + course.getValue());
        }
    }

    public String getName() {
        return name;
    }
}

public class Main {
    public static void main (String[] args) {

    User user = new User();
    Country sweden = new Country("Швеция" , "EUR to SEK" , 11.40 , "SEK");
    Country china = new Country("Китай" , "JPY to CNY" , 0.048 , "CNY");

    String choice = user.getDataDromUser("Выберите страну отправления: \nШвеция \nКитай \n");

    if (sweden.getName().equals(choice)) {
        user.travel(sweden);
    } else if (china.getName().equals(choice)) {
        user.travel(china);
    }
    }
}
