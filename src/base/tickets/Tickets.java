package base.tickets;

import java.util.Scanner;

class Solution {

    private String getTicketFromUser () {
        Scanner ticket = new Scanner(System.in);
        System.out.print("Введите номер билета :");
        return ticket.next();
    }

    public boolean calcTicketSum () {
        String ticket = this.getTicketFromUser();

        char[] ticketArray = ticket.toCharArray();

        int sum1 , sum2;

        sum1 = ticketArray[0] + ticketArray[1] + ticketArray[2];
        sum2 = ticketArray[3] + ticketArray[4] + ticketArray[5];

        return sum1 == sum2;
    }
}

public class Tickets {
    public static void main (String[] args) {
        Solution solution = new Solution();

        Boolean isMiracle = solution.calcTicketSum();

        System.out.print(isMiracle);
    }
}
