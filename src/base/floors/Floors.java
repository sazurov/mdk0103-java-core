package base.floors;

import java.util.Scanner;

class Solution {

    private int getApartmentsNumber () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер квартиры: ");
        return scanner.nextInt();
    }

    public String apartmentsPosition(int[][] building) {
        int ROWS = building.length;
        int COLUMNS = building[0].length;

        int apartmentNumber = this.getApartmentsNumber();

        if (apartmentNumber > ROWS * COLUMNS || apartmentNumber <= 0) {
            return "Такой квартиры не существует в этом доме";
        }

        int floor = (apartmentNumber - 1) / COLUMNS;
        int position = (apartmentNumber - 1) % COLUMNS;

        String floorStr = (floor == 0) ? "первом этаже" : (floor + 1) + " этаже";
        String positionStr;

        boolean condition = position < COLUMNS / 2;

        if (position % 2 == 0) {
            positionStr = condition ? "слева от лифта" : "справа от лифта";
        } else {
            positionStr = condition ? "далеко слева" : "далеко справа";
        }

        return "Квартира №" + apartmentNumber + " находится на " + floorStr + ", " + positionStr;
    }
}

class Apartments {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] building = new int[9][4];

        String str = solution.apartmentsPosition(building);

        System.out.println(str);
    }
}