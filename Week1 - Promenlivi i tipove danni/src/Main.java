import java.util.List;
public class Main {
    public static void main(String[] args) {

        System.out.println("Принтиране на задачите:");
        System.out.println("\n\n\n----> Задача 1:");
        FirstTask();
        System.out.println("\n\n\n----> Задача 2:");
        SecondTask();
        System.out.println("\n\n\n----> Задача 3:");
        ThirdTask();
        System.out.println("\n\n\n----> Задача 4:");
        FourthTask();
        System.out.println("\n\n\n----> Задача 5:");
        FifthTask();
    }

    public static void FirstTask() {
    // Да се напише програма, която отпечатва правоъгълник от звездички със страни 3 на 5

        for (byte i = 1; i <= 3; i++) {
            System.out.println("*".repeat(5));
        }
    }
    public static void SecondTask() {
        // Да се напише програма, която отпечатва квадрат

        String topBottom = "—"; // "—" "-"
        String sides = "|";

        // Change for different square size
        int squareSize = 9;

        // This might need to be changed if different symbols are used
        int newNumberToMakeItLookBetter = (int)Math.round(0.5 * squareSize) + (squareSize * 2);

        // print top
        System.out.println(" " + topBottom.repeat(newNumberToMakeItLookBetter));

        for (byte i = 1; i <= squareSize; i++) {
            System.out.println(sides + " ".repeat(newNumberToMakeItLookBetter) + sides);
        }

        // print bottom
        System.out.println(" " + topBottom.repeat(newNumberToMakeItLookBetter));
    }
    public static void ThirdTask() {
        //Да се напише програма, която описва характеристики на град: име на град, държава,
        // население, площ, гъстота на населението (среден брой хора на квадратен километър -
        // изчислява се на база предходните 2), кмет

        String country = "Bulgaria";
        String city = "Razgrad";
        String mayor = "Dencho Boyadzhiev";
        int population = 47590;

        // square km
        float sqkm = 92.84F;

        // square meters (calculated)
        int sqm = (int) (sqkm * 1000000);

        // round the number to 2 decimal places
        double averagePeoplePerSQKM = Math.round((population / sqkm) * 100.0) / 100.0;

        System.out.println("-".repeat(80));
        System.out.println("Държава: " + country);
        System.out.println("Град: " + city);
        System.out.println("Кмет: " + mayor);
        System.out.println("Население: " + population);
        System.out.println("Площ(км²): " + sqkm);
        System.out.println("Среден брой хора на км²: " + averagePeoplePerSQKM);
        System.out.println("-".repeat(80));
    }
    public static void FourthTask() {
        // Да се напише програма, в която записваме население и
        //площ на 2 държави. Отпечатайте гъстотата на населението
        //на 2-те държава. Отпечатайте гъстотата и като цяло число -
        //чрез type casting (преобразуване от дробно в цяло число).
        //След това отпечатайте по-голямото население (потърсете как
        //работи функцията max() )

        String country1 = "Bulgaria";
        int population1 = 6878000;
        int sqkm1 = 110994;

        String country2 = "Romania";
        int population2 = 19120000;
        int sqkm2 = 238397;


        float averagePeoplePerSQKM1 = (float) population1 / sqkm1;
        int averagePeoplePerSQKM1Round = Math.round(averagePeoplePerSQKM1);

        float averagePeoplePerSQKM2 = (float) population2 / sqkm2;
        int averagePeoplePerSQKM2Round = Math.round(averagePeoplePerSQKM2);

        //Finding the highest number (population)
        int max_population = Math.max(population1, population2);

        System.out.println("-".repeat(80));

        System.out.println("Държава: " + country1);
        System.out.println("Население: " + population1);
        System.out.println("Площ(км²): " + sqkm1);
        System.out.println("Гъстота: " + averagePeoplePerSQKM1 + " човека на км²");
        System.out.println("Гъстота (закръглено): " + averagePeoplePerSQKM1Round + " човека на км²");

        System.out.println("-".repeat(80));

        System.out.println("Държава: " + country2);
        System.out.println("Население: " + population2);
        System.out.println("Площ(км²): " + sqkm2);
        System.out.println("Гъстота: " + averagePeoplePerSQKM2 + " човека на км²");
        System.out.println("Гъстота (закръглено): " + averagePeoplePerSQKM2Round + " човека на км²");

        System.out.println("-".repeat(80));

        if (population1 == population2) {
            System.out.println("Двете страни са с еднакъв брой население" + " - " + population1);
        } else if (max_population == population1) {
            System.out.println("Страната с по-голямо население е: " + country1 + " - " + population1);
        } else {
            System.out.println("Страната с по-голямо население е: " + country2 + " - " + population2);
        }

    }
    public static void FifthTask() {
        // Да се напише програма, в която записвате 3 оценки на
        //ученик по даден предмет. След това изчислявате средно
        //аритметично. И закръгляте оценката (ако е 4.55 трябва да
        //закръгляне на 5, ако е 4.30 закръгляме на 4)

        List<Integer> list = List.of(5, 5, 6);

        // Get the length of the list (3 in our case)
        int numOfScores = list.size();

        // Calculate the sum
        int sum = 0;
        for (int i=0; i<numOfScores; i++) {
            sum += list.get(i);
        }

        float avg = (float) sum / numOfScores;
        int avgRound = Math.round(avg);

        System.out.println("-".repeat(80));
        System.out.println("Лист със всички оценки: " + list);
        System.out.println("Сбор на всички оценки: " + sum);
        System.out.println("Средна оценка: " + avg);
        System.out.println("Средна оценка(закръглена): " + avgRound);
        System.out.println("-".repeat(80));

    }

}

