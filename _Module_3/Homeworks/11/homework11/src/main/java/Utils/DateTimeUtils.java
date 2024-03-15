package Utils;

import java.time.Year;

public class DateTimeUtils {
    public static boolean isLeapYear(int year) {
        boolean divisibleBy4 = year % 4 == 0;
        boolean divisibleBy100 = year % 100 == 0;
        boolean divisibleBy400 = year % 400 == 0;

        // If the year is divisible by 4 and not by 100, or if it's divisible by 400, it's a leap year
        return (divisibleBy4 && !divisibleBy100) || divisibleBy400;
    }


    public static int calculateYearBorn(int age){
        int year = Year.now().getValue();
        return year - age;
    }

    public static boolean isBornInLeapYear(int age){
        int yearBorn = calculateYearBorn(age);
        return isLeapYear(yearBorn);
    }
}
