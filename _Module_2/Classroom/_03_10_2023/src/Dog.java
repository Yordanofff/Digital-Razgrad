public class Dog extends Pet{
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    private String breed;

    public Dog(String name, int age, double weight, String breed) {
        super(name, age, weight);
        this.breed = breed;
    }

    public Dog(String breed) {
        this.breed = breed;
    }

    @Override
    public void eat() {
        System.out.println(super.getName() + " is eating like a dog");
    }

    @Override
    public void sleep() {
        System.out.println(super.getName() + " is sleeping like a dog");
    }

    public void play(String toy) {
        System.out.println(this.getName() + " is playing with his favourite dog toy: " + toy);
    }
}
