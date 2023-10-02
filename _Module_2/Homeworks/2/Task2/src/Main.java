public class Main {
    public static void main(String[] args) {
        //Направете клас Mountain (планина), който има поне 3
        //полета и поне 2 метода. В единия метод използвайте
        //цикъл по избор. Добавете 2 конструктора към класа и
        //допълнителен метод printInfo(), който отпечатва
        //информация за всички полета.
        //Създайте поне 2 обекта на класа.
        Mountain m1 = new Mountain("Everest", 8000, 50000, 250000);
        Mountain m2 = new Mountain("Rila", 2925, 50000, 250000);

        m1.printInfo();
    }
}