public class Main {
    public static void main(String[] args) {
        Task1(4);  // Will also work with other values
        
    }

    public static void Task1(int num) {
        //Да се напише метод, който връща като резултат правоъгълен триъгълник
        //от ‘@’, който има 4 реда и изглежда по този начин:
        //      @
        //    @ @
        //  @ @ @
        //@ @ @ @


        int j = (num * 2) -3;
        String symbols_to_print = " @";

        for (int i=1; i<=num; i++){
            System.out.print(" ".repeat(j));
            if (j>2) {
                j-=2;
            } else {
                j = 0;
            }

            String to_print = symbols_to_print.repeat(i) + "\n";

            // remove the space in the beginning of the string on the last row.
            if (i==num) {
                to_print = to_print.trim();
            }

            System.out.print(to_print);

        }
    }
}