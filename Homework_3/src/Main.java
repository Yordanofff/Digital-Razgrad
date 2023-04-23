import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

//         Uncomment the task that you would like to run and test.

//        Task1();
//        Task2();
//        Task3();
//        Task4();
//        Task5();
//        Task6();

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

    public static boolean getYN(String question) {
        String hasLightsYN = readString(question + " [y/n]? ");
        if (hasLightsYN.equalsIgnoreCase("y")) {
            return true;
        } else if (hasLightsYN.equalsIgnoreCase("n")) {
            return false;
        } else {
            System.out.println("Грешен отговор! Моля отговорете с 'y' или 'n'.");
            return getYN(question);
        }
    }


    public static void Task1() {
        //Да се напише програма, в която потребителя
        //въвежда следната информация за шоколад:
        //▪ име
        //▪ грамаж
        //▪ процент въглехидрати (дробно число от 0.1 до
        //0.99)
        //▪ подходящ ли е за вегани
        //▪ цена (дробно число)
        //Програмата отпечатва въведената информация в
        //подходящ вид

        System.out.println("Въведете информацията за шоколада:");
        String name = readString("Име: ");
        float weight = readFloat("Грамаж (грам): ");
        float carbohydrates = readFloat("Процент въглехидрати (дробно число от 0.1 до 0.99): ");
        boolean vegan = getYN("Подходящ ли е за вегани");
        float price = readFloat("Цена (лв.): ");

        System.out.println("Шоколад: " + name);
        System.out.println("Цена: " + price + " лв.");
        System.out.println("Грамаж : " + weight + " грама");
        System.out.println("Процент въглехидрати: " + carbohydrates * 100 + "%");
        if (vegan) {
            System.out.println("Подходящ за вегани: Да");
        } else {
            System.out.println("Подходящ за вегани: Не");
        }
    }

    public static void Task2() {
        //Напишете програма, която позволява на потребителя
        //да въведе цяло двуцифрено число. Програмата
        //намира и отпечатва сбора от цифрите на числото
        //б) решете задачата и за четирицифрено число

        int num = readInt("Въведете число: ");
        String str = Integer.toString(num);
        char[] digits = str.toCharArray();
        int sum = 0;
        for (char digit : digits) {
            sum += Character.getNumericValue(digit);
        }
        System.out.println("Сбора на числата в " + str + " е: " + sum);
    }

    public static void Task3() {
        //Напишете програма, която позволява на потребителя
        //да въведе парола. Ако въведената парола е
        //”pass1234” отпечатва съобщение, че е коректна
        //(Welcome). В противен случай - пише, че е въведена
        //грешна парола (Wrong password!)

        String pass = readString("Въведете парола: ");
        if (pass.equals("pass1234")) {
            System.out.println("Welcome back Mr. Anderson");
        } else {
            System.out.println("Wrong password!");
        }
    }

    public static void Task4() {
        //Да се напише програма, която проверява дали едно
        //топче е подходящо за деца под 3 години. За да бъде
        //подходящо трябва да отговаря на следните условия:
        //▪ да не е метално или пластмасово
        //▪ радиусът на топчето да е по-голям от 5 см
        //▪ теглото му (дробно число) да е под 1 кг
        //Информацията за материал, радиус и тегло на
        //топчето се въвежда от потребителя.

        boolean goodForKids = false;
        System.out.println("Въведете информация за топчето: ");
        boolean isMetal = getYN("Метално ли е");
        boolean isPlastic = getYN("Пластмасово ли е");
        float radius = readFloat("Колко сантиметра е диаметърът му? ");
        int weightGr = readInt("Колко грама е? ");
        if (!isMetal && !isPlastic && radius>5 && weightGr<1000) {
            goodForKids = true;
        }
        if (goodForKids) {
            System.out.println("Топчето е подходящо за деца под 3 години.");
        } else {
            System.out.println("Топчето не е подходящо за деца под 3 години.");
        }
    }

    public static void Task5() {
        // Да се напише програма за търсене в текст. В
        //програмата е даден дълъг текст (поне 3 изречения).
        //Потребителя въвежда търсена дума и програмата
        //отпечатва дали тази дума се среща в текста или не.
        //Жокер: Потърсете функция към String, която извършва
        //такова търсене
        String longString = readString("Въведете дълъг текст: ");
        String shortString = readString("Въведете дума която търсите: ");
        if (longString.contains(shortString)) {
            System.out.println("Думата " + shortString + " се съдържа в текста.");
        } else {
            System.out.println("Думата " + shortString + " не се съдържа в текста.");
        }
    }

    public static void Task6() {
        //  Да се напише програма, която позволява на
        //потребителя да въведе цяло число и отпечатва
        //символът със съответния ASCII код.
        //Например за 97 - отпечатва ‘a’.
        int num = readInt("Въведете цяло число, което ще принтира ASCII код с този номер: ");
//        char asciiChar = (char) num;
        System.out.println("Символът със съответния ASCII код е: " + (char) num);
    }

}

