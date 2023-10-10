package Task2;

public class Square extends Tetragon {
    private int a;

    public Square(int a) {
        super();
        this.a = a;
    }

    public Square() {
        this.a = 10;
    }

    @Override
    public int calculatePerimeter() {
        return this.a * 4;
    }

    @Override
    public String toString() {
        return "Square{" +
                "a=" + a +
//                ", b=" + a +
//                ", c=" + a +
//                ", d=" + a +
                '}';
    }
}
