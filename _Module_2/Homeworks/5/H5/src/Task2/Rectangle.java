package Task2;

public class Rectangle extends Tetragon {
    private int a;
    private int b;

    public int calculatePerimeter() {
        return (this.a + this.b) * 2;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "a=" + a +
                ", b=" + b +
//                ", c=" + a +
//                ", d=" + b +
                '}';
    }

    public Rectangle(int a, int b) {
        super();
        this.a = a;
        this.b = b;
    }

    public Rectangle() {
        this.a = 5;
        this.b = 10;
    }
}
