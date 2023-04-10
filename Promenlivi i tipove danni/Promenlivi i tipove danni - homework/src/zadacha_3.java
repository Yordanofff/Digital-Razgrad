//Да се напише програма, която описва характеристики на град: име на град, държава,
// население, площ, гъстота на населението (среден брой хора на квадратен километър -
// изчислява се на база предходните 2), кмет

public class zadacha_3 {
    public static void main(String[] args) {
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

}
