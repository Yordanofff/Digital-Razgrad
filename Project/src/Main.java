import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// todo - Expiry date: n/a | Entry date: 05.05.2021 - expirity date can be n/a or null
// todo checker - [][][] or letter[][][]
// todo - color output
// todo - print in the middle
// todo - if no expiry date - empty string as answer or n/a?
// todo - add error printing in data validation --> what's wrong as a parameter
// todo - don't allow separator to be used in the data.
// todo - postypvane v sklada - opciq - today

// todo - items per shelf - ako produkta ve4e go ima - da izpishe kolko se sybirat na edin raft.
// produkta moje da e s promeneni razmeri i da se sybirat po-malko ili pove4e ot nego.

// todo - print "Product was added successfully! after getAllValidUserInput()"

// todo - forbiden elements - " and separator/delimiter

// todo - write tests

// todo - if entry date earlier than expiry date - warning - stock is expired

// todo - allow date to be split by . and /
// todo Enter available stock: --> Enter available stock(unit): -> Enter available stock(kg):

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String SEPARATOR = ";";
    static String SEPARATOR_WHEN_PRINTING = " | ";
    static String DB_FILE_NAME = "app_data.csv";

//    static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    static char[] LETTER_SECTION = "ab".toCharArray();
    static int NUM_SECTION = 2;
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
    static String[] DESCRIPTION_NO_NAME = new String[]{"Expiry date", "Entry date", "Manufacturer", "Unit", "Stock", "Position", "Available items at shelf", "Comment"};

    private static List<String> TEMP_USED_LOCATIONS_NOT_IN_DB = new ArrayList<>();

    public static void main(String[] args) throws IOException, ParseException {
        runApp();
    }

    public static void runApp() throws IOException, ParseException {
        createDBfileIfMissing();
        while (true) {
            if (!printMenuGetAnsAndTakeAction()) {
                break;
            }
            System.out.println();
        }
    }

    public static boolean printMenuGetAnsAndTakeAction() throws IOException, ParseException {
        int option = printMenuOptions();
        return takeMenuAction(option);
    }

    public static boolean takeMenuAction(int selectedOption) throws IOException, ParseException {
        if (selectedOption == 1) {
            printAllDataFromDB();
        } else if (selectedOption == 2) {
            getAllUserDataAndWriteToDB();
        } else if (selectedOption == 3) {
            printDataForTimePeriod();
        } else if (selectedOption == 4) {
            return false;
        }
        return true;
    }

    public static int printMenuOptions() {
        String[] menuOptions = new String[]{"List all items", "Add new delivery", "List deliveries for time period", "Exit"};

        System.out.println("Please choose what to do:");
        for (int i = 1; i <= menuOptions.length; i++) {
            System.out.println(i + " - " + menuOptions[i - 1]);
        }

        return getMenuAnswer(menuOptions.length);

    }

    public static int getMenuAnswer(int numOptions) {
        int ans = scanner.nextInt();
        scanner.nextLine();
        while (ans < 1 || ans > numOptions) {
            System.out.println("Wrong answer. Please choose a number between 1 and " + numOptions);
            ans = scanner.nextInt();
            scanner.nextLine();
        }
        return ans;
    }

    public static String[] insertElementInArray(String[] array, String elementToAdd, int position) {
        int i;
        int n = array.length;

        String[] newArray = new String[n + 1];

        for (i = 0; i < n + 1; i++) {
            if (i < position - 1) {
                newArray[i] = array[i];
            } else if (i == position - 1) {
                newArray[i] = elementToAdd;
            } else {
                newArray[i] = array[i - 1];
            }
        }
        return newArray;
    }

    public static boolean isNumber(String num) {
        try {
            Integer.parseInt(num.strip());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isPositionValid(String placement) {
         /*
         Example of valid positions:
         A3 / 4 / 10
         A3/4/10
         A3/4/10
         B40/15 / 20
         */

        String[] parts = placement.split("/");

        // Check if it's made out of 3 parts
        if (parts.length != 3) {
            return false;
        }

        // Check if the last 2 parts are numbers
        if (!(isNumber(parts[1]) && isNumber(parts[2]))) {
            return false;
        }

        // Check if the first part is made out of a single letter + a number
        try {
            // split the first part in String/Integer
            // \D matches all non-digit characters, while \d matches all digit characters
            String[] firstPart = parts[0].strip().split("(?<=\\D)(?=\\d)");

            String letterPart = firstPart[0].strip();
            if (letterPart.length() == 1) {
                // no need to check if is letter
                if (isNumber(firstPart[1])) {
                    return true;
                }
            }
        } catch (Exception e) {
            // something's wrong
            return false;
        }
        return false;
    }

    public static List<String> getAllPossibleLocations() {

        List<String> result = new ArrayList<>();

        // Rows/shelves/numbers start at 1.
        for (char c : LETTER_SECTION) {
            for (int i = 1; i < NUM_SECTION; i++) {
                for (int j = 1; j < NUM_SHELVES; j++) {
                    for (int k = 1; k < NUM_NUMBERS; k++) {
                        String itemPosition = String.valueOf(c) + i + " / " + j + " / " + k;
                        result.add(itemPosition);
                    }
                }
            }
        }

        return result;
    }

    // todo ?
    public static List<String> getAllLocationsThatHaveAtLeastOneItem() throws IOException {
        List<String> result = new ArrayList<>();
        String[] DB = getDbDataToStringArray();

        for (String row : DB) {
            String currentElement = row.split(SEPARATOR)[6];
            if (!result.contains(currentElement)) {
                result.add(currentElement);
            }
        }

        // don't use the same location that has been used, but not written to DB.
        if (!TEMP_USED_LOCATIONS_NOT_IN_DB.isEmpty()) {
            for (String tempLocation : TEMP_USED_LOCATIONS_NOT_IN_DB) {
                if (!result.contains(tempLocation)) {
                    result.add(tempLocation);
                }
            }
        }

        return result;
    }

    public static String getFirstLocationThatDoeNotHaveAnyItems() throws IOException {
        List<String> allPositions = getAllPossibleLocations();
        List<String> usedPositions = getAllLocationsThatHaveAtLeastOneItem();

        for (String usedPosition : usedPositions) {
            // No need to check if usedPosition in allPositions
            allPositions.remove(usedPosition);
        }

        // Sort the list to get the first position
        java.util.Collections.sort(allPositions);

//        if (allPositions.size() == 0){
//            throw new Exception("No more space in the warehouse.");
//        }

        return allPositions.get(0);
    }

    public static int getFreeSpaceAtLocationIfAtLeastOneItem(String location) throws IOException {
        /*
        Need to check all rows that have the same Position and calculate total number of Items added to that location
        By design - only same item names + expiry date will be added to 1 location, so no need to check if they match.
        returns number of items that can be added to the location.
        returns 0 - if location not in DB.
         */

        String[][] DB = getAllDataFromDB();
        int numItems = 0;
        int maxNumItems = 0;
        for (String[] row : DB) {
            if (row[6].equalsIgnoreCase(location)) {
                int currentRowStock = Integer.parseInt(row[5]);
                numItems += currentRowStock;

                maxNumItems = Integer.parseInt(row[7]);
            }
        }
        return maxNumItems - numItems;
    }

    public static String getPositionToPlaceItem(String itemName, String expiryDate) throws IOException {
        // If item is already in the DB - find location where that item is.
        // Check if there are spaces left on that shelf - calculateRemainingSpaceAtLocation
        // fill them up until none left - then get first free location - keep adding there.

        List<String> locations = getAllUniqueLocationsForItem(itemName, expiryDate);

        // If the item is in the DB and there is free space for more items - return location
        for (String location : locations) {
            if (getFreeSpaceAtLocationIfAtLeastOneItem(location) > 0) {

                // Check if the location has been filled in, so that on the second iteration in getAllData() while loop
                // this will not return the same location
                for (String loc : TEMP_USED_LOCATIONS_NOT_IN_DB) {
                    if (loc.equals(location)) {
                        return getFirstLocationThatDoeNotHaveAnyItems();
                    }
                }

                return location;
            }
        }

        // If item not in DB or in DB but all locations are full - get the next free location.
        // todo - check if free location in warehouse
        return getFirstLocationThatDoeNotHaveAnyItems();
    }

    public static boolean isDateValid(String date) {
        /*
        Valid date needs to be in format "dd.mm.yyyy".
        Example of valid dates:
        01.01.2020
        30.10.2022
         */

        String[] parts = date.split("\\.");

        // Check if the formatting is correct - 2 dots -> xx.yy.zzzz
        if (parts.length != 3) {
            return false;
        }

        // Check if all parts can be converted to integers and all are bigger than 1. Day, month and year can't be 0.
        for (int i = 0; i < 3; i++) {
            try {
                int num = Integer.parseInt(parts[i]);
                if (num < 1) {
                    return false;
                }
            } catch (Exception e) {
                System.out.println(parts[i] + " cannot be converted to integer");
                return false;
            }
        }

        int numDay = Integer.parseInt(parts[0]);
        int numMonth = Integer.parseInt(parts[1]);
        int numYear = Integer.parseInt(parts[2]);

        // Month should be 1-12
        if (numMonth > 12) {
            return false;
        }

        // Year should be 4 digits long - ex. 2021
        if (parts[2].length() != 4) {
            return false;
        }

        int daysInCurrentMonth = getDaysInMonth(numYear, numMonth);

        // Check if the month has that date.
        if (numDay > daysInCurrentMonth) {
            return false;
        }

        return true;
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

    public static boolean isUnitValid(String unit) {
        // Check if the string unit is in the UNIT_OPTIONS

        for (String unitOption : UNIT_OPTIONS.keySet()) {
            if (unit.strip().equalsIgnoreCase(unitOption)) {
                return true;
            }
        }

        return false;
    }

    public static String getValidUserInput(String question) {
        /*
        This method will be called for every user input.
        Every answer needs to have some input, except the ones that have "optional" in the question.
        keyword "reset!" can be used to restart the questions if wrong data has been added
        returns a valid answer
         */

        System.out.println("Enter " + question + ": ");
        String ans = scanner.nextLine();
        if (ans.strip().equalsIgnoreCase("reset!")) {
            return ans.strip();
        }

        // Make sure that the unit question returns a valid answer
        if (question.contains("unit")) {
            // Convert the input to lowercase
            ans = ans.toLowerCase();
            boolean isValid = isUnitValid(ans);
            while (!isValid) {
                String availableOptions = String.join(", ", UNIT_OPTIONS.keySet());
                System.out.println("Error! Please Enter a valid option for Unit: " + availableOptions + ".");
                ans = scanner.nextLine().toLowerCase();
                isValid = isUnitValid(ans);
            }
        }

        // expiry date - can be a date or "n/a" for products that don't expire. todo - allow blank input as N/A ?
        else if (question.equalsIgnoreCase("expiry date")) {
            boolean isValid = isDateValid(ans);
            while (!(isValid || ans.equalsIgnoreCase("n/a"))) {
                System.out.println("Error! Please Enter a date in the format: \"dd.mm.yyyy\" or \"n/a\" if the product doesn't expire.");
                ans = scanner.nextLine();
                isValid = isDateValid(ans);
            }
        }

        // entry date - needs to be a date. Can't be "n/a". Will also work for other "date"(s).
        else if (question.contains("date")) {
            boolean isValid = isDateValid(ans);
            while (!isValid) {
                System.out.println("Error! Please Enter a date in the format: \"dd.mm.yyyy\"");
                ans = scanner.nextLine();
                isValid = isDateValid(ans);
            }
        }

        // Allow empty/blank input if the question is optional. Require an input if not.
        else if (!question.contains("optional")) {
            while (ans.isEmpty()) {
                System.out.println("Empty answer. Please enter " + question + ": ");
                ans = scanner.nextLine();
            }
        }

        return ans.strip();
    }

    public static String[] getAllValidUserInput() {
        /*
        Enter product name:
        << Battery CR32

        Enter expiry date:
        <<24.11.2025

        Enter entry date:
        << 02.06.2021

        Enter manufacturer:
        << Varta

        Enter unit:
        << Item

        Enter available stock:
        << 900

        Enter comment (optional):
        <<

         "product name", "expiry date", "entry date", "manufacturer", "unit", "available stock", "comment (optional)"
         returns: {"Battery CR32", "24.11.2025", "02.06.2021", "Varta", "Item", "900", ""}
         */

        System.out.println("Enter the data for the items that you want to add.\nUse \"reset!\" to reset the data input.");

        String[] answers = new String[USER_QUESTIONS.length];
        for (int i = 0; i < USER_QUESTIONS.length; i++) {
            String ans = getValidUserInput(USER_QUESTIONS[i]);
            if (ans.equals("reset!")) {
                getAllValidUserInput();
            }
            answers[i] = ans;
        }

        return answers;
    }


    public static List<List<String>> getAllData(String[] allValidUserInput) throws IOException {
        /*
        The data that the user will add will be something like:
        "product name", "expiry date", "entry date", "manufacturer", "unit", "available stock", "comment (optional)"
        {"Battery CR32", "24.11.2025", "02.06.2021", "Varta", "Item", "900", ""}
        We need to add two more things:
            - position where the items will be located in the warehouse (First possible place)
            - number of items that can fit on a single shelf.

        If we add more stock than what can fit on a shelf, then we need to add another row in the
            DB with a new position where the rest of the items will be located.

        If item with the same "name" and "expiry date" is already in the DB, and there is free space on
            the shelf where that item is, we need to add the items in that same position.
            If they don't fit the remaining items will be added to another position.

        The String Array that this method returns will be written in the DB.

        "Position" and "Max number of items on shelf" will be placed between "Stock" and "Comment", so that they can
        later be printed easily in the required format:
        //Light bulb - LED 75W | Expiry date: n/a | Entry date: 05.05.2021 | Manufacturer: Philips | Unit: Item | Stock: 104 |
        Position: A3 / 4 / 10 | Available items at shelf: 500 | Comment:

        "product name", "expiry date", "entry date", "manufacturer", "unit", "available stock",
        "position in WH", "Max number of items on shelf", "comment (optional)"

        {"Battery CR32", "24.11.2025", "02.06.2021", "Varta", "Item", "900", "A3 / 4 / 10", "10000", ""}
         */


        // Get the max number of items per shelf depending on the type of item
        int numberOfItemsThatCanFitOnShelf = UNIT_OPTIONS.get(allValidUserInput[4]);
        String numberOfItemsThatCanFitOnShelfString = String.valueOf(numberOfItemsThatCanFitOnShelf);

        List<List<String>> newRowsToAdd = new ArrayList<>();

        String itemName = allValidUserInput[0];
        String expiryDate = allValidUserInput[1];
        int numItemsRemainingToAdd = Integer.parseInt(allValidUserInput[5]);  // Stock from user input
        while (numItemsRemainingToAdd > 0) {

            // Because we want to return a List, items won't be recorded in the DB on every iteration.
            // that's why we use a temp variable to store the locations that are already used.
            String positionToPlaceItem = getPositionToPlaceItem(itemName, expiryDate);
            TEMP_USED_LOCATIONS_NOT_IN_DB.add(positionToPlaceItem);

            // If item is in DB - get free space in location
            int numItemsThatWillFitInLocation = numberOfItemsThatCanFitOnShelf;
            if (isAtLeastOneItemInLocation(positionToPlaceItem)) {  // todo - move to another func
                numItemsThatWillFitInLocation = getFreeSpaceAtLocationIfAtLeastOneItem(positionToPlaceItem);
            }

            // There might be 100 free spaces but only 10 items need to be added
            int numItemsThatWillBeAddedToLocation = numItemsThatWillFitInLocation;
            if (numItemsRemainingToAdd < numItemsThatWillFitInLocation) {
                numItemsThatWillBeAddedToLocation = numItemsRemainingToAdd;
            }

            // List initialization is inside the loop, so that it stores the data in different memory
            List<String> allValidUserInputList = new ArrayList<>();
            for (String element : allValidUserInput) {
                allValidUserInputList.add(element);
            }
            allValidUserInputList.set(5, String.valueOf(numItemsThatWillBeAddedToLocation));
            allValidUserInputList.add(6, positionToPlaceItem);
            allValidUserInputList.add(7, numberOfItemsThatCanFitOnShelfString);

            newRowsToAdd.add(allValidUserInputList);

            numItemsRemainingToAdd -= numItemsThatWillBeAddedToLocation;
        }

        // This can be added to a Finally block in writeDataToDB() but will work as good here too.
        // Even if it fails to write the data - it will start over and the list needs to be empty.
        TEMP_USED_LOCATIONS_NOT_IN_DB.clear();

        return newRowsToAdd;
    }


    public static void getAllUserDataAndWriteToDB() throws IOException {
        String[] allValidUserInput = getAllValidUserInput();
        List<List<String>> allData = getAllData(allValidUserInput);

        for (List<String> row : allData) {
            writeDataToDB(row);
        }
        System.out.println("Product was added successfully!");
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


    public static List<String> getAllUniqueLocationsForItem(String itemName, String expiryDate) throws IOException {
        /*
        Will return every UNIQUE location if the itemName and expiryDate are the same.

        Will return all positions, including those that are full.

        Example return = {A3 / 5 / 10, A3 / 7 / 10}
        */

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
                System.out.println("Error! Empty row in " + DB_FILE_NAME + " file.");
            }
        }

        return positions;
    }


    public static void printAllDataFromDB() throws IOException {
        String[][] DB = getAllDataFromDB();
        printResults(DB);
    }

    public static String[][] getAllDataFromDB() throws IOException {
        int arrayLen = getLenOfFile(false);
        String[][] result = new String[arrayLen][DESCRIPTION_NO_NAME.length + 1];
        String[] DB = getDbDataToStringArray();

        for (int i = 0; i < arrayLen; i++) {
            String[] rowArray = DB[i].split(SEPARATOR);
            result[i] = rowArray;
        }

        return result;
    }

    public static void printResults(String[][] rows) {
        // Light bulb - LED 75W | Expiry date: n/a | Entry date: 05.05.2021 | Manufacturer: Philips | Unit: Item
        // | Stock: 104 | Position: A3 / 4 / 10 | Available items at shelf: 500 | Comment:

        for (int i = 0; i < rows.length; i++) {
            int printingDescriptionLength = rows[i].length;

            // print the name of the item - and a separator on the back - "Light bulb - LED 75W | "
            System.out.print(rows[i][0] + SEPARATOR_WHEN_PRINTING);

            // Print every other description: value |
            for (int j = 1; j < printingDescriptionLength; j++) {
                String currentVal = rows[i][j];
                if (j == printingDescriptionLength - 1) {

                    // When converting from List to Array - and there is no comment - the value becomes null.
                    // Don't print null in the Comment section if empty.
                    if (currentVal == null) {
                        currentVal = "";
                    }

                    // Don't print separator at the end.
                    System.out.print(DESCRIPTION_NO_NAME[j - 1] + ": " + currentVal);
                    continue;
                }
                System.out.print(DESCRIPTION_NO_NAME[j - 1] + ": " + currentVal + SEPARATOR_WHEN_PRINTING);

            }

            System.out.println();
        }
    }

    public static void printDataForTimePeriod() throws IOException, ParseException {
        String[] fromToDates = getUserInputFromToDates();
        String fromDate = fromToDates[0];
        String toDate = fromToDates[1];

        // Populate a list with the rows in the range
        List<List<String>> dataBetweenTwoDates = getDataForTimePeriod(fromDate, toDate);

        // Convert the list to an Array
        String[][] dataBetweenTwoDatesArray = convertListToArray(dataBetweenTwoDates);

        // Print the DESCRIPTION and the VALUES from the Array
        printResults(dataBetweenTwoDatesArray);
    }

    public static String[][] convertListToArray(List<List<String>> listOfListsToConvert) {
        // Convert the list to an Array, so that printResults can print both:
        // 1 - List all items;
        // 3 - List deliveries for time period

        String[][] arr = new String[listOfListsToConvert.size()][DESCRIPTION_NO_NAME.length + 1];

        for (int i = 0; i < listOfListsToConvert.size(); i++) {
            for (int j = 0; j < listOfListsToConvert.get(i).size(); j++) {
                String currentElement = listOfListsToConvert.get(i).get(j);
                arr[i][j] = currentElement;
            }
        }

        return arr;
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

    public static String[] getUserInputFromToDates() {
        String[] result = new String[2];

        String fromDate = getValidUserInput("From date");
        String toDate = getValidUserInput("To date");

        result[0] = fromDate;
        result[1] = toDate;

        return result;
    }

    public static boolean isDateBetweenTwoDates(String dateToCheck, String startDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = sdf.parse(dateToCheck);
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        return date.compareTo(start) >= 0 && date.compareTo(end) <= 0;
    }

    public static int getLenOfFile(boolean countEmptyRows) throws IOException {
        // Count all rows in a file with/without the empty rows.

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
            System.out.println("DB file \"" + DB_FILE_NAME + "\" not found and will be created.");
            f.createNewFile();
        }
    }

    public static String[] getDbDataToStringArray() throws IOException {
        // Create an array as big as many rows are in the file (without empty rows)
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

    public static void writeDataToDB(List<String> row) {
        try {
            FileWriter writer = new FileWriter(DB_FILE_NAME, true);

            for (String element : row) {
                writer.write(element + SEPARATOR);
            }
            writer.write("\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing " + row);
            e.printStackTrace();
        }
    }

}


