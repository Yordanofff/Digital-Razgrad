import static java.lang.System.exit;

public class MotorBike extends MotorVehicle {
    private int engineVolume;
    private String category; // (пистов, крос, ендуро)

    public int getEngineVolume() {
        return this.engineVolume;
    }

    private void setEngineVolume(int engineVolume) {
        if (engineVolume < 0) {
            System.out.println("Error: Engine size cannot be < 0");
        } else if (engineVolume > 2500) {
            System.out.println("Error: Engine size cannot be > 2500");
        } else {
            this.engineVolume = engineVolume;
        }
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        if (!((category.equalsIgnoreCase("пистов")) ||
                (category.equalsIgnoreCase("крос")) ||
                (category.equalsIgnoreCase("ендуро")))) {
            System.out.println("Wrong category. Possible options: (пистов, крос, ендуро)");
        // exit(1);
            this.category = "N/A";
        } else {
            this.category = category;
        }
    }

    public void changeEngine(int engineVolume) {
        System.out.println("The engine of " + getBrandAndModel() + " was updated to " + engineVolume + " cc.");
        setEngineVolume(engineVolume);
    }

    MotorBike(String brand, String model, int price, int engineVolume, String category) {
        super(brand, model, price);
        this.engineVolume = engineVolume;
        setCategory(category);
    }

    public void rideOnRearWheel(int duration) {
        System.out.println(getCategory() + " motor " + getBrandAndModel() + " with " + getEngineVolume() +
                " cc engine has been riding on it's rear wheel for " + duration + " seconds.");
    }
}
