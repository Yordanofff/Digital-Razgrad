package Task2;

public class Store {
    private String name;
    private String address;
    private double totalProductsCost;
    private int storeProductsCount;
    private int quantityCount;

    public void storeProducts(String productName, double productPrice, int quantity) {
        System.out.println("New stock: " + productName + " Price: " + productPrice + " Quantity: " + quantity);
        this.totalProductsCost += calculateProductsCost(productPrice, quantity);
        this.storeProductsCount += 1;
        this.quantityCount += quantity;
    }

    public void endDailyTransactions() {
        System.out.println("The store \"" + this.name + "\" at address: \"" + this.address + "\"  has been loaded " + this.storeProductsCount + " times today. Total number of items: " + this.quantityCount + ". Total amount spent: " + totalProductsCost);
    }

    private double calculateProductsCost(double price, int quantity) {
        return price * quantity;
    }

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public static void open() {
        System.out.println("Магазинът отвори. Добре дошли.");
    }

    public static void close() {
        System.out.println("Магазинът затвори. Довиждане.");
    }

    @Override
    public String toString() {
        return "Store{" +
                "name=" + name +
                ", address='" + address + '\'' +
                ", totalProductsCost=" + totalProductsCost+
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalProductsCost() {
        return totalProductsCost;
    }
}
