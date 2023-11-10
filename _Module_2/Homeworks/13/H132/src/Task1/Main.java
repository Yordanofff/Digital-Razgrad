package Task1;

public class Main {
    //Да се напишат подходящи тестове (поне по 3 на метод) към следните
    //задачи:
    //a. Да се напише метод, който приема масив като аргумент и връща true
    //или false, в зависимост от това, дали масивът е огледален.
    //Примери за огледални масиви:
    // [1, 2, 3, 3, 2, 1]; [3, 4, 4, 9, 3, 9, 4, 4, 3]; [1, 5, 5, 1]; [2, 0, 2]; [7]; [ ]
    public static void main(String[] args) {
        int[] test_arr = {1, 2, 3, 3, 2, 1};
        if (ArrayChecker.isMirroredArray(test_arr)) {
            System.out.println("Cool beans");
        }
    }
}
