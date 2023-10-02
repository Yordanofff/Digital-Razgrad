import static java.lang.System.exit;

public class House extends Building{
    private String roofType;
    private int gardenSizeSquareMeters;

    public House(String address, int length, int width, int height, int price, String roofType, int gardenSizeSquareMeters) {
        super(address, length, width, height, price);
        this.roofType = roofType;
        this.buildingType = "house";
        setGardenSizeSquareMetersInit(gardenSizeSquareMeters, length, width);
    }

    private int getMaxGardenSizeInit(int length, int width) {
        return length * width;
    }

    private void setGardenSizeSquareMetersInit(int gardenSizeSquareMeters, int length, int width) {
        int maxGardenSize = getMaxGardenSizeInit(length, width);
        if (gardenSizeSquareMeters > maxGardenSize) {
            System.out.println("The garden cannot be " + gardenSizeSquareMeters + "sq.m. when the house dimensions are: Length: " + super.getLength() + " Width: " + super.getWidth() + ". Max size: " + maxGardenSize + "sq.m.");
            exit(1);
        } else {
            this.gardenSizeSquareMeters = gardenSizeSquareMeters;
        }
    }

    public int getGardenSizeSquareMeters() {
        return this.gardenSizeSquareMeters;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public void slantMeadow() {
        System.out.println(super.getTheBuildingForPrinting() + "just got it's grass cut.");
    }
}
