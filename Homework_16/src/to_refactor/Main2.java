public class Main2 {
    static String monkeyATriangle() {

        return "@";

    }
    public static void main(String[] args) {
System.out.println("   "+monkeyATriangle());
System.out.println("  "+monkeyATriangle()+monkeyATriangle());
System.out.println(" " +monkeyATriangle()+monkeyATriangle()+monkeyATriangle());
System.out.println(monkeyATriangle()+monkeyATriangle()+monkeyATriangle()+monkeyATriangle());
    }
}

