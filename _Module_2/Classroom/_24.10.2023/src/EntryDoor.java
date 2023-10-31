public class EntryDoor {
    private int height;
    private int width;
    private String color;
    public boolean isLocked;

    public void lock() {
        isLocked = true;
        System.out.println("The door was locked.");
    }
     public void unlock() {
        isLocked = false;
        System.out.println("The door was unlocked.");
    }
    public EntryDoor() {
        this.height = 180;
        this.width = 80;
        this.color = "White";
        this.isLocked = false;
    }
    public EntryDoor(int height, int width, String color, boolean isLocked) {
        this.height = height;
        this.width = width;
        this.color = color;
        this.isLocked = isLocked;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

//    @Override
//    public String toString() {
//        return "EntryDoor{" +
//                "height=" + height +
//                ", width=" + width +
//                ", color='" + color + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "EntryDoor{" +
                "height=" + height +
                ", width=" + width +
                ", color='" + color + '\'' +
                ", isLocked=" + isLocked +
                '}';
    }
}
