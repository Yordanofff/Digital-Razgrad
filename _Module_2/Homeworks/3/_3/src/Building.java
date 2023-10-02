public class Building {
    private String address;
    private int length;
    private int width;
    private int height;
    private int price;
    private String color = "N/A";

    protected String buildingType = "building";

    String getAddress() {
        return this.address;
    }

    protected String getTheBuildingForPrinting() {
        return "The " + this.buildingType + " at \"" + this.address + "\" address ";
    }

    public Building(String address, int length, int width, int height, int price) {
        this.address = address;
        this.length = length;
        this.width = width;
        this.price = price;
    }

    private void setColor(String color) {
        if (this.color.equalsIgnoreCase(color)) {
            System.out.println("Warning. " + getTheBuildingForPrinting() + "is already painted in " + color + " and it won't be re-painted.");
        } else {
            this.color = color;
            System.out.println(getTheBuildingForPrinting() + "was painted in " + color);
        }
    }

    private void setPrice(int price) {
        this.price = price;
    }

    private void increasePrice(int priceIncrease) {
        int newPrice = this.price + priceIncrease;
        setPrice(newPrice);
    }

    public void paint(String color) {
        setColor(color);
    }

    public void furnish(String furniture, int price) {
        System.out.println(getTheBuildingForPrinting() + "just got new furniture: " + furniture + " that cost " + price + ". The price of the house increases.");
        increasePrice(price);
    }

    public int getPrice() {
        return this.price;
    }

    public int getLength() {
        return this.length;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }
}
