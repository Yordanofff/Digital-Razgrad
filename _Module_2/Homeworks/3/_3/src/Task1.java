public class Task1 {
    //Използвайки подходящи нива на достъп (private / protected / public),
    //направете клас Building с полета: адрес, дължина, ширина, височина, цена
    //и има ли соларни панели. С методи: paint(String color) - боядисвам,
    //furnish(String furniture, int price). Тези методи променят цената на сградата.
    //Направете клас House, който наследява Building и има полета: вид покрив,
    //размер на двор (в квадратни метри). Както и метод: slantMeadow() - кося
    //ливада. Добавете Getters & Setters.
    //Направете клас Block, който наследява Building и има полета: брой етажи,
    //има ли асансьор. Както и метод: termalIsolation().
    //Използвайте подходящи нива на достъп, Getters и Setters за всички
    //класове. Преценете има ли нужда някои методи да са private?
    //Създайте по 2 обекта на класовете House и Block, тествайте всички
    //методи.

    public static void main() {
        Block block1 = new Block("Razgrad, jk.Orel, bl.19", 120, 50, 80,250000,8, true);
        Block block2 = new Block("Ruse, bul.Ivan Todorov, bl.23", 50, 50, 30,80000,3, false);

        printTestBlock(block1);
        printTestBlock(block2);

        House h1 = new House("Razgrad, ul.Petar Petrov 22", 150, 200, 50, 160000, "roof tiles \"Ruse\"", 150);
        House h2 = new House("Razgrad, ul.Petar Petrov 23", 160, 220, 60, 180000, "roof tiles \"Plovdiv\"", 15000);

        printTestHouse(h1);
        printTestHouse(h2);
    }

    private static void printTestHouse(House testHouse) {
        printSeparator();
        System.out.println("Address:" + testHouse.getAddress());
        System.out.println("Garden size: " + testHouse.getGardenSizeSquareMeters() + "sq. meters");

        System.out.println();
        System.out.println("Current roof tiles: " + testHouse.getRoofType());
        testHouse.setRoofType("Cheap roof tiles");
        System.out.println("Updated roof tiles: " + testHouse.getRoofType());

        System.out.println();
        testHouse.slantMeadow();
    }

    private static void printTestBlock(Block testBlock) {
        printSeparator();
        System.out.println("Address: " + testBlock.getAddress());
        System.out.println("Number of floors: " + testBlock.getNumberOfFloors());

        System.out.println();

        System.out.println("Termal isolation: " + testBlock.getTermalIsolation());
        testBlock.termalIsolation();
        testBlock.termalIsolation();
        System.out.println("Termal isolation: " + testBlock.getTermalIsolation());

        System.out.println();

        System.out.println("Price: " + testBlock.getPrice());
        testBlock.furnish("82inch TV", 2500);
        testBlock.furnish("TV table", 500);
        System.out.println("Price: " + testBlock.getPrice());

        System.out.println();

        testBlock.paint("Red");
        testBlock.paint("Red");
        testBlock.paint("Blue");
    }

    private static void printSeparator() {
        System.out.println("\n" + "*".repeat(100));
    }
}
