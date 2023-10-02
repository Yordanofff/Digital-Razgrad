public class Main {
    public static void main(String[] args) {
        // Да се направи клас MotorVehicle с полета: марка, модел, цена и метод sell(String newOwner).
        //Да се добави клас MotorBike, който наследява MotorVehicle с полета: категория (пистов, крос, ендуро) и кубатура. И метод rideOnRearWheel(int duration)
        //Да се добави клас Car, който наследява MotorVehicle с полета: брой врати, капацитет на багажник. И метод putLuggage(String name, double weight).

        Car car1 = new Car("Skoda", "Octavia", 5000, 4, 520);
        car1.putLuggage("potatoes", 200);
        car1.putLuggage("tomatoes", 250);
        car1.sell("Ivan", 2000);

        MotorBike bike1 = new MotorBike("Suzuki", "RMZ", 2500, 1000, "ендуро");
        bike1.rideOnRearWheel(20);
        bike1.changeEngine(200);
        bike1.rideOnRearWheel(25);
        }
    }