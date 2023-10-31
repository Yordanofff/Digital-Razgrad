public class Room {
    private String wallColor;
    private int sizeInSQM;

    public Room(String wallColor, int sizeInSQM) {
        this.wallColor = wallColor;
        this.sizeInSQM = sizeInSQM;
    }
    public Room() {
        this.wallColor = "White";
        this.sizeInSQM = 25;
    }

    public void rePaint(String color) {
        setWallColor(color);
    }

    public void clean() {
        System.out.println("The room has been cleaned");
    }

    public String getWallColor() {
        return wallColor;
    }

    public void setWallColor(String wallColor) {
        this.wallColor = wallColor;
    }

    public int getSizeInSQM() {
        return sizeInSQM;
    }

    public void setSizeInSQM(int sizeInSQM) {
        this.sizeInSQM = sizeInSQM;
    }

    @Override
    public String toString() {
        return "Room{" +
                "wallColor='" + wallColor + '\'' +
                ", sizeInSQM=" + sizeInSQM +
                '}';
    }
}
