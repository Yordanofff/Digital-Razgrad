public class Iguana extends Pet{
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Iguana(String name, int age, double weight, int length) {
        super(name, age, weight);
        this.length = length;
    }

    public Iguana(int length) {
        this.length = length;
    }

    @Override
    public void eat() {
        System.out.println(super.getName() + " is eating like an iguana");
    }

    @Override
    public void sleep() {
        System.out.println(super.getName() + " is sleeping like an iguana");
    }

    public void play(String toy) {
        System.out.println(this.getName() + " is playing with his favourite iguana toy: " + toy);
    }

}
