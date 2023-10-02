package Task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Human {
    private String firstName;
    private String lastName;

    private int EGN;
    public static final byte FINGERS_COUNT = 10;
    public static final byte LEGS_COUNT = 2;

    public boolean verifyEgn() {
        String EGNString = Integer.toString(this.EGN);
//        List<Integer> EGNPowers = new ArrayList<>(2,4,8,5,10,9,7,3,6);
        List<Integer> EGNPowers = new ArrayList<>(Arrays.asList(2, 4, 8, 5, 10, 9, 7, 3, 6));
        if (EGNString.length() != 10) {
            return false;
        }

        int sum;
        for (int i = 0; i < 10; i++) {
            sum += Integer.valueOf(EGNString[1]);
        }
    }

    public Human() {
        this.firstName = "Ivan";
        this.lastName = "Ivanov";
    }

    public Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void printNames() {
        System.out.println(this.firstName + " " + this.lastName);
    }
}
