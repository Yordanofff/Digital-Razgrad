package Task3;

public class Student extends Human{
    private double score;

    public Student() {
        super();
        this.score = 5.20;
    }

    public Student(String firstName, String lastName, double score) {
        super(firstName, lastName);
        this.score = score;
    }

}
