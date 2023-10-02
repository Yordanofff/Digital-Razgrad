package Task1;

public class Main {
    public static void main(String[] args) {
        //Направете клас Human, който има статично поле votingAge - възраст за
        //гласуване. И полета име, адрес, възраст. Добавете метод isAllowedToVote(),
        //който проверя дали даден човек има право да гласува (е навършил годините,
        //за да гласува).
        //Добавете 2 конструктора и поне 3 обекта на класа. Getters и Setters.

        Human h1 = new Human();
        Human h2 = new Human("Gosho", 16, "Varna");
        Human h3 = new Human("Penka", 85, "Plovdiv");

        h1.printIsAllowedToVoteMsg();

        System.out.println("\n" + "*".repeat(50) + "\n");

        for (int i = 0; i < 4; i++) {
            h2.printIsAllowedToVoteMsg();
            h2.increaseAge();
        }

        System.out.println("\n" + "*".repeat(50) + "\n");

        h3.printIsAllowedToVoteMsg();
        System.out.println(h3);

        h3.setAge(86);
        h3.setAddress("Ruse");
        System.out.println(h3);
    }
}