package base.alpinists;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

class Alpinist {
    private String name;
    private int age;
    private String address;

    public Alpinist (String name , int age , String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void displayAlKeys () {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age);
        System.out.println("Адрес проживания: " + address);
    }
}

class Mountain {
    private String name;
    private String country;
    private double height;
    private boolean isGroupOpen;

    private List<Alpinist> group = new ArrayList<Alpinist>();

    public Mountain (String name , String country , double height , boolean isGroupOpen , List<Alpinist> alpinists) {

        this.name = name;
        this.country = country;
        this.height = height;
        this.isGroupOpen = isGroupOpen;
        group = alpinists;
    }

    public void display() {
        for (Alpinist alpinist : group ) {
        alpinist.displayAlKeys();
        }
    }

    public void addAlpinistToGroup (Alpinist alpinist) {
        if (group.size() < 3 && isGroupOpen) {
            group.add(alpinist);
        } else {
            System.out.println("Группа уже заполнена! Максимальное количество участников в группе: 3");
        }
    }

}

public class Alpinists {
public static void main (String[] args) {


    Alpinist vasya = new Alpinist("Вася" , 24 , "Ижора");
    Alpinist petya = new Alpinist("Петя" , 51 , "Санкт-Петербург");
    Alpinist katya = new Alpinist("Катя", 32 , "Пермь");

    List<Alpinist> alpinists = new ArrayList<Alpinist>();
    alpinists.add(vasya);
    alpinists.add(petya);
    alpinists.add(katya);

    Mountain everest = new Mountain("Эверест" , "Китай" , 3233, false , alpinists );
    everest.display();
    everest.addAlpinistToGroup(vasya);
}
}
