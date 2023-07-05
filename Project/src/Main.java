import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;


// todo - write tests

// todo option - print units and amount that can be fit on shelf
// todo - move units to a config file
// todo option - allow user to modify amount units/kg + warehouse size


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String SEPARATOR = ";";
    static String SEPARATOR_WHEN_PRINTING = " | ";
    static String DB_FILE_NAME = "app_data.csv";

//    static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    static char[] LETTER_SECTION = "ab".toCharArray();
    static int NUM_SECTION = 3;
    static int NUM_SHELVES = 3;
    static int NUM_NUMBERS = 4;

    static Map<String, Integer> UNIT_OPTIONS = Map.of(
            //Unit type - number of items per shelf
            "kg", 100,
            "liter", 100,
            "meter", 10,
            "unit", 1000
    );

    static String[] USER_QUESTIONS = new String[]{"product name", "expiry date", "entry date", "manufacturer", "unit",
            "available stock", "comment (optional)"};

    // Starting at the second element - "Name" not printed.
    static String[] DESCRIPTION = new String[]{"Name", "Expiry date", "Entry date", "Manufacturer", "Unit", "Stock", "Position", "Available items at shelf", "Comment"};

    @SuppressWarnings("FieldMayBeFinal")
    private static List<String> TEMP_USED_LOCATIONS_NOT_IN_DB = new ArrayList<>();

    static String[] ANSWERS = new String[USER_QUESTIONS.length];


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BOLD = "\u001B[1m";

    static final String[] SPECIAL_CMDS = new String[]{"reset!", "stop!"};
    static final List<String> INPUT_INFO = Arrays.asList("Use \"" + getBoldYellowMsg("reset!") + "\" to reset the data input.",
            "Use \"" + getBoldYellowMsg("stop!") + "\" to go back to the menu.",
            "Dates need to be formatted as " +
                    getBoldYellowMsg("dd.mm.yyyy") + " or " +
                    getBoldYellowMsg("d.m.yyyy") + " and can be separated by " +
                    getBoldYellowMsg(".") + " or " + getBoldYellowMsg("/"),
            "Expiry date can be " + getBoldYellowMsg("today") + ", " + getBoldYellowMsg("tomorrow") + " or " + getBoldYellowMsg("n/a"),
            "Entry date can be " + getBoldYellowMsg("today"),
            "Units can be: " + getBoldYellowMsg(UNIT_OPTIONS.keySet().toString()),
            "Forbidden character: " + getBoldYellowMsg(SEPARATOR));

    static final int MIN_NUMBER_OF_SPACES_ON_EACH_SIDE_OF_MENU = 5;

    private static final DecimalFormat df = new DecimalFormat("0.0");

    public static void main(String[] args) throws IOException, ParseException {
        runApp();
    }

    /**
     * This method will print the description + the information from the DB in a colored frame.
     * ======================================================================================================================================
     * | NAME      | EXPIRY DATE | ENTRY DATE | MANUFACTURER          | UNIT  | STOCK | POSITION   | AVAILABLE ITEMS AT SHELF | COMMENT     |
     * ======================================================================================================================================
     * | Milk      | 02.03.1234  | 03.04.4231 | Alfatar               | unit  | 200   | b1 / 1 / 3 | 1000                     | 3%          |
     * ...
     *
     * @param rows      - product information from DB
     * @param colorANSI - color of the frame
     */
    public static void printDBInFrameWithDescription(ArrayList<ArrayList<String>> rows, String colorANSI) {
        if (rows.size() == 0)
            throw new RuntimeException("DB file " + DB_FILE_NAME + " is empty.");

        // Make sure all rows are same length - some don't have comments and will be 8, other 9 --> all 9.
        for (List<String> row : rows) {
            while (row.size() < DESCRIPTION.length) {
                row.add("");
            }
        }

        // Get the maximum word length of every column
        // Will be something like [9, 23, 22, 18, 10, 11, 20, 30, 18]
        int[] arrayWithMaxLengths = new int[DESCRIPTION.length];
        for (ArrayList<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                // rows.size() == DESCRIPTION_WITH_NAME.length == 9
                if (arrayWithMaxLengths[i] < row.get(i).length()) {
                    arrayWithMaxLengths[i] = row.get(i).length();
                }
            }
        }
        // Check the length of the description in case it has more symbols than the data field
        for (int i = 0; i < DESCRIPTION.length; i++) {
            if (arrayWithMaxLengths[i] < DESCRIPTION[i].length()) {
                arrayWithMaxLengths[i] = DESCRIPTION[i].length();
            }
        }

        int numSymbolsTopBottom = Arrays.stream(arrayWithMaxLengths).sum() +
                SEPARATOR_WHEN_PRINTING.length() * (DESCRIPTION.length + 1) - 2;

        String colored_frame = getColoredMsg("=".repeat(numSymbolsTopBottom), colorANSI);
        String colored_separator_no_space_in_front = getColoredMsg(SEPARATOR_WHEN_PRINTING.strip() + " ", colorANSI);

        // Print frame above description
        System.out.println("\n" + colored_frame);

        // Print description for each column
        System.out.print(colored_separator_no_space_in_front);
        for (int i = 0; i < arrayWithMaxLengths.length; i++) {
            String formatString = "%-" + arrayWithMaxLengths[i] + "s" + getColoredMsg(SEPARATOR_WHEN_PRINTING, colorANSI);
            System.out.printf(formatString, DESCRIPTION[i].toUpperCase());
        }

        // Print frame below description
        System.out.println("\n" + colored_frame);

        // Print all rows with data from the DB
        for (ArrayList<String> row : rows) {
            System.out.print(colored_separator_no_space_in_front);
            for (int i = 0; i < arrayWithMaxLengths.length; i++) {
                // "%" + numberOfSpaces + "s", "text To Print"
                // The formatString will be something like "%-25s | ". The "-" is used for left alignment
                String formatString = "%-" + arrayWithMaxLengths[i] + "s" + getColoredMsg(SEPARATOR_WHEN_PRINTING, colorANSI);

                System.out.printf(formatString, row.get(i));
            }
            System.out.println();
        }

        // print frame at the bottom
        System.out.println(colored_frame + "\n");
    }


    /**
     * This method will be used to print the menu options. Adds a frame and prints the words
     * in the middle, surrounded by at least n spaces (for the longest word) and more for the rest.
     * ===============================================
     * |     menuTopQuestion                         |
     * ===============================================
     * |     1 - menuOptions 1                       |
     * |     2 - menuOptions 2                       |
     * |     3 - menuOptions 3 ...                   |
     * ===============================================
     *
     * @param menuTopQuestion - will be surrounded by a thick frame
     * @param menuOptions     - list of items to be printed
     * @param ANSI_color      - color of the frame
     */
    public static void printMenuOptionsInFrame(String menuTopQuestion, List<String> menuOptions, String ANSI_color) {
        // Create a new list using (menuOptions + menuTopQuestion) and find the longest row from both.
        // Checking menuTopQuestion too, in case it's longer than the options below.
        List<String> menuQuestionAndOptions = new ArrayList<>(menuOptions);
        menuQuestionAndOptions.add(menuTopQuestion);
        int longestRowInMsg = getLengthOfTheLongestRowInList(menuQuestionAndOptions);

        int frameLength = longestRowInMsg + MIN_NUMBER_OF_SPACES_ON_EACH_SIDE_OF_MENU * 2 + 2;

        String coloredFrameHorizontal = getColoredMsg("=".repeat(frameLength), ANSI_color);
        String coloredFrameAndSpacesBeginningOfRow = getColoredMsg("|" + " ".repeat(MIN_NUMBER_OF_SPACES_ON_EACH_SIDE_OF_MENU), ANSI_color);

        System.out.println(coloredFrameHorizontal);

        System.out.print(coloredFrameAndSpacesBeginningOfRow + menuTopQuestion);
//        System.out.println(getColoredMsg(" ".repeat(frameLength -
//                (menuTopQuestion.length() + MIN_NUMBER_OF_SPACES_ON_EACH_SIDE_OF_MENU + 2)) + "|", ANSI_color));
        System.out.println(getColoredMsg(" ".repeat(frameLength -
                (getLengthOfTheLongestRowInList(Collections.singletonList(menuTopQuestion)) + MIN_NUMBER_OF_SPACES_ON_EACH_SIDE_OF_MENU + 2)) + "|", ANSI_color));

        System.out.println(coloredFrameHorizontal);

        for (String currentQuestion : menuOptions) {
            System.out.print(coloredFrameAndSpacesBeginningOfRow + currentQuestion);
            System.out.println(getColoredMsg(" ".repeat(frameLength -
                    (getLengthOfTheLongestRowInList(Collections.singletonList(currentQuestion)) + MIN_NUMBER_OF_SPACES_ON_EACH_SIDE_OF_MENU + 2)) + "|", ANSI_color));
        }

        System.out.println(coloredFrameHorizontal);
    }

    public static void runApp() throws IOException, ParseException {
        createDBfileIfMissing();
        while (printMenuGetAnsAndTakeAction()) {
            System.out.println();
        }
    }

    public static boolean printMenuGetAnsAndTakeAction() throws IOException, ParseException {
        int option = printMenuOptions();
        return takeMenuAction(option);
    }

    public static boolean takeMenuAction(int selectedOption) throws IOException, ParseException {
        switch (selectedOption) {
            case 1 -> printDBInFrameWithDescription(getDbDataToArrayList(), ANSI_GREEN);
            case 2 -> getAllUserDataAndWriteToDB();
            case 3 -> printDataForTimePeriod();
            case 4 -> printAllEmptyLocations();
            case 5 -> printFullLocations();
            case 6 -> printAllLocationsColoredFullPartlyEmpty();
            case 7 -> printStockExpiringSoon();
            case 8 -> printExpiredStock();
            case 9 -> printWarehouseInfo();
            case 10 -> {
                return false;
            }
        }
        return true;
    }

    /**
     * Builds the menu questions and calls the method to print them.
     * @return valid answer, depending on the number of questions
     */
    public static int printMenuOptions() {
        String[] menuOptions = new String[]{"List all items", "Add new delivery",
                "List deliveries for time period", "Print all empty locations", "Print all full locations", "Print color-coded locations",
                "Print stock expiring soon", "Print Expired stock", "Print Warehouse Info", "Exit"};

        // Add all questions to a List with numbers for the options
        List<String> menuOptionsWithNumbers = new ArrayList<>();
        for (int i = 1; i <= menuOptions.length; i++) {
            menuOptionsWithNumbers.add(i + " - " + menuOptions[i - 1]);
        }

        printMenuOptionsInFrame("Please choose what to do:", menuOptionsWithNumbers, ANSI_GREEN);

        return getMenuAnswer(menuOptions.length);
    }

    /**
     * Loops until the entered value is a possible option in the menu.
     *
     * @param numOptions - the biggest available option number
     * @return A valid option in the menu.
     */
    public static int getMenuAnswer(int numOptions) {
        int ans = scanner.nextInt();
        scanner.nextLine();

        // Keep printing the error msg if answer is less than 1 or greater than the highest option number
        while (ans < 1 || ans > numOptions) {
            printError("Please choose a number between 1 and " + numOptions);
            ans = scanner.nextInt();
            scanner.nextLine();
        }

        return ans;
    }

    public static void printStockExpiringSoon() throws IOException {
        System.out.println("Enter number of days to check: ");
        int daysToCheck = scanner.nextInt();
        scanner.nextLine();

        ArrayList<ArrayList<String>> rowsNotExpired = getStockExpiringSoon(daysToCheck);

        if (rowsNotExpired.size() > 0) {
            System.out.print(getColoredMsg("STOCK THAT WILL EXPIRE IN THE NEXT " + daysToCheck + " DAYS:", ANSI_YELLOW));
            printDBInFrameWithDescription(rowsNotExpired, ANSI_YELLOW);
        } else {
            System.out.print(getColoredMsg("NO STOCK WILL EXPIRE IN THE NEXT " + daysToCheck + " DAYS:", ANSI_GREEN));
        }
    }

    public static void printExpiredStock() throws IOException {
        ArrayList<ArrayList<String>> rowsExpired = getExpiredStock();

        if (rowsExpired.size() > 0) {
            System.out.print(getColoredMsg("ALREADY EXPIRED STOCK:", ANSI_RED));
            printDBInFrameWithDescription(rowsExpired, ANSI_RED);
        } else {
            System.out.print(getColoredMsg("NO EXPIRED STOCK:", ANSI_GREEN));
        }
    }

    public static ArrayList<ArrayList<String>> getExpiredStock() throws IOException {
        ArrayList<ArrayList<String>> rowsExpired = new ArrayList<>();
        ArrayList<ArrayList<String>> data = getDbDataToArrayList();
        for (ArrayList<String> row : data) {

            String expiryDate = row.get(1);

            int diff = getNumberOfDaysFromTodayToDate(expiryDate);

            ArrayList<String> rowExpired = new ArrayList<>();
            if (diff < 0) {
                rowExpired.addAll(row);
                rowsExpired.add(rowExpired);
            }
        }
        return  rowsExpired;
    }

    public static ArrayList<ArrayList<String>> getStockExpiringSoon(int numDays) throws IOException {
        ArrayList<ArrayList<String>> rowsNotExpired = new ArrayList<>();

        ArrayList<ArrayList<String>> data = getDbDataToArrayList();
        for (ArrayList<String> row : data) {

            String expiryDate = row.get(1);

            int diff = getNumberOfDaysFromTodayToDate(expiryDate);
            ArrayList<String> rowExpired = new ArrayList<>();
            ArrayList<String> rowNotExpired = new ArrayList<>();
            if (numDays >= diff && diff >= 0) {
                rowNotExpired.addAll(row);
                rowsNotExpired.add(rowNotExpired);
            }
        }
        return rowsNotExpired;
    }


    /**
     * Get the difference in days between a date and today.
     * if tomorrow --> 1, if yesterday --> -1
     *
     * @param dateToCheck - date in format dd.mm.yyyy or d.m.yyyy
     * @return 0 if dateToCheck=today, positive number if the day is in the future, negative number if an older date.
     */
    public static int getNumberOfDaysFromTodayToDate(String dateToCheck) {
        String today = getToday();
        int todaysDateInDays = getNumberOfDaysToDate(today);
        int dateToCheckInDays = getNumberOfDaysToDate(dateToCheck);
        return dateToCheckInDays - todaysDateInDays;
    }

    /**
     * Used to compare dates
     *
     * @param date - date in format dd.mm.yyyy or d.m.yyyy
     * @return number of days since year 1.
     */
    public static int getNumberOfDaysToDate(String date) {
        date = date.replaceAll("\\s", "");
        int year = Integer.parseInt(date.split("\\.")[2]);
        int month = Integer.parseInt(date.split("\\.")[1]);
        int day = Integer.parseInt(date.split("\\.")[0]);

        int numDaysInAllYears = 0;
        // Without the current year because it hasn't passed.
        for (int i = 1; i < year; i++) {
            if (isLeapYear(i)) {
                numDaysInAllYears += 366;
            } else {
                numDaysInAllYears += 365;
            }
        }

        int numDaysInAllMonths = 0;
        // Without the current month because it hasn't passed.
        for (int i = 1; i < month; i++) {
            int m_days = getDaysInMonth(year, i);
            numDaysInAllMonths += m_days;
        }

        return numDaysInAllYears + numDaysInAllMonths + day;
    }

    /**
     * Loops over all static values to get all possible warehouse positions.
     * Used to get the next free position/location to place items.
     *
     * @return list with all warehouse positions/locations
     */
    public static List<String> getAllPossibleLocations() {
        List<String> result = new ArrayList<>();

        // Rows/shelves/numbers start at 1.
        for (char c : LETTER_SECTION) {
            for (int i = 1; i <= NUM_SECTION; i++) {
                for (int j = 1; j <= NUM_SHELVES; j++) {
                    for (int k = 1; k <= NUM_NUMBERS; k++) {
                        String itemPosition = String.valueOf(c) + i + " / " + j + " / " + k;
                        result.add(itemPosition);
                    }
                }
            }
        }

        return result;
    }

    public static int getWarehouseSize() {
        List<String> all = getAllPossibleLocations();
        return all.size();
    }

    /**
     * This method does it all - gets information about the Warehouse and prints it
     */
    public static void printWarehouseInfo() throws IOException {
        List<String> results = new ArrayList<>();

        int numAllLocations = getWarehouseSize();
        int numEmptyLocations = getAllEmptyLocations().size();
        int numFullLocations = getFullLocations().size();
        int numUsed = numAllLocations - numEmptyLocations;
        float percentageUsed = ((float) numUsed / numAllLocations) * 100;
        String percentageUsedFormatted = String.format("%.2f%%", percentageUsed);

        results.add("Warehouse size: " + numAllLocations);
        results.add("Used locations: " + numUsed);
        results.add("Full locations: " + numFullLocations);
        results.add("Partly full locations: " + (numUsed - numFullLocations));
        results.add("Empty locations: " + numEmptyLocations);
        results.add("Percentage used: " + percentageUsedFormatted);

        // Sum up all different UNIT types and store the data to be used later on
        ArrayList<ArrayList<String>> data = getDbDataToArrayList();
        for (String unit : UNIT_OPTIONS.keySet()) {
            double total = 0;
            for (ArrayList<String> row : data) {
                if (!row.isEmpty()) {
                    if (row.get(4).equalsIgnoreCase(unit)) {
                        total += Double.parseDouble(row.get(5));
                    }
                }
            }
            results.add("Total number of " + unit + "(s): " + removeDecimalIfEndsOnZero(String.valueOf(total)));
        }

        // print the data - formatted in a frame
        printMenuOptionsInFrame("WAREHOUSE INFO", results, ANSI_GREEN);
    }

    public static void printAllLocationsColoredFullPartlyEmpty() throws IOException {
        // Full locations - red
        // Partly full locations - yellow
        // Empty locations - green

        List<String> allLocations = getAllPossibleLocations();
        List<String> emptyLocations = getAllEmptyLocations();
        List<String> fullLocations = getFullLocations();

        List<String> resultToPrint = new ArrayList<>();

        for (String location: allLocations) {
            if (emptyLocations.contains(location)) {
                resultToPrint.add(location);
            } else if (fullLocations.contains(location)) {
                resultToPrint.add(getColoredMsg(location, ANSI_RED));
            } else {
                resultToPrint.add(getColoredMsg(location, ANSI_YELLOW));
            }
        }

        String msg = getColoredMsg("Empty     ", ANSI_GREEN) + getColoredMsg("Partly full", ANSI_YELLOW) +
                getColoredMsg("     Full", ANSI_RED);

        printMenuOptionsInFrame(msg, resultToPrint, ANSI_GREEN);
    }

    public static List<String> getFullLocations() throws IOException {
        List<String> fullLocations = new ArrayList<>();
        List<String> usedLocations = getAllLocationsThatHaveAtLeastOneItem();
        for (String usedLocation: usedLocations) {
            if (getFreeSpaceAtLocationIfAtLeastOneItem(usedLocation) == 0) {
                fullLocations.add(usedLocation);
            }
        }
        return fullLocations;
    }

    public static void printFullLocations() throws IOException {
        List<String> fullLocations = getFullLocations();
        Collections.sort(fullLocations);

        int numFullLocations = fullLocations.size();

        printMenuOptionsInFrame("Number of full locations: " + numFullLocations, fullLocations, ANSI_GREEN);
    }

    public static List<String> getAllLocationsThatHaveAtLeastOneItem() throws IOException {
        List<String> result = new ArrayList<>();
        String[] DB = getDbDataToStringArray();

        for (String row : DB) {
            String currentElement = row.split(SEPARATOR)[6];
            if (!result.contains(currentElement)) {
                result.add(currentElement);
            }
        }

        // Don't use the same location that has been used, but not written to DB.
        if (!TEMP_USED_LOCATIONS_NOT_IN_DB.isEmpty()) {
            for (String tempLocation : TEMP_USED_LOCATIONS_NOT_IN_DB) {
                if (!result.contains(tempLocation)) {
                    result.add(tempLocation);
                }
            }
        }

        return result;
    }

    public static String getFirstEmptyLocation() throws IOException {
        List<String> allEmptyLocations = getAllEmptyLocations();

        if (allEmptyLocations.size() == 0) {
            throw new RuntimeException("DB file/Warehouse is full.");
        }

        // Sort the list to get the first position
        Collections.sort(allEmptyLocations);

        return allEmptyLocations.get(0);
    }

    public static List<String> getAllEmptyLocations() throws IOException {
        List<String> allPositions = getAllPossibleLocations();
        List<String> usedPositions = getAllLocationsThatHaveAtLeastOneItem();

        for (String usedPosition : usedPositions) {
            // No need to check if usedPosition in allPositions
            allPositions.remove(usedPosition);
        }

        return allPositions;
    }

    public static void printAllEmptyLocations() throws IOException {
        List<String> allEmptyLocations = getAllEmptyLocations();
        Collections.sort(allEmptyLocations);
        int numEmptyLocations = allEmptyLocations.size();

        printMenuOptionsInFrame("Number of empty locations: " + numEmptyLocations, allEmptyLocations, ANSI_GREEN);
    }

    /**
     * Need to check all rows that have the same Location/Position and calculate total number of Items added to that location
     * By design - only same item names + expiry date will be added to 1 location, so no need to check if they match.
     *
     * @param location - location to check
     * @return number of items that can be added to the specified location.
     * @throws IOException - if the location is empty (Number of items depends on the type of item) or if the location
     *                     doesn't exist in the DB (Wrong location/typo)
     */
    public static double getFreeSpaceAtLocationIfAtLeastOneItem(String location) throws IOException {
        List<String> allEmptyLocations = getAllEmptyLocations();
        if (allEmptyLocations.contains(location)) {
            throw new RuntimeException("Location " + location + " is empty and will fit different amount depending on the type of units.");
        }

        // This should never happen.
        List<String> allLocations = getAllPossibleLocations();
        if (!allLocations.contains(location)) {
            throw new RuntimeException("Location " + location + " doesn't exist in the warehouse.");
        }

        String[][] DB = getAllDataFromDB();
        double numItems = 0;
        double maxNumItems = 0;

        for (String[] row : DB) {
            if (row[6].equalsIgnoreCase(location)) {
                double currentRowStock = Double.parseDouble(row[5]);
                numItems += currentRowStock;

                maxNumItems = Double.parseDouble(row[7]);
            }
        }
        // format to single digit, so it doesn't return "26.200000000000003"
        return Double.parseDouble(df.format(maxNumItems - numItems));
    }

    /**
     * Get the position where to place an item.
     * If item is already in the DB - find location where that item is. Then check if there are spaces left on
     * that shelf and if there are - return the cell location.
     * If item not in DB or no free space on shelf - returns the next free cell/position/location (if any) or throws
     * an error that the warehouse is full.
     *
     * @param itemName   - name of the item trying to add
     * @param expiryDate - expiry date of the item (needed because same expiry date items can be on the same place)
     * @throws IOException - if warehouse is full.
     */
    public static String getPositionToPlaceItem(String itemName, String expiryDate) throws IOException {
        List<String> locations = getAllUniqueLocationsForItem(itemName, expiryDate);

        // If the item is in the DB and there is free space for more items - return location
        for (String location : locations) {
            if (getFreeSpaceAtLocationIfAtLeastOneItem(location) > 0) {

                // Check if the location has been filled in, so that on the second iteration in addPositionAndMaxNumberToAllValidUserInput() while loop
                // this will not return the same location
                for (String loc : TEMP_USED_LOCATIONS_NOT_IN_DB) {
                    if (loc.equals(location)) {
                        return getFirstEmptyLocation();
                    }
                }

                return location;
            }
        }

        // If item not in DB or in DB but all locations are full - get the next free location.
        return getFirstEmptyLocation();
    }

    /**
     * Checks if a date is valid. Valid date is a date split by 2 dots and is a real date that exists.
     * The year needs to be 4 digits long.
     * A month needs to be a number between 1-12.
     * A day will be at least 1 and might be different depending on the month and year.
     * -
     * An error message with the problem will be printed if the date is not valid.
     * -
     * Example of valid dates:
     * 01.01.2020
     * 30.10.2022
     * 1.1.2020
     * " 1. 2 .   2010  "
     *
     * @param date - date formatted  "dd.mm.yyyy" or "d.m.yyyy". Will return false if not.
     * @return true if the date is valid, false if not.
     */
    public static boolean isDateValid(String date) {
        date = date.replaceAll("\\s", "");
        String[] parts = date.split("\\.");

        // Check if the formatting is correct - 2 dots -> xx.yy.zzzz
        if (parts.length != 3) {
            printError("The date is not split by 2 dots. Needs to be dd.mm.yyyy");
            return false;
        }

        // Check if all parts can be converted to integers and all are bigger than 1. Day, month and year can't be 0.
        for (int i = 0; i < 3; i++) {
            try {
                int num = Integer.parseInt(parts[i]);
                if (num < 1) {
                    printError("Every calendar Day and Month start from 1.");
                    return false;
                }
            } catch (Exception e) {
                printError(parts[i] + " cannot be converted to integer");
                return false;
            }
        }

        int numDay = Integer.parseInt(parts[0]);
        int numMonth = Integer.parseInt(parts[1]);
        int numYear = Integer.parseInt(parts[2]);

        // Month should be 1-12
        if (numMonth > 12) {
            printError("The month needs to be in the range 1-12.");
            return false;
        }

        // Year should be 4 digits long - ex. 2021
        if (parts[2].length() != 4) {
            printError("The year needs to be exactly 4 digits.");
            return false;
        }

        int daysInCurrentMonth = getDaysInMonth(numYear, numMonth);

        // Check if the month has that date.
        if (numDay > daysInCurrentMonth) {
            printError(getMonthNameFromNumber(numMonth) + " " + numYear + "y. has " + daysInCurrentMonth + " days. Invalid entry: " + numDay);
            return false;
        }

        return true;
    }

    public static String getMonthNameFromNumber(int n) {
        return switch (n) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "No such month";
        };
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else return year % 100 != 0;
    }

    public static int getDaysInMonth(int year, int month) {
        /*
         A leap year occurs nearly every 4 years which adds an extra day to February.
         February is the only month with 28 days.
         There are 30 days in April, June, September and November.
         There are 31 days in January, March, May, July, August, October, December.
         */

        int[] months30Days = {4, 6, 9, 11};
        int[] months31Days = {1, 3, 5, 7, 8, 10, 12};

        if (isCurrentMonthInArray(months30Days, month)) {
            return 30;
        }

        if (isCurrentMonthInArray(months31Days, month)) {
            return 31;
        }

        // February has 29 days if leap year and 28 if not.
        if (isLeapYear(year)) {
            return 29;
        } else {
            return 28;
        }
    }

    public static boolean isCurrentMonthInArray(int[] monthNumbers, int monthNumber) {
        for (int number : monthNumbers) {
            if (number == monthNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Write the validated user data + extra two columns (method addPositionAndMaxNumberToAllValidUserInput) in the DB file.
     */
    public static void getAllUserDataAndWriteToDB() throws IOException, ParseException {
        String[] allValidUserInput = getAllValidUserInput();
        List<List<String>> allData = addPositionAndMaxNumberToAllValidUserInput(allValidUserInput);

        for (List<String> row : allData) {
            writeDataToDB(row);
        }
        System.out.println("Product was added successfully!");
    }

    public static String removeDecimalIfEndsOnZero(String decimalNumber) {
        // .0 -> 0
        // .2 -> 0.2
        // 10.0 -> 10
        // 10.2 -> 10.2
        // 10 -> 10

        try {
            String firstPart = decimalNumber.split("\\.")[0];
            if (firstPart.equals("")) {
                firstPart = "0";
            }
            String secondPart = decimalNumber.split("\\.")[1];

            if (secondPart.equals("0")) {
                return firstPart;
            }

            // build it before returning it, so it adds 0 in front of to .2 -> 0.2
            return firstPart + "." + secondPart;
        } catch (RuntimeException e) {
            // the above will fail if num is integer
            return decimalNumber;
        }
    }

    /**
     * This method gets the validated answers of the used input and adds the remaining information that is needed before
     * the data is stored in the DB.
     * -
     * The data that the user will add will be something like:
     * "product name", "expiry date", "entry date", "manufacturer", "unit", "available stock", "comment (optional)"
     * -
     * Entered data will be something like:
     * {"Battery CR32", "24.11.2025", "02.06.2021", "Varta", "Item", "900", ""}
     * *
     * We need to add two more things:
     * - position where the items will be located in the warehouse (First possible place or another place where it
     * already exists and has the same expiry date.)
     * - number of items that can fit on a single shelf (from map)
     * * "Position" and "Max number of items on shelf" will be placed between "Stock" and "Comment".
     *
     * @param allValidUserInput - Array of all validated user inputs.
     * @return a String Array that will be written in the DB file.
     * Example:
     * "Battery CR32", "24.11.2025", "02.06.2021", "Varta", "Item", "900", "A3 / 4 / 10", "10000", "Some comment"
     * @throws IOException - if DB/Warehouse locations/positions are full (no free space left)
     */
    public static List<List<String>> addPositionAndMaxNumberToAllValidUserInput(String[] allValidUserInput) throws IOException {
        // Get the max number of items per shelf depending on the type of item
        double numberOfItemsThatCanFitOnShelf = UNIT_OPTIONS.get(allValidUserInput[4]);
        String totalNumberOfItemsThatCanFitOnShelfString = String.valueOf(numberOfItemsThatCanFitOnShelf);

        List<List<String>> newRowsToAdd = new ArrayList<>();

        String itemName = allValidUserInput[0];
        String expiryDate = allValidUserInput[1];
        double numItemsRemainingToAdd = Double.parseDouble(allValidUserInput[5]);  // Stock from user input
        while (numItemsRemainingToAdd > 0) {

            // Because we want to return a List, items won't be recorded in the DB on every iteration.
            // that's why there is a temp variable to store the locations that are already used but not written.
            String positionToPlaceItem = getPositionToPlaceItem(itemName, expiryDate);
            TEMP_USED_LOCATIONS_NOT_IN_DB.add(positionToPlaceItem);

            // If item is in DB - get free space in location
            double numItemsThatWillFitInLocation = numberOfItemsThatCanFitOnShelf;
            if (isAtLeastOneItemInLocation(positionToPlaceItem)) {
                numItemsThatWillFitInLocation = getFreeSpaceAtLocationIfAtLeastOneItem(positionToPlaceItem);
            }

            // There might be 100 free spaces but only 10 items need to be added
            double numItemsThatWillBeAddedToLocation = numItemsThatWillFitInLocation;
            if (numItemsRemainingToAdd < numItemsThatWillFitInLocation) {
                numItemsThatWillBeAddedToLocation = numItemsRemainingToAdd;
            }

            // List initialization is inside the loop, so that it stores the data in different memory
            List<String> allValidUserInputList = new ArrayList<>();
            for (String element : allValidUserInput) {
                allValidUserInputList.add(element);
            }
//            allValidUserInputList.set(5, removeDecimalIfEndsOnZero(String.valueOf(numItemsThatWillBeAddedToLocation)));
            allValidUserInputList.set(5, removeDecimalIfEndsOnZero(df.format(numItemsThatWillBeAddedToLocation)));
            allValidUserInputList.add(6, positionToPlaceItem);
            allValidUserInputList.add(7, removeDecimalIfEndsOnZero(totalNumberOfItemsThatCanFitOnShelfString));

            newRowsToAdd.add(allValidUserInputList);

            numItemsRemainingToAdd -= numItemsThatWillBeAddedToLocation;
        }

        // This can be added to a Finally block in writeDataToDB() but will work as good here too.
        // Even if it fails to write the data - it will start over and the list needs to be empty.
        TEMP_USED_LOCATIONS_NOT_IN_DB.clear();

        return newRowsToAdd;
    }

    /**
     * Gets all valid options from the user and takes action if any of the SPECIAL_CMDS are called.
     *
     * @return - an array of all valid answers. "Optional" answers can be empty. - will be written in the DB file.
     */
    public static String[] getAllValidUserInput() throws IOException, ParseException {
        // Clear ANSWERS - not necessary but better to have it.
        Arrays.fill(ANSWERS, null);

        System.out.println();
        printMenuOptionsInFrame("Enter the data for the items that you want to add:", INPUT_INFO, ANSI_GREEN);
        for (int i = 0; i < USER_QUESTIONS.length; i++) {

            String ans = getValidUserInput(USER_QUESTIONS[i]);

            // Don't allow ; to be added in the ans string as this is used in the CSV file and will mess things up.
            while (isSeparatorInAns(ans)) {
                printError("The symbol \"" + SEPARATOR + "\" can't be used. ");
                ans = getValidUserInput(USER_QUESTIONS[i]);
            }

            // SPECIAL_CMDS - take action
            if (ans.equalsIgnoreCase("reset!")) {
                System.out.println();
                Arrays.fill(ANSWERS, null);
                return getAllValidUserInput();
            } else if (ans.equalsIgnoreCase("stop!")) {
                Arrays.fill(ANSWERS, null);
                runApp();
            }

            ANSWERS[i] = ans;
        }

        return ANSWERS;
    }

    /**
     * This is the "main" validator method. It will be called for every user input.
     * Inner methods will loop until a valid input is entered for every question.
     * An answer is required for every question except the ones that have "optional" in them. They can be empty.
     * -
     * If a command from SPECIAL_CMDS is being entered - it will return it.
     * Example - "reset!" can be used to restart each question, if wrong data has been entered.
     *
     * @param question - different questions - will run different parts of the app.
     * @return - validated answer depending on the question.
     */
    public static String getValidUserInput(String question) {
        // Modify the question "Enter available stock" to include the unit type and values on shelf
        if (question.contains("stock")) {
            String unitType = ANSWERS[4];
            int unitValue = UNIT_OPTIONS.get(unitType);
            System.out.println("Enter " + question + " ([" + unitType + "] - " + unitValue + " items per shelf): ");
        } else {
            System.out.println("Enter " + question + ": ");
        }

        String ans = scanner.nextLine().strip();

        for (String specialCmd : SPECIAL_CMDS) {
            if (ans.equalsIgnoreCase(specialCmd)) {
                return specialCmd;
            }
        }

        // Make sure that the unit question returns a valid answer
        if (question.contains("unit")) {
            return getUnit(ans.toLowerCase());
        }

        else if (question.equalsIgnoreCase("expiry date")) {
            return getExpiryDate(convertDateFromUKtoEUType(ans));
        }

        else if (question.equalsIgnoreCase("Entry date")) {
            return getEntryDate(convertDateFromUKtoEUType(ans));
        }

        // "from - to" date
        else if (question.contains("date")) {
            return getDate(convertDateFromUKtoEUType(ans));
        }

        else if (question.contains("stock")) {
            return getStock(ans);
        }

        // Allow empty/blank input if the question is optional. Require an input if not.
        else if (!question.contains("optional")) {
            while (ans.isEmpty()) {
                printError("Empty answer. Please enter " + question + ": ");
                ans = scanner.nextLine().strip();
                // no need for reset! check as it will be returned anyway if entered.
            }
        }

        return ans;
    }

    public static boolean isAtLeastOneItemInLocation(String location) throws IOException {
        String[][] DB = getAllDataFromDB();
        for (String[] row : DB) {
            if (row[6].equalsIgnoreCase(location)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Will return every UNIQUE location for an itemName with specified expiryDate including those that are full.
     *
     * @return something like {A3 / 5 / 10, A3 / 7 / 10}
     */
    public static List<String> getAllUniqueLocationsForItem(String itemName, String expiryDate) throws IOException {
        List<String> positions = new ArrayList<>();
        String[] DB = getDbDataToStringArray();

        for (String row : DB) {
            try {
                String currentItemName = row.split(SEPARATOR)[0];
                String currentExpiryDate = row.split(SEPARATOR)[1];
                if ((itemName.equalsIgnoreCase(currentItemName)) && expiryDate.equals(currentExpiryDate)) {

                    String currentPosition = row.split(SEPARATOR)[6];

                    // Add position only once - there might be multiple rows with same position.
                    if (!positions.contains(currentPosition)) {
                        positions.add(currentPosition);
                    }
                }
            } catch (Exception e) {
                printError("Empty row in " + DB_FILE_NAME + " file.");
            }
        }

        return positions;
    }

    public static String[][] getAllDataFromDB() throws IOException {
        int numRowsWithDataInDB = getLenOfFile(false);
        String[][] result = new String[numRowsWithDataInDB][DESCRIPTION.length];
        String[] DB = getDbDataToStringArray();

        for (int i = 0; i < numRowsWithDataInDB; i++) {
            String[] rowArray = DB[i].split(SEPARATOR);
            result[i] = rowArray;
        }

        return result;
    }

    public static void printDataForTimePeriod() throws IOException, ParseException {
        String[] fromToDates = getUserInputFromToDates();
        String fromDate = fromToDates[0];
        String toDate = fromToDates[1];

        // Populate a list with the rows in the range
        List<List<String>> dataBetweenTwoDates = getDataForTimePeriod(fromDate, toDate);

        if (dataBetweenTwoDates.size() == 0) {
            System.out.println(getColoredMsg("NO STOCK FOUND IN THE PERIOD " + fromDate + " TO " + toDate, ANSI_GREEN));
        } else {
            // Add the results to an ArrayList, so it can be printed
            ArrayList<ArrayList<String>> results = new ArrayList<>();
            for (List<String> row : dataBetweenTwoDates) {
                ArrayList<String> result = new ArrayList<>();
                result.addAll(row);
                results.add(result);
            }
            System.out.print(getColoredMsg("STOCK ADDED IN THE PERIOD " + fromDate + " TO " + toDate + ":", ANSI_GREEN));
            printDBInFrameWithDescription(results, ANSI_GREEN);
        }
    }

    public static List<List<String>> getDataForTimePeriod(String fromDate, String toDate) throws IOException, ParseException {
        List<List<String>> results = new ArrayList<>();

        String[] DB = getDbDataToStringArray();

        for (String row : DB) {
            String[] rowArray = row.split(SEPARATOR);
            String currentEntryDate = rowArray[2];
            if (isDateBetweenTwoDates(currentEntryDate, fromDate, toDate)) {
                results.add(List.of(rowArray));
            }
        }

        return results;
    }

    public static int getLenOfFile(boolean countEmptyRows) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(DB_FILE_NAME));
        int lines = 0;
        String row = reader.readLine();
        while (row != null) {
            if (countEmptyRows) {
                lines++;
            } else if (!row.equals("")) {
                lines++;
            }
            row = reader.readLine();
        }
        reader.close();
        return lines;
    }

    public static void createDBfileIfMissing() throws IOException {
        File f = new File(DB_FILE_NAME);
        if (!f.exists()) {
            printWarning("DB file \"" + DB_FILE_NAME + "\" not found and will be created.");
            f.createNewFile();
        }
    }

    public static String[] getDbDataToStringArray() throws IOException {
        // Create an array as big as the number of rows in the file (without empty rows)
        int numRowsInFile = getLenOfFile(false);
        String[] rowsData = new String[numRowsInFile];

        File file = new File(DB_FILE_NAME);
        Scanner sc = new Scanner(file);

        int numRowsInFileIncludingEmpty = getLenOfFile(true);
        int rowNumber = 0;
        for (int i = 0; i < numRowsInFileIncludingEmpty; i++) {
            String row = sc.nextLine();
            if (!row.equals("")) {
                rowsData[rowNumber] = row;
                rowNumber++;
            }
        }

        return rowsData;
    }

    public static ArrayList<ArrayList<String>> getDbDataToArrayList() throws IOException {
        ArrayList<ArrayList<String>> rows = new ArrayList<>();
        File file = new File(DB_FILE_NAME);
        Scanner sc = new Scanner(file);

        int numRowsInFileIncludingEmpty = getLenOfFile(true);
        for (int i = 0; i < numRowsInFileIncludingEmpty; i++) {
            ArrayList<String> row = new ArrayList<>();
            String rowCSV = sc.nextLine();
            if (!rowCSV.equals("")) {
                row.add(rowCSV.split(SEPARATOR)[0]);
                for (int j = 1; j < rowCSV.split(SEPARATOR).length; j++) {
                    String val = rowCSV.split(SEPARATOR)[j];
                    row.add(val);
                }
            }
            rows.add(row);
        }

        return rows;
    }

    public static void writeDataToDB(List<String> row) {
        try {
            FileWriter writer = new FileWriter(DB_FILE_NAME, true);

            for (String element : row) {
                writer.write(element + SEPARATOR);
            }
            writer.write("\n");

            writer.close();
        } catch (IOException e) {
            printError("An error occurred while writing " + row);
            e.printStackTrace();
        }
    }


    // =============

    /**
     * Loops until a valid Unit option is entered.
     *
     * @param userInput - initial user input
     * @return - valid UNIT option or a special command.
     */
    public static String getUnit(String userInput) {
        String availableOptions = String.join(", ", UNIT_OPTIONS.keySet());

        while (true) {

            if (isUnitValid(userInput)) {
                return userInput;
            }

            printError("Please Enter a valid option for Unit [" + availableOptions + "]: ");
            userInput = scanner.nextLine().strip().toLowerCase();

            for (String specialCmd : SPECIAL_CMDS) {
                if (userInput.equalsIgnoreCase(specialCmd)) {
                    return specialCmd;
                }
            }
        }
    }

    public static String getStock(String userInput) {

        while (true) {

            if (isNumber(userInput)) {
                return userInput;
            }

            printError("Please Enter a valid option for Stock (needs to be an Integer or a number with a single decimal place digit): ");

            // removeDecimalIfEndsOnZero used, so it allows .2 to be entered too. Won't change anything else.
            userInput = removeDecimalIfEndsOnZero(scanner.nextLine().strip());

            for (String specialCmd : SPECIAL_CMDS) {
                if (userInput.equalsIgnoreCase(specialCmd)) {
                    return specialCmd;
                }
            }
        }
    }

    /**
     * Will keep asking for a date until the input is "n/a" or a valid date in the format dd.mm.yyyy or d.m.yyyy.
     * The date can be separated by "." or "/". Spaces are ignored.
     * Example input -> output:
     * 1/2/2020  -> 01.02.2020
     * 01.2.2020 -> 01.02.2020
     * N/A       -> n/a (will always return lower case n/a)
     * today/tomorrow -> formatted date.
     *
     * @param userInput - initial user input
     * @return String date with leading zeros for the day and month (if single digits) and separated by "." no matter
     * how it was entered by the user. Can return n/a or a special command too.
     */
    public static String getExpiryDate(String userInput) {

        while (true) {
            String errorMessage = "Please Enter a date in the format: \"dd.mm.yyyy\" / \"dd/mm/yyyy\" / today / tomorrow or \"n/a\" if the product doesn't expire.";

            if (userInput.isEmpty()) {
                errorMessage = "Empty answer. " + errorMessage;
            } else {
                if (userInput.equalsIgnoreCase("today")) {
                    return getToday();  // no checks needed.
                }

                if (userInput.equalsIgnoreCase("tomorrow")) {
                    return getTomorrow();  // no checks needed.
                }

                // Expiry date can be n/a
                if (userInput.equalsIgnoreCase("n/a")) {
                    return "n/a";
                }

                // Check if is date and if the product is not expired - return the date.
                if (isDateValid(userInput)) {
                    if (!isDateExpired(userInput)) {
                        return addLeadingZeroToDayMonth(userInput);
                    }
                }
            }
            printError(errorMessage);
            userInput = convertDateFromUKtoEUType(scanner.nextLine().strip());

            for (String specialCmd : SPECIAL_CMDS) {
                if (userInput.equalsIgnoreCase(specialCmd)) {
                    return specialCmd;
                }
            }
        }
    }

    /**
     * Loops until the date entered is not older than the expiry date(item already expired). A valid date is a date in
     * the format "dd.mm.yyyy" or "d.m.yyyy". The date can be separated by "." or "/". Spaces are ignored.
     * "today" can be used to add today's date.
     *
     * @param userInput - initial user input
     * @return String date with leading zeros for the day and month (if single digits) and separated by "." no matter
     * how it was entered by the user.
     */
    public static String getEntryDate(String userInput) {
        String currentExpiryDate = ANSWERS[1];
        while (true) {
            String errorMessage = "Please Enter a date in the format: \"dd.mm.yyyy\" / \"dd/mm/yyyy\" / today";

            if (userInput.isEmpty()) {
                errorMessage = "Empty answer. " + errorMessage;
            } else {
                if (userInput.equalsIgnoreCase("today")) {
                    return getToday();  // no checks needed.
                }

                if (isDateValid(userInput)) {
                    if (getNumberOfDaysToDate(userInput) <= getNumberOfDaysToDate(currentExpiryDate)) {
                        return addLeadingZeroToDayMonth(userInput);
                    }
                    // Print an Error msg if Entry date is later than the Expiry date (Item already expired when entering the warehouse)
                    printError("Something is wrong. Entry date(" + userInput + ") is after expiry date(" + currentExpiryDate + ")");
                }
            }

            printError(errorMessage);
            userInput = convertDateFromUKtoEUType(scanner.nextLine().strip());

            for (String specialCmd : SPECIAL_CMDS) {
                if (userInput.equalsIgnoreCase(specialCmd)) {
                    return specialCmd;
                }
            }
        }
    }

    public static String getDate(String userInput) {

        while (true) {
            String errorMessage = "Please Enter a date in the format: \"dd.mm.yyyy\" / \"dd/mm/yyyy\" / today";

            if (userInput.isEmpty()) {
                errorMessage = "Empty answer. " + errorMessage;
            } else {
                if (userInput.equalsIgnoreCase("today")) {
                    return getToday();  // no checks needed.
                }

                if (isDateValid(userInput)) {
                    return addLeadingZeroToDayMonth(userInput);
                }
            }

            printError(errorMessage);
            userInput = convertDateFromUKtoEUType(scanner.nextLine().strip());

            for (String specialCmd : SPECIAL_CMDS) {
                if (userInput.equalsIgnoreCase(specialCmd)) {
                    return specialCmd;
                }
            }
        }
    }

    public static String[] getUserInputFromToDates() throws IOException, ParseException {
        String[] results = new String[2];
        String[] questions = {"From date", "To date"};
        for (int i = 0; i < questions.length; i++) {
            String result = getValidUserInput(questions[i]);
            // SPECIAL_CMDS - take action
            if (result.equalsIgnoreCase("reset!")) {
                System.out.println();
                return getUserInputFromToDates();
            } else if (result.equalsIgnoreCase("stop!")) {
                runApp();
            } else {
                results[i] = result;
            }
        }

        // Flip the values if needed.
        if (getNumberOfDaysToDate(results[0]) > getNumberOfDaysToDate(results[1])) {
            printWarning("\"To date\" is before \"From date\". Flipping the numbers.");
            String temp = results[0];
            results[0] = results[1];
            results[1] = temp;
        }

        return results;
    }

    // =============

    public static void printWarning(String msg) {
        System.out.println(getColoredMsg("Warning: " + msg, ANSI_YELLOW));
    }

    public static void printError(String msg) {
        System.out.println(getColoredMsg("Error: " + msg, ANSI_RED));
    }

    public static String getBoldYellowMsg(String msg) {
        return getBoldMsg(getColoredMsg(msg, ANSI_YELLOW));
    }

    public static String getColoredMsg(String msg, String ANSI_color) {
        return ANSI_color + msg + ANSI_RESET;
    }

    public static String getBoldMsg(String msg) {
        return ANSI_BOLD + msg + ANSI_RESET;
    }

    public static int getStringLengthWithoutANSI(String str) {
        return str.replaceAll("\u001B\\[[;\\d]*m", "").length();
    }

    public static int getLengthOfTheLongestRowInList(List<String> list) {
        int longest = 0;
        for (String row : list) {
            int currentLength = getStringLengthWithoutANSI(row);
            if (currentLength > longest) {
                longest = currentLength;
            }
        }
        return longest;
    }

    public static String getToday() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

    public static String getTomorrow() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return tomorrow.format(formatter);
    }

    public static boolean isSeparatorInAns(String ans) {
        for (int i = 0; i < ans.length(); i++) {
            if (ans.split("")[i].equals(SEPARATOR)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDateBetweenTwoDates(String dateToCheck, String startDate, String endDate) throws ParseException {
        // All dates need to be in the format dd.mm.yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = sdf.parse(dateToCheck);
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        return date.compareTo(start) >= 0 && date.compareTo(end) <= 0;
    }

    /**
     * If the date is split by "/" - it will convert it to ".". If split by "." - won't change it.
     * -
     * Example:
     * 01/02/2019 -> 01.02.2019
     * 01.02.2019 -> 01.02.2019
     *
     * @param date - date split by "." or "/"
     * @return - date split by "." (no additional formatting) -> (01.02.2020, 1.2.2020, etc..)
     */
    public static String convertDateFromUKtoEUType(String date) {
        // Allow date to be entered as dd/mm/yyyy as well as dd.mm.yyyy. Will not get triggered on n/a.
        if (date.split("/").length == 3) {
            return date.replace("/", ".");
        }
        return date;
    }

    /**
     * Checks if date is expired or expires today.
     * Prints an error or a warning message if (item) date is expired or expires today.
     *
     * @param date - date in format dd.mm.yyyy or d.m.yyyy
     * @return - true if date is expired, false if not expired or expires today.
     */
    public static boolean isDateExpired(String date) {
        if (getNumberOfDaysFromTodayToDate(date) < 0) {
            printError("The item has already expired!");
            return true;
        } else if (getNumberOfDaysFromTodayToDate(date) == 0) {
            printWarning("The item expires today!");
        }
        return false;
    }

    /**
     * Adds a zero ("0") to the days and months (if single digit)
     * -
     * Example:
     * 1.2.2019   -> 01.02.2019
     * 01.2.2019  -> 01.02.2019
     * 01.02.2019 -> 01.02.2019
     * " 01 . 2 . 2019 " -> 01.02.2019
     *
     * @param date - date split by "." ("dd.mm.yyyy" or "d.m.yyyy")
     * @return dd.mm.yyyy (2 digits for date and month and 4 digits for year)
     */
    public static String addLeadingZeroToDayMonth(String date) {
        date = date.replaceAll("\\s", "");
        // date needs to be in the format dd.mm.yyyy - already checked.
        String d = date.split("\\.")[0];
        String m = date.split("\\.")[1];
        String y = date.split("\\.")[2];

        String dateFormatted = "";
        dateFormatted += String.format("%02d", Integer.parseInt(d)) + ".";
        dateFormatted += String.format("%02d", Integer.parseInt(m)) + ".";
        dateFormatted += y;

        return dateFormatted;
    }

    public static boolean isUnitValid(String unit) {
        for (String unitOption : UNIT_OPTIONS.keySet()) {
            if (unit.strip().equalsIgnoreCase(unitOption)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumber(String strToCheck) {
        // Will match integers + optional single decimal place digit
        return strToCheck.matches("\\d+(\\.\\d)?");
    }

}
