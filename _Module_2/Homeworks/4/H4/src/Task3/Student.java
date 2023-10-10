package Task3;

public class Student extends Human{
    private double score;
    private static boolean HAS_EXAMS = true;
    private static boolean HAS_SCORES = true;
    private static int GRADES_TO_PASS_TO_GRADUATE = 12;
    private static int MAX_SUBJECTS_PER_DAY = 8;  // number of different lessons in class per day.

    public Student() {
        super();
        this.score = 5.20;
    }

    public Student(String firstName, String lastName, String egn, double score) {
        super(firstName, lastName, egn);
        this.score = score;
    }

    public void doNoHomeworkPunishment() {
        for (int i = 0; i <= 40; i++) {
            System.out.println("I will not miss homework again!");

        }
    }

}
