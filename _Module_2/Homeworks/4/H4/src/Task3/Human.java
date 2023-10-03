package Task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Human {
    private String firstName;
    private String lastName;

    private String EGN;
    public static final byte FINGERS_COUNT = 10;
    public static final byte LEGS_COUNT = 2;

    public boolean verifyEgn() {
        List<Integer> EGNPowers = new ArrayList<>(Arrays.asList(2, 4, 8, 5, 10, 9, 7, 3, 6));
        if (this.EGN.length() != 10) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int currentEGNnumber = Character.getNumericValue(this.EGN.charAt(i));
            int currentEGNPower = EGNPowers.get(i);
            sum += currentEGNnumber * currentEGNPower;
        }

        if ((sum % 11 == 0) || (sum % 11 == this.EGN.charAt(9))) {
            return true;
        }
        return false;
    }

    public void printIsEGNValid() {
        if (verifyEgn()) {
            System.out.println("ЕГН-то е валидно.");
        } else {
            System.out.println("ЕГН-то не е валидно!");
        }
    }

    public Human(String firstName, String lastName, String EGN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.EGN = EGN;
    }

    public Human() {
        this.firstName = "Ivan";
        this.lastName = "Ivanov";
        this.EGN = "123456789";
    }


    public void printNames() {
        System.out.println(this.firstName + " " + this.lastName);
    }
}
