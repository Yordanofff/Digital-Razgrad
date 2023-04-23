import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        // Uncomment the task that you would like to run and test.

//        Task1();
//        Task2();
//        Task3();
//        Task4();
//        Task5();
        Exam_5();

    }



    public static @NotNull String readString(String question) {
        System.out.print(question);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

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


    public static void Task1() {
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

    public static void Task2() {
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

    public static void Task3() {
        // За даден автомобил са дадени следните характеристики:
        // ▪ Възраст в години (age)
        // ▪ Цена в хил.лв. (price)
        //Един автомобил е от висок клас, ако е по-стар от 5 години и струва над 20 хил.лв. или е
        // на 5 или по-малко години и струва над 40 хил.лв.
        //Прочетете двете стойности от конзолата.
        //Напишете израз, който определя дали автомобил с дадени характеристики е от висок клас.
        //Принтирайте резултатът от израза (true или false).
        int age;
        int price;
        boolean highClass;

        age = readInt("На колко години е автомобилът? ");
        price = readInt("Колко пари струва (хил. лв)? ");

        // Set the bool to true or false
        highClass = (age > 5 && price > 20000) || (age <= 5 && price > 40000);

        if (highClass) {
            System.out.println("Автомобилът е висок клас.");
        } else {
            System.out.println("Автомобилът е нисък клас.");
        }

    }

    public static void Task4() {
        // Напишете израз, който намира и извежда в конзолата абсолютната стойност на дадено цяло число.
        // Абсолютната стойност на едно число Х е разстоянието му до 0.
        // Пример:
        // ако x = 5, то абсолютната стойност е 5 ако х = -23, то абсолютната стойност е 23
        // Прочетете числото от конзолата.

        float N;
        N = readFloat("Въведете число: ");

        // check if the number is float or int, so that it doesn't print 5.0 when the input is 5/-5,
        // but prints 5.3 when the input is 5.3/-5.3
        if (N % 1 == 0) {
            System.out.println("Абсолютната стойност на \"" + (int) N + "\" е: " + Math.abs((int)N));
        } else {
            System.out.println("Абсолютната стойност на \"" + N + "\" е: " + Math.abs(N));
        }
    }

    public static void Task5() {
        //Напишете булев израз, който да проверява дали едно цяло число въведено от потребител
        // се дели на 3 и на 7 без остатък.

        int N = readInt("Въведете число: ");
        String toPrint = "Числото \"" + N + "\" се дели без остатък на ";

        boolean isDivisibleBy3 = N % 3 == 0;
        boolean isDivisibleBy7 = N % 7 == 0;


        if (isDivisibleBy3 && isDivisibleBy7) {
            toPrint += "3 и на 7.";
        } else if (isDivisibleBy3) {
            toPrint += "3, но не и на 7.";
        } else if (isDivisibleBy7){
            toPrint += "7, но не и на 3.";
        } else {
            toPrint = "Числото \"" + N + "\" не се дели без остатък нито на 3, нито на 7.";
        }
        System.out.println(toPrint);


    }

    public static void Exam_1() {
        int a = readInt("Въведете а:");
        int b = readInt("Въведете б:");
        if (a > b) {
            System.out.println("a is bigger than b");
        } else {
            System.out.println("a is smaller than b");
        }
    }

    public static void Exam_2() {
        float weight = readFloat("Въведете теглото си на Земята: ");
        float moonRatioToEarth = 0.1653F;
        float moonWeight = (weight/9.81F) * 1.622F;
        System.out.println("Вашето тегло на Луната е " + moonWeight);
    }

    public static void Exam_3() {
        float radius = readFloat("Въведете радиуса на окръжноста: ");
        float pi = 3.14F;
        float lice = pi * radius * radius;
        float obikolka = pi * radius * 2;
        System.out.println("Лицето на окръжноста е: " + lice);
        System.out.println("Обиколката на окръжноста е: " + obikolka);
    }
    public static void Exam_4() {
        int score = 0;
        for (int i=0; i<5; i++) {
            String question = readString("Това е произволен въпрос. Отговори с [y/n]: ");
            if (question.equalsIgnoreCase("y")) {
                score++;
            } else if (question.equalsIgnoreCase("n")){
                score--;
            } else {
                System.out.println("Моля отговорете с [y/n].");
                i--;
            }
        }
        System.out.println("Вашият резултат е " + score);
    }

    public static void Exam_5() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of lines: ");
        int n = input.nextInt();
        input.close();
        for (int i = 1; i <= n; i++) {

            // Create the string of dashes before and after the 'A's
            String dashes = "-".repeat(n - i);
            String a = "";

            // Create inner pyramid
            for (int j = 0; j < (n * 2 - (n - i) * 2) - 1; j++) {
                if (j % 2 == 0) {
                    a += "A";
                } else {
                    a += "-";
                }
            }

            // Print the pattern for this line
            System.out.println(dashes + a + dashes);
        }
    }



}

