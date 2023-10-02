public class Car extends MotorVehicle {
    private int doorsCount;
    private int trunkCapacityInLiters;
    private double totalWeightLoaded;


    public double getTotalWeightLoaded() {
        return totalWeightLoaded;
    }

    public void setTotalWeightLoaded(double totalWeightLoaded) {
        this.totalWeightLoaded = totalWeightLoaded;
    }

    Car(String brand, String model, int price, int doorsCount, int trunkCapacityInLiters) {
        super(brand, model, price);
        this.doorsCount = doorsCount;
        this.trunkCapacityInLiters = trunkCapacityInLiters;
    }

    void putLuggage(String name, double weight) {
        setTotalWeightLoaded(getTotalWeightLoaded() + weight);
        System.out.println(getBrandAndModel() + " with " + this.doorsCount + " doors and a trunk size of " +
                this.trunkCapacityInLiters + " liters, has been loaded with " + weight + " kg. of " + name +
                ". Total weight loaded: " + getTotalWeightLoaded() + " kg.");
    }
}
