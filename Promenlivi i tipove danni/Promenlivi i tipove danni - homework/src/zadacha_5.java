// Да се напише програма, в която записвате 3 оценки на
//ученик по даден предмет. След това изчислявате средно
//аритметично. И закръгляте оценката (ако е 4.55 трябва да
//закръгляне на 5, ако е 4.30 закръгляме на 4)
import java.util.List;

public class zadacha_5 {
    public static void main(String[] args) {

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
