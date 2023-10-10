public class Cat extends Pet{
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Cat(String name, int age, double weight, String color) {
        super(name, age, weight);
        this.color = color;
    }

    public Cat(String color) {
        this.color = color;
    }

    @Override
    public void eat() {
        System.out.println(super.getName() + " is eating like a cat");
    }

    @Override
    public void sleep() {
        System.out.println(super.getName() + " is sleeping like a cat");
    }

    public void play(String toy) {
        System.out.println(this.getName() + " is playing with his favourite cat toy: " + toy);
    }
}
