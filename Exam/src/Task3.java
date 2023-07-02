public class Task3 {
    static String[] SYMBOLS_TO_REMOVE_FROM_BEGINNING_OR_END_OF_WORD = {"[", "]", ".", "!", ",", "?"};

    public static void main(String[] args) {
        // Task 3:
        runApp();
    }

    public static void runApp() {
        String text = "This text! is a sample Text that I will use it to do this task. Nice text I guess. And [tExt] in brackets.";

        System.out.println("Text to check: " + text);
        System.out.println("The most common word in the text is: " + getMostCommonWord(text));
        System.out.println("It is found " + getNumberOfTimesMostCommonWordIsInText(text) + " times.");

    }

    public static int getNumberOfTimesMostCommonWordIsInText(String text) {
        int maxNum = 0;
        for (String word : text.split(" ")) {
            word = clearUpWord(word);
            int currentWordCount = countNumberOfOccurrenceInText(word, text);
            if (currentWordCount > maxNum) {
                maxNum = currentWordCount;
            }
        }
        return maxNum;
    }

    public static String getMostCommonWord(String text) {
        int currentMax = 0;
        String currentWord = "";
        for (String word : text.split(" ")) {
            word = clearUpWord(word);
            int currentWordCount = countNumberOfOccurrenceInText(word, text);
            if (currentWordCount > currentMax) {
                currentMax = currentWordCount;
                currentWord = word;
            }
        }
        return currentWord;
    }

    public static int countNumberOfOccurrenceInText(String wordToCount, String text) {
        int found = 0;
        for (String word : text.split(" ")) {
            word = clearUpWord(word);
            if (word.equalsIgnoreCase(wordToCount)) {
                found += 1;
            }
        }
        return found;
    }

    public static String clearUpWord(String word) {
        // remove symbols at the beginning or end of word
        for (String symbol : SYMBOLS_TO_REMOVE_FROM_BEGINNING_OR_END_OF_WORD) {
            if (word.endsWith(symbol)) {
                word = word.substring(0, word.length() - 1);
            }
            if (word.startsWith(symbol)) {
                word = word.substring(1);
            }
        }
        return word.toLowerCase();
    }
}
