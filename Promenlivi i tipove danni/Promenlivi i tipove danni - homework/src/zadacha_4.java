// Да се напише програма, в която записваме население и
//площ на 2 държави. Отпечатайте гъстотата на населението
//на 2-те държава. Отпечатайте гъстотата и като цяло число -
//чрез type casting (преобразуване от дробно в цяло число).
//След това отпечатайте по-голямото население (потърсете как
//работи функцията max() )

public class zadacha_4 {
    public static void main(String[] args) {
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
}
