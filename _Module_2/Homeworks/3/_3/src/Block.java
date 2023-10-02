public class Block extends Building {
    private int numberOfFloors;
    private boolean isLift;
    private boolean isIsolation = false;

    public Block(String address, int length, int width, int height, int price, int numberOfFloors, boolean isLift) {
        super(address, length, width, height, price);
        this.numberOfFloors = numberOfFloors;
        this.isLift = isLift;
        this.buildingType = "block";
    }

    public int getNumberOfFloors() {
        return this.numberOfFloors;
    }

    public boolean isLift() {
        return isLift;
    }

    public void termalIsolation() {
        if (!isIsolation) {
            System.out.println(super.getTheBuildingForPrinting() + "just got termal isolation done.");
            setTermalIsolation(true);
        } else {
            System.out.println(super.getTheBuildingForPrinting() + "already has isolation.");
        }
    }

    public boolean getTermalIsolation() {
        return this.isIsolation;
    }

    private void setTermalIsolation(boolean isIsolation) {
        this.isIsolation = isIsolation;
    }

}
