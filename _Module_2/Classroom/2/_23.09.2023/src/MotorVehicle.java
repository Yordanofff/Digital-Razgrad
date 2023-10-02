public class MotorVehicle {
    private String brand;
    private String model;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    void sell(String newOwner, int price) {
        System.out.println(getBrandAndModel() + " that was previously bought for " + getPrice() + " was now sold to: " + newOwner + " for " + price);
        setPrice(price);
    }

    String getBrandAndModel() {
        return this.brand + " " + this.model;
    }

    MotorVehicle(String brand, String model, int price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }
}
