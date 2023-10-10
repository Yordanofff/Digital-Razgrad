package Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    //Да се направи клас Task1.Athlete (спортист) с полета: име, тегло, умения, енергия. И методи:
    //train(), който увеличава уменията му, compete(), който намаля енергията му, rest() -
    //увеличава енергията. Добавете 2 конструктора за към този клас.
    //● Направете класове Task1.Swimmer, Task1.Runner, Task1.WeightLifter, които наследяват Task1.Athlete. И имат
    //полета: Task1.Swimmer - style (стил плуване), Task1.Runner - distance, Weight Lifter - weight category.
    //Всеки клас има 2 конструктора. Използвайте полиморфизъм чрез overriding за train(),
    //rest() и чрез overloading за compete(), така че да променят полетата на класа по
    //различен начин. Предефинирайте toString().
    //● Да се създаде масив от 6 спортиста - по 2 от всеки вид. Експериментирайте с
    //извикване на всеки от методите за всички спортисти от масива.

    public static void main(String[] args) {
        printSeparator( 120, "-", "Manual test - with/without competitor");
        runManualTest();

        printSeparator( 120, "-", "Separate Lists - random competitor");
        runTestOnSeparateLists();

        printSeparator( 120, "-", "Single Task1.Athlete List - no competitor");
        runTestsOnAthleteList();
    }

    public static void runManualTest() {
        Athlete a1 = new Athlete();
        doStuffAnyAthlete(a1);

        Swimmer s1 = new Swimmer("Ivan", 100, 24, 13, "Butterfly");
        Swimmer s2 = new Swimmer("Nikola", 105, 22, 12, "Butterfly");
        doStuffAnyAthlete(s1);  // default - no competitor specified
        doStuffSpecialAthleteOnly(s1, s2.getName());


        Runner r1 = new Runner("Gosho", 62, 25, 100, 100);
        doStuffAnyAthlete(r1);
        doStuffSpecialAthleteOnly(r1, "Dimitar");


        WeightLifter w1 = new WeightLifter("Kiril", 97, 20, 70, 100);
        doStuffAnyAthlete(w1);
        doStuffSpecialAthleteOnly(w1, "Peter");
    }

    public static void runTestOnSeparateLists() {
        String[] competitorNames = {"John", "Michael", "Robert", "William", "David", "James", "Joseph", "Charles", "Thomas", "Daniel"};
        Random randomNumber = new Random();

        List<Swimmer> swimmersList = new ArrayList<>();
        swimmersList.add(new Swimmer("Ivan", 100, 24, 13, "Butterfly"));
        swimmersList.add(new Swimmer("Nikola", 105, 22, 12, "Butterfly"));

        List<Runner> runnersList = new ArrayList<>();
        runnersList.add(new Runner("Gosho", 62, 25, 100, 100));
        runnersList.add(new Runner("Peter", 65, 20, 94, 1000));

        List<WeightLifter> weightLiftersList = new ArrayList<>();
        weightLiftersList.add(new WeightLifter("Kiril", 97, 20, 70, 100));
        weightLiftersList.add(new WeightLifter("Dimitar", 94, 23, 75, 95));

        for (Swimmer athlete : swimmersList) {
            int randomIndex = randomNumber.nextInt(competitorNames.length);
            String randomCompetitorName = competitorNames[randomIndex];
            doStuffSpecialAthleteOnly(athlete, randomCompetitorName);
        }

        for (Runner athlete : runnersList) {
            int randomIndex = randomNumber.nextInt(competitorNames.length);
            String randomCompetitorName = competitorNames[randomIndex];
            doStuffSpecialAthleteOnly(athlete, randomCompetitorName);
        }

        for (WeightLifter athlete : weightLiftersList) {
            int randomIndex = randomNumber.nextInt(competitorNames.length);
            String randomCompetitorName = competitorNames[randomIndex];
            doStuffSpecialAthleteOnly(athlete, randomCompetitorName);
        }
    }

    public static void runTestsOnAthleteList() {
        List<Athlete> athleteList = new ArrayList<>();
        athleteList.add(new Swimmer("Ivan", 100, 24, 13, "Butterfly"));
        athleteList.add(new Swimmer("Nikola", 105, 22, 12, "Butterfly"));
        athleteList.add(new Runner("Gosho", 62, 25, 100, 100));
        athleteList.add(new Runner("Peter", 65, 20, 94, 1000));
        athleteList.add(new WeightLifter("Kiril", 97, 20, 70, 100));
        athleteList.add(new WeightLifter("Dimitar", 94, 23, 75, 95));

        for (Athlete athlete : athleteList) {
            doStuffAnyAthlete(athlete);
            // can't call doStuffSpecialAthleteOnly and specify a competitor name because it's an Task1.Athlete and the
            // Task1.Athlete class doesn't have an abstract method that accepts a competitor.
        }
    }

    public static void doStuffSpecialAthleteOnly(Swimmer specialAthlete, String competitorName) {
        System.out.println(specialAthlete);
        for (int i = 0; i < 10; i++) {
            specialAthlete.compete(competitorName);
        }

        doStuffCommon(specialAthlete);
    }

    public static void doStuffSpecialAthleteOnly(Runner specialAthlete, String competitorName) {
        System.out.println(specialAthlete);
        for (int i = 0; i < 10; i++) {
            specialAthlete.compete(competitorName);
        }

        doStuffCommon(specialAthlete);
    }

    public static void doStuffSpecialAthleteOnly(WeightLifter specialAthlete, String competitorName) {
        System.out.println(specialAthlete);
        for (int i = 0; i < 10; i++) {
            specialAthlete.compete(competitorName);
        }

        doStuffCommon(specialAthlete);
    }


    public static void doStuffAnyAthlete(Athlete anyAthlete) {
        System.out.println(anyAthlete);
        for (int i = 0; i < 10; i++) {
            anyAthlete.compete();
        }

        doStuffCommon(anyAthlete);
        printSeparator(80, "=", "Next person");
    }

    private static void doStuffCommon(Athlete anyAthlete) {
        printSeparator();

        System.out.println(anyAthlete);
        for (int i = 0; i < 6; i++) {
            anyAthlete.train();
        }

        printSeparator();

        System.out.println(anyAthlete);
        for (int i = 0; i < 6; i++) {
            anyAthlete.rest();
        }

        System.out.println(anyAthlete);
    }

    private static void printSeparator() {
        System.out.println("\n" + "=".repeat(60) + "\n");
    }

    private static void printSeparator(int symbolCount) {
        System.out.println("\n" + "=".repeat(symbolCount) + "\n");
    }

    private static void printSeparator(int symbolCount, String symbol, String text) {
        int numSpacesBeforeAfter = (symbolCount - text.length()) / 2;
        System.out.println("\n" + symbol.repeat(symbolCount));
        System.out.println(" ".repeat(numSpacesBeforeAfter) + text);
        System.out.println(symbol.repeat(symbolCount) + "\n");
    }

}