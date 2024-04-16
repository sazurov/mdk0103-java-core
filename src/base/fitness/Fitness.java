package base.fitness;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

enum MembershipType {
    ONE_TIME,
    DAY_PASS,
    FULL
}

class Person {
    String firstName;
    String lastName;
    int birthYear;

    public Person(String firstName, String lastName, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }
}

class Membership {
    LocalDate registrationDate;
    LocalDate expirationDate;
    Person person;
    MembershipType type;

    public Membership(LocalDate registrationDate, LocalDate expirationDate, Person person, MembershipType type) {
        this.registrationDate = registrationDate;
        this.expirationDate = expirationDate;
        this.person = person;
        this.type = type;
    }

    public MembershipType getType() {
        return type;
    }

    public boolean isValid() {
        return LocalDate.now().isBefore(expirationDate);
    }
}

class FitnessClub {
    final int MAX_CAPACITY = 20;
    ArrayList<Membership> gymMembers = new ArrayList<>();
    ArrayList<Membership> poolMembers = new ArrayList<>();
    ArrayList<Membership> groupClassMembers = new ArrayList<>();

    public void register(Membership membership, String zone) {
        if (!membership.isValid()) {
            System.out.println("Срок действия абонемента истек!");
            return;
        }

        ArrayList<Membership> zoneMembers;
        switch (membership.getType()) {
            case ONE_TIME:
                registerOneTime(membership, zone);
                break;
            case DAY_PASS:
                registerDayPass(membership, zone);
                break;
            case FULL:
                registerFull(membership, zone);
                break;
            default:
                System.out.println("Неправильно указан тип абонемента");
        }
    }

    private void registerOneTime(Membership membership, String zone) {

        LocalTime currentTime = LocalTime.now();
        if (currentTime.isBefore(LocalTime.of(8, 0)) || currentTime.isAfter(LocalTime.of(21, 59))) {
            System.out.println("Разовые абонементы действительны с 8 до 22 часов!");
            return;
        }


        registerInZone(poolMembers, "бассейна", membership, zone);
        registerInZone(gymMembers, "тренажерного зала", membership, zone);
    }

    private void registerDayPass(Membership membership, String zone) {

        LocalTime currentTime = LocalTime.now();
        if (currentTime.isBefore(LocalTime.of(8, 0)) || currentTime.isAfter(LocalTime.of(15, 59))) {
            System.out.println("Дневные абонементы действительны с 8 до 16 часов!");
            return;
        }


        if (zone.equals("тренажерный зал") || zone.equals("групповые занятия")) {
            registerInZone(gymMembers, "тренажерного зала", membership, zone);
            registerInZone(groupClassMembers, "групповых занятий", membership, zone);
        } else {
            System.out.println("Дневные абонементы не действительны для бассейна!");
        }
    }

    private void registerFull(Membership membership, String zone) {

        LocalTime currentTime = LocalTime.now();
        if (currentTime.isBefore(LocalTime.of(8, 0)) || currentTime.isAfter(LocalTime.of(21, 59))) {
            System.out.println("Полные абонементы действительны с 8 до 22 часов!");
            return;
        }

        // Обрабатываем регистрацию для полного абонемента
        registerInZone(poolMembers, "бассейна", membership, zone);
        registerInZone(gymMembers, "тренажерного зала", membership, zone);
        registerInZone(groupClassMembers, "групповых занятий", membership, zone);
    }

    private void registerInZone(ArrayList<Membership> zoneMembers, String zoneName, Membership membership, String zone) {
        if (zoneMembers.size() >= MAX_CAPACITY) {
            System.out.println("В зоне " + zoneName + " нет свободных мест");
            return;
        }
        if (isRegisteredInOtherZone(membership, zone)) {
            System.out.println("Абонемент уже зарегистрирован в другой зоне");
            return;
        }
        zoneMembers.add(membership);
        System.out.println("Посетитель успешно зарегистрирован в зоне: " + zone);
    }

    private boolean isRegisteredInOtherZone(Membership membership, String currentZone) {
        switch (currentZone) {
            case "тренажерный зал":
                return poolMembers.contains(membership) || groupClassMembers.contains(membership);
            case "бассейн":
                return gymMembers.contains(membership) || groupClassMembers.contains(membership);
            case "групповые занятия":
                return gymMembers.contains(membership) || poolMembers.contains(membership);
            default:
                return false;
        }
    }

    public void displayVisitors(MembershipType type) {
        System.out.println("Посетители зала:");
        displayMemberships(gymMembers, type);

        System.out.println("Посетители бассейна:");
        displayMemberships(poolMembers, type);

        System.out.println("Посетители групповых занятий:");
        displayMemberships(groupClassMembers, type);
    }

    private void displayMemberships(ArrayList<Membership> memberships, MembershipType type) {
        for (Membership membership : memberships) {
            if (membership.getType() == type) {
                System.out.println(membership.person.firstName + " " + membership.person.lastName);
            }
        }
    }
}

public class Fitness {
    public static void main(String[] args) {
        FitnessClub fitnessClub = new FitnessClub();

        Person person1 = new Person("Алжон", "Бобиев", 1990);
        Membership membership1 = new Membership(LocalDate.now(), LocalDate.now().plusDays(1), person1, MembershipType.ONE_TIME);
        fitnessClub.register(membership1, "тренажерный зал");

        Person person2 = new Person("Аджик", "Фалиев", 1985);
        Membership membership2 = new Membership(LocalDate.now(), LocalDate.now().plusDays(1), person2, MembershipType.DAY_PASS);
        fitnessClub.register(membership2, "бассейн");

        Person person3 = new Person("Барак", "Обама", 1995);
        Membership membership3 = new Membership(LocalDate.now(), LocalDate.now().plusDays(1), person3, MembershipType.FULL);
        fitnessClub.register(membership3, "групповые занятия");

    }
}
