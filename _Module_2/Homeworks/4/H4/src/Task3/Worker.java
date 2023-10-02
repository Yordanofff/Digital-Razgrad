package Task3;

public class Worker extends Human {
    private double dailySalary;
    private int hoursWork;

    public static int DEFAULT_WORKING_HOURS = 8;
    public static int DEFAULT_WORKING_DAYS_PER_WEEK = 5;
    public static int DEFAULT_LUNCH_BRAKE_HOURS = 1;

    public Worker() {
        super();
        this.dailySalary = 100;
        this.hoursWork = DEFAULT_WORKING_HOURS;
    }

    public static void printDefaultWorkingSummary() {
        System.out.println("Standard working day has: " + DEFAULT_WORKING_HOURS + " working hours. \n" +
                "Standard working day has: " + DEFAULT_WORKING_DAYS_PER_WEEK + " days. \n" +
                "Standard working week has: " + DEFAULT_WORKING_HOURS * DEFAULT_WORKING_DAYS_PER_WEEK + " hours. \n" +
                "Total hours per year: " + DEFAULT_WORKING_HOURS * DEFAULT_WORKING_DAYS_PER_WEEK * 52 );
    }

    public Worker(String firstName, String lastName, double dailySalary, int hoursWork) {
        super(firstName, lastName);
        this.dailySalary = dailySalary;
        this.hoursWork = hoursWork;
    }

    public double getSalaryPerHour() {
        return this.dailySalary / hoursWork;
    }

    public double getDailySalary() {
        return dailySalary;
    }

    public void setDailySalary(double dailySalary) {
        this.dailySalary = dailySalary;
    }

    public int getHoursWork() {
        return hoursWork;
    }

    public void setHoursWork(int hoursWork) {
        this.hoursWork = hoursWork;
    }

}
