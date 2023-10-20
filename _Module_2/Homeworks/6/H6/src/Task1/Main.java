package Task1;

public class Main {
    //Разработвате счетоводен софтуер
    //● Да се направи интерфейс Task1.Payable, който има методи paySalary(), payBonus(double amount)
    //● Да се направи интерфейс Task1.Abscensable, който има методи paidLeave(int length) - брой дни
    //платен отпуск, unpaidLeave(int lenght) - неплатен отпуск и sick(int length, String disease) -
    //болничен
    //● Да се направи клас Task1.Worker с полета: име, длъжност, заплата, стаж. Класът имплементира
    //интерфейсте Task1.Payable и Task1.Abscensable
    //● Създайте 3 обекта на класа Task1.Worker като използвате 2 различни контруктора
    public static void main(String[] args) {
        Worker w1 = new Worker("John", "painter", 25000);
        Worker w2 = new Worker("Jack", "construction worker", 35000, 12);
        Worker w3 = new Worker("Josh", "Software developer", 55000, 24);

        w1.sick(18, "Corona");
        w1.paidLeave(2);
        w1.unpaidLeave(10);
        w1.paySalary();
        w1.payBonus(156.8);

        System.out.println("*".repeat(80));

        w2.sick(2, "Diarrhea");
        w2.paidLeave(1);
        w2.unpaidLeave(5);
        w1.paySalary();
        w1.payBonus(320.2);

        System.out.println("*".repeat(80));

        w3.sick(1, "Headache");
        w3.paidLeave(10);
        w3.unpaidLeave(1);
        w1.paySalary();
        w1.payBonus(2000);

    }
}