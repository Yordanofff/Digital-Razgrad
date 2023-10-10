package Task2;

public class Tetragon {
    private int a;
    private int b;
    private int c;
    private int d;

    public int calculatePerimeter() {
        return this.a + this.b + this.c + this.d;
    }
    @Override
    public String toString() {
        return "Tetragon{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
    public Tetragon(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public Tetragon() {
        this.a = 10;
        this.b = 15;
        this.c = 12;
        this.d = 13;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
}
