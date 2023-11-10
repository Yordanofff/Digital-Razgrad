package Task2.Homework10;

public class StringFormatting {

    public static String formatToDecimalPoints(int numberOfSymbolsToDisplay, double numberToConvert) {
        String format_string = "%." + numberOfSymbolsToDisplay + "f";
        return String.format(format_string, numberToConvert);
    }

}
