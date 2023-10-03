import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Да се направи клас Pet (домашен любимец) с полета:
        // име, тегло, възраст. И методи: eat(), sleep(), play(). Добавете 2 конструктора за към този клас.
        //Направете класове Cat, Dog, Iguana, които наследяват Pet.
        // И имат полета: Cat - color, Dog - breed (порода), Iguana - length.
        // Всеки клас има 2 конструктора. Използвайте полиморфизъм чрез overriding за eat(), sleep() и
        // чрез overloading за play(). Създайте по 2 обекта от всеки клас добавете ги към масив и тествайте всеки от методите.

        Pet p1 = new Pet();
        p1.play();
        p1.eat();
        p1.sleep();

        System.out.println("*".repeat(80));

        Iguana i1 = new Iguana("Lili", 2, 4, 40);
        i1.eat();
        i1.play();
        i1.play("ball");
        i1.sleep();

        Iguana i2 = new Iguana(20);
        i2.eat();
        i2.play();
        i2.play("ball2");
        i2.sleep();

        System.out.println("*".repeat(80));
        Cat c1 = new Cat("Garfield", 5, 20, "orange");
        c1.play();
        c1.play("cardboard box");
        c1.eat();
        c1.sleep();

        Cat c2 = new Cat("Pufi", 1, 2, "brown");
        c2.play();
        c2.play("plastic box");
        c2.eat();
        c2.sleep();


        System.out.println("*".repeat(80));
        Dog d1 = new Dog("Jonny", 2, 25, "Koferman");
        d1.play();
        d1.play("plastic ball");
        d1.sleep();
        d1.eat();

        Dog d2 = new Dog("Sharo", 3, 35, "Bulldog");
        d2.play();
        d2.play("wooden stick");
        d2.sleep();
        d2.eat();

        System.out.println("*".repeat(80));
        System.out.println("*".repeat(80));

        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet());
        pets.add(new Cat("silver"));
        pets.add(new Dog("Chocho", 3, 15, "chichi"));
        pets.add(new Iguana("Peter", 2, 1.2, 23));

        for(Pet pet: pets) {
            pet.eat();
            pet.sleep();
            pet.play();
            System.out.println("*".repeat(80));
        }
    }
}