// import java.util.Scanner;
//    public static void FirstTask(){
//            /*1. Да се напише програма, която отпечатва
//        правоъгълник от звездички със страни 3 на 5*/
//        var a = 5;
//        var b = 3;
//
//        for (int i =0; i<b;i++){
//            for (int j = 0; j < a ; j++) {
//                System.out.print("*");
//            }
//            System.out.println();
//        }
//    }
//    public static void SecondTask(){
//        /*2. Да се напише програма, която отпечатва квадрат*/
//        var a = 3;
//
//
//        for (int i =0; i<a;i++){
//            for (int j = 0; j < a ; j++) {
//                System.out.print("*");
//            }
//            System.out.println();
//        }
//    }
//    public static void ThirdTask(){
//        /*3. Да се напише програма, която описва характеристики на
//град: име на град, държава, население, площ, гъстота на
//населението (среден брой хора на квадратен километър -
//изчислява се на база предходните 2), кмет
//*/
//        var nameTown = "Razgrad";
//        var state= "Bulgariq";
//        var population = 40000;
//        var areaSquareKm = 92.84;
//        var populationDensity =population/areaSquareKm;
//        var mayor = "Dencho Boyadjiev";
//        var text ="Town is "+nameTown+".\nIts in NothEast part of "+state+
//                "\nPopulation is "+population+
//                "\nArea of "+nameTown+" is "+areaSquareKm+
//                "\nPopulation density: "+populationDensity+
//                "\nMayor is "+mayor;
//        System.out.println(text);
//    }
//    public static void FourthTask(){
//        /*4. Да се напише програма, в която записваме население и
//площ на 2 държави. Отпечатайте гъстотата на населението
//на 2-те държава. Отпечатайте гъстотата и като цяло число -
//чрез type casting (преобразуване от дробно в цяло число).
//След това отпечатайте по-голямото население (потърсете как
//работи функцията max() )*/
//        Scanner input = new Scanner(System.in);
//        var usaPopulation =331000000;
//        var usaArea = 9834000 ;
//        var usaPopulationDensity = usaPopulation/usaArea;
//        var bgPopulation = 6250000;
//        var bgArea = 110994;
//        var bgPopulationDensity = bgPopulation/bgArea;
//        System.out.println("USA population density: "+ (int)usaPopulationDensity);
//        System.out.println("Bulgaria population density: "+ (int)bgPopulationDensity);
//        System.out.println("По-голямото население е "+Math.max(usaPopulation,bgPopulation));
//
//        if (usaPopulation>bgPopulation){
//            System.out.println("По-голямото население е на САЩ - "+usaPopulation);
//        }else {
//            System.out.println("По-голямото население е на България - "+bgPopulation);
//        }
//    }
//    public static void FivedTask(){
//        /*5. Да се напише програма, в която записвате 3 оценки на
//ученик по даден предмет. След това изчислявате средно
//аритметично. И закръгляте оценката (ако е 4.55 трябва да
//закръгляне на 5, ако е 4.30 закръгляме на 4)*/
//
//        Scanner input = new Scanner(System.in);
//
//        System.out.println("Enter first grade ");
//        var first = input.nextDouble();
//        System.out.println("Enter second grade");
//        var second = input.nextDouble();
//        System.out.println("Enter third grade");
//        var third = input.nextDouble();
//        var calculate = (first+second+third)/3;
//        var temp = calculate % 1;
//
//        System.out.println("Реална оценка: "+calculate);
//        if (temp>0.55){
//            System.out.println("Средно аритметична оценка: "+(int)Math.ceil(calculate));
//        } else if (temp<0.55) {
//            System.out.println("Средно аритметична оценка: "+(int)calculate);
//        }
//
//
//    }
