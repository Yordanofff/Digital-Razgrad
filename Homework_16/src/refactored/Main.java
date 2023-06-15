package refactored;

public class Main {
    public static void main(String[] args) {
        String str = "tova e programa";
        System.out.println(reverse(str));
    }

    public static String reverse(String str) {
        char[] charArr = str.toCharArray();
        char[] newCharArr = new char[charArr.length];

        for (int i = 0; i < charArr.length; i++) {
            newCharArr[charArr.length - i - 1] = charArr[i];
        }

        return String.valueOf(newCharArr);
    }
}
