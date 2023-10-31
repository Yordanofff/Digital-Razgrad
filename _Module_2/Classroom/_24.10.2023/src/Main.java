public class Main {
    public static void main(String[] args) {
        Room r1 = new Room();
        Room r2 = new Room("Red",20);
        Room r3 = new Room("Pink", 30);
        EntryDoor entryDoor = new EntryDoor(200, 80, "Brown", true);

        Room[] rooms = {r1, r2, r3};
        Apartment a1 = new Apartment();
        Apartment a2 = new Apartment(entryDoor, rooms, "Razgrad, ul. Ivan Ivanov 22", 5);


        System.out.println(a1);
        System.out.println(a2);
        a2.setRoomColor(0,"Dark Red");
        System.out.println(a2);
        a2.setRoomColor(3,"Dark Blue");
    }
}