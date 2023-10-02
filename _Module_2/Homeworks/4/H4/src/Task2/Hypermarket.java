package Task2;

public class Hypermarket extends Store {
    private int standCount;
    private int checkoutCount;
    private boolean isToilet;
    private static final int TAX_PERCENTAGE = 20;
    public static final String OPEN_HOURS = "08:00AM - 10:00PM";
    public static final int MAX_CAPACITY = 100;

    public Hypermarket(String name, String address, int standCount, int checkoutCount, boolean isToilet) {
        super(name, address);
        this.standCount = standCount;
        this.checkoutCount = checkoutCount;
        this.isToilet = isToilet;
    }
    public static void open() {
        System.out.println("Хипермаркетът отвори. Добре дошли.");
    }

    public static void close() {
        System.out.println("Хипермаркетът затвори. Довиждане.");
    }

    public double serviceSpeed() {
        return (double) this.standCount / this.checkoutCount;
    }

    @Override
    public String toString() {
        return "Hypermarket{" +
                "name=" + super.getName() +
                ", address='" + super.getAddress() + '\'' +
                ", totalProductsCost=" + super.getTotalProductsCost()+
                ", standCount=" + this.standCount+
                ", checkoutCount=" + this.checkoutCount+
                ", serviceSpeed=" + this.serviceSpeed()+
                ", isToilet=" + this.isToilet+
                ", OPEN_HOURS='" + OPEN_HOURS + '\'' +
                ", MAX_CAPACITY=" + MAX_CAPACITY+
                '}';
    }

    public int getStandCount() {
        return standCount;
    }

    public void setStandCount(int standCount) {
        this.standCount = standCount;
    }

    public int getCheckoutCount() {
        return checkoutCount;
    }

    public void setCheckoutCount(int checkoutCount) {
        this.checkoutCount = checkoutCount;
    }

    public boolean isToilet() {
        return isToilet;
    }

    public void setToilet(boolean toilet) {
        isToilet = toilet;
    }
}
