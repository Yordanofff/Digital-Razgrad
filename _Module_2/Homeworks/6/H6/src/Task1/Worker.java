package Task1;

import static java.lang.Math.round;

public class Worker implements Payable, Abscensable {
    private String name;
    private String position;
    private int salary;
    private int durationOfEmploymentInMonths;

    public Worker(String name, String position, int salary, int durationOfEmploymentInMonths) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.durationOfEmploymentInMonths = durationOfEmploymentInMonths;
    }

    /*
    New position / junior / just starting
     */
    public Worker(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.durationOfEmploymentInMonths = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDurationOfEmploymentInMonths() {
        return durationOfEmploymentInMonths;
    }

    public void setDurationOfEmploymentInMonths(int durationOfEmploymentInMonths) {
        this.durationOfEmploymentInMonths = durationOfEmploymentInMonths;
    }

    public double getDailyPayRate() {
        int numWorkingDays = 52 * 5;
        return (double) this.salary / numWorkingDays;
    }

    @Override
    public void paidLeave(int length) {
        System.out.println(this.name + " started a paid leave for " + length + " days. That will be a total of: " + (double) round(this.getDailyPayRate() * length * 100) /  100);
    }

    @Override
    public void unpaidLeave(int length) {
        System.out.println(this.name + " started a unpaid leave for " + length + " days.");
    }

    @Override
    public void sick(int length, String disease) {
        System.out.println(this.name + "has [" + disease + "] disease and will be off sick for " + length + " days. That will be a total of: " + (double) round(this.getDailyPayRate() * length * 100) /  100);
    }

    @Override
    public void paySalary() {
        System.out.println(this.name + " got a salary of: " + getSalary());
    }

    @Override
    public void payBonus(double amount) {
        System.out.println(this.name + " got a bonus of: " + amount);;
    }
}
