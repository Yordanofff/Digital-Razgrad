public class Mountain {
    String name;
    int heightInMeters;
    int weightInKg;
    int volumeInSquareMeters;

    public void printInfo() {
        System.out.println("Име на планината: " + this.name);
        System.out.println("Височина: " + this.heightInMeters + " м.");
        System.out.println("Тежи: " + this.weightInKg + " кг.");
        System.out.println("Обем: " + this.volumeInSquareMeters + " м2");
    }

//    Mountain() {
//        this.name = "dada";
//    }

    Mountain(String name, int heightInMeters, int weightInKg, int volumeInSquareMeters) {
        this.name = name;
        this.heightInMeters = heightInMeters;
        this.weightInKg = weightInKg;
        this.volumeInSquareMeters = volumeInSquareMeters;
    }

}


