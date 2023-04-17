import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

//        System.out.println("Принтиране на задачите:");
//        System.out.println("\n\n\n----> Задача 1:");
//        FirstTask();
        System.out.println("\n\n\n----> Задача 2:");
        SecondTask();


    }
    public static @NotNull String readString(String question) {
        System.out.print(question);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    //    @org.jetbrains.annotations.NotNull
    public static @NotNull Integer readInt(String question) {
        System.out.print(question);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static @NotNull Float readFloat(String question) {
        System.out.print(question);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextFloat();
    }

    public static boolean checkYN() {
        //само за 1-ва задача
        String hasLightsYN;
        hasLightsYN = readString("Има ли осветление[y/n]? ");
        if (hasLightsYN.equalsIgnoreCase("y")) {
            return true;
        } else if (hasLightsYN.equalsIgnoreCase("n")) {
            return false;
        } else {
            System.out.println("Грешен отговор! Моля отговорете с 'y' или 'n'.");
            return checkYN();
        }
    }

    public static void FirstTask() {
        // За даден стадион имаме следните характеристики:
        //▪ Наименование на стадиона
        //▪ Капацитет в бр. седящи места (в променливата capacity) ▪ Дали има осветление (в променливата hasLights)
        //▪ Площ в квадратни метри (дробно число)
        //Напишете програма, която позволява на потребителя да въведе характеристики за стадион и
        // след това ги отпечатва в подходящ вид. Стремете се да имате добро потребителско
        // изживяване (да е ясно какво се очаква от потребителя).

        String stadiumName;
        int capacity;
        boolean hasLights;
        float areaSqm;
        stadiumName = readString("Въведете името на стадиона: ");
        capacity = readInt("Какъв е капацитетът му? ");
        areaSqm = readFloat("Каква е площа на стадион " + stadiumName + " в квадратни метри?");
        hasLights = checkYN();

        System.out.println("Стадион: " + stadiumName);
        System.out.println("Капацитет: " + capacity);
        System.out.println("Площ: " + areaSqm);
        if (hasLights) {
            System.out.println("Осветление: Да");
        } else {
            System.out.println("Осветление: Не");
        }
    }

    public static void SecondTask() {
        //Да се напише програма, която позволява на потребителя да въведе цяло число N и
        // след това отпечатва цяла част и остатък при деление на N с 3.
        int N;
        int resultPart1;
        int resultPart2;
        N = readInt("Въведете число: ");

        resultPart1 = N / 3;
        resultPart2 = N % 3;

        System.out.println("При делене на 3 има " + resultPart1 + " цели части и остатък от " + resultPart2 + ".");
 
    }
}

