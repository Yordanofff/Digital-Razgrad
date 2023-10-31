import java.util.Arrays;

public class Apartment {
    private EntryDoor entryDoor;
    private Room[] rooms;
    private String address;
    private int floorNumber;
    private boolean isEntryDoorLocked;

    public Apartment(EntryDoor entryDoor, Room[] rooms, String address, int floorNumber) {
        this.entryDoor = entryDoor;
        this.rooms = rooms;
        this.address = address;
        this.floorNumber = floorNumber;
        this.isEntryDoorLocked = entryDoor.isLocked;
    }

    public Apartment() {
        Room r1 = new Room();
        Room r2 = new Room();
        Room[] rooms = {r1, r2};
        this.entryDoor = new EntryDoor();
        this.rooms = rooms;
        this.address = "N/A";
        this.floorNumber = 1;
        this.isEntryDoorLocked = this.entryDoor.isLocked;
    }

    public void setRoomColor(int roomIndex, String newColor) {
        if (roomIndex >= 0 && roomIndex < rooms.length) {
            rooms[roomIndex].rePaint(newColor);
        } else {
            System.out.println("Invalid room index.");
        }
    }

    public void lockEntryDoor() {
        this.entryDoor.lock();
    }

    public void unlockEntryDoor() {
        this.entryDoor.unlock();
    }

//    @Override
//    public String toString() {
//        return "Apartment{" +
//                "entryDoor=" + entryDoor +
//                ", rooms=" + Arrays.toString(rooms) +
//                ", address='" + address + '\'' +
//                ", floorNumber=" + floorNumber +
//                '}';
//    }


    @Override
    public String toString() {
        return "Apartment{" +
                "entryDoor=" + entryDoor +
                ", rooms=" + Arrays.toString(rooms) +
                ", address='" + address + '\'' +
                ", floorNumber=" + floorNumber +
                ", isEntryDoorLocked=" + isEntryDoorLocked +
                '}';
    }
}
