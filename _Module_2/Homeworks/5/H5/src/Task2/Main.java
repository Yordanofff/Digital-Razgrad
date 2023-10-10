package Task2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    //Да се направи клас Tetragon (четириъгълник), който има 4 полета: дължините на своите
    //страни. И метод calculatePerimeter(), който връща като резултат периметър на
    //четириъгълника. Предефинирайте toString() като отпечатва името на фигурата и дължините
    //на 4-те страни. Както и 2 конструктора, използвайте правилни нива на достъп.
    //● Да се направи клас Rectangle (правоъгълник), който наследява Tetragon и предефинира
    //метода calculatePerimeter(). Предефинирайте toString(), така че да отпечатва 2-те различни
    //страни на правоъгълник.
    //● Да се направи клас Square (квадрат), който наследява Tetragon и предефинира метода
    //calculatePerimeter(). Предефинирайте toString(), така че да отпечатва страната на квадрата.
    //● Създайте масив с 6 елемента - по 2 от всеки тип. За всеки елемент на масива отпечатайте
    //информация за фигурата и периметъра ѝ.
    public static void main(String[] args) {
        printObjectInfo();
    }

    public static void printObjectInfo() {
        List<Tetragon> figures = new ArrayList<>();
        figures.add(new Tetragon());
        figures.add(new Tetragon(5, 2, 3, 6));
        figures.add(new Rectangle());
        figures.add(new Rectangle(6, 3));
        figures.add(new Square());
        figures.add(new Square(8));

        for (Tetragon figure : figures) {
            System.out.println(figure);
            System.out.println("This " + figure.getClass().getSimpleName() + " perimeter is: " + figure.calculatePerimeter());
            System.out.println();
        }
    }
}
