public class Main {
    public static void main(String[] args) throws Exception {
        UserPassword up = new UserPassword();
//        up.password = "Yo yo yo";
//        System.out.println(up.isSpaceInPassword());
//        System.out.println(up.isDigitInPassword());
        up.getUserPasswordFromFile();
    }
}
