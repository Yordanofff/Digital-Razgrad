package Task3;

public class Main {
    public static void main(String[] args) {
        System.out.println("Humans have " + Human.LEGS_COUNT + " legs and " + Human.FINGERS_COUNT + " fingers.");
        System.out.println("Students have " + Student.LEGS_COUNT + " legs and " + Student.FINGERS_COUNT + " fingers.");
        System.out.println("Workers have " + Worker.LEGS_COUNT + " legs and " + Worker.FINGERS_COUNT + " fingers.");

        Human h1 = new Human();
        h1.printNames();

        System.out.println("\n" + "*".repeat(50) + "\n");

        Human h2 = new Human("Peter", "Petrov", "123123123");
        h2.printNames();
        h2.printIsEGNValid();

        System.out.println("\n" + "*".repeat(50) + "\n");


    }
}
