package Task2;

public class Main {
    public static void main(String[] args) {
        //Направете клас търговски обект с полета: име, адрес, обща сума на стоките в
        //магазина. И методи: storeProducts() - за зареждане със стока, който увеличава
        //общата сума на стоки в магазина; endDailyTransactions() - отпечатва текст при
        //затваряне на касите в магазина.
        //Добавете клас хипермаркет, който наследява класа търговски обект с полета:
        //брой щандове, брой каси, има ли тоалетна. И метод serviceSpeed(), който
        //изчислява бързина на обслужване като разделя броя щандове на броя каси.
        //Напишете съответните конструктори и методи за достъп до полетата
        //(свойства). Създайте по 2 обекта от класовете и тествайте всички методи.
        //Помислете за поне 1 статично поле и поне 1 статичен метод в който и да е от
        //двата класа.
        Store s1 = new Store("Лафка", "Варна, ул. Петър Берон 7");
        Store.open();
        s1.storeProducts("Water 0.5l", 1, 50);
        s1.storeProducts("Water 1.0l", 1.1, 20);
        System.out.println(s1);
        s1.setAddress("Варна, ул. Иван Петров 2");
        System.out.println(s1);
        Store.close();
        System.out.println("\n" + "*".repeat(50) + "\n");

        Hypermarket h1 = new Hypermarket("Lidl", "Варна, ул. Иван Иванов 6", 50, 5, true);
        Hypermarket.open();
        h1.storeProducts("Milk", 0.8, 200);
        h1.storeProducts("Sugar", 2.2, 400);
        System.out.println(h1);
        h1.setName("LIDL");
        System.out.println(Hypermarket.OPEN_HOURS);
        System.out.println(h1);
        Hypermarket.close();
        System.out.println("\n" + "*".repeat(50) + "\n");

        Hypermarket h2 = new Hypermarket("Billa", "Разград, ул. Георги Жечев 2", 120, 6, true);
        System.out.println(h2);
        h2.storeProducts("Water 0.5l", 1, 50);
        System.out.println(h2);
        h2.storeProducts("Water 1.0l", 1.1, 20);
        h2.setCheckoutCount(1);
        System.out.println(h2);
    }
}
