import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


//Склад
//Да се напише компютърна програма, реализираща информационна система, обслужваща склад.
// Програмата да поддържа текстов диалогов режим, позволяващ удобен интерактивен избор на следните операции:
//
//списък на наличните продукти в склада.
//
// За всеки продукт се съхранява и извежда следната информация:
//име (описание - символен низ с произволна дължина)
//срок на годност
//дата на постъпване в склада
//име на производител
//мерна единица (килограми, литри)
//налично количество
//местоположение (секция/рафт/номер) (номерирайте склада си както прецените, че ще ви е удобно,
// имайте предвид, че в началото той е празен и различно количество стока е нормално да заема различно по обем място)
//допустим брой от продукта на рафт
//коментар (свободен текст)
//
//записване на нова доставка
//ако нов продукт е с различен срок на годност от вече съществуващ едноименен продукт, той да бъде поставен на различно място
//ако имате достатъчно място, еднакви продукти с един и същи срок на годност да бъдат поставени на едно и също място

//при извеждане на списъка с налични продукти да се изведе общото количество на едноименните продукти независимо от срока им на годност
//справка за наличността в даден период (по дадена начална и крайна дата се извежда списък с
// всички промени на наличността в дадения период, включително зареждания и извеждания на стоки)
//
//Пример:
//
//Please choose what to do (1 - List all items; 2 - Add new delivery; 3 - List deliveries for time period)
//<< 1
//Light bulb - LED 75W | Expiry date: n/a | Entry date: 05.05.2021 | Manufacturer: Philips | Unit: Item | Stock: 104 | Position: A3 / 4 / 10 | Available items at shelf: 500 | Comment:
//Battery AAA | Expiry date: 10.10.2026 | Entry date: 10.07.2020 | Manufacturer: Duracel | Unit: Item | Stock: 638 | Position: C2 / 5 / 1 | Available items at shelf: 10000 | Comment:in every box there are 10 packages of 4 batters each
//
//
//Please choose what to do (1 - List all items; 2 - Add new delivery; 3 - List deliveries for time period)
//<< 2
//Enter product name:
//<< Battery CR32
//Enter expiry date:
//<<24.11.2025
//Enter entry date:
//<< 02.06.2021
//Enter manufacturer:
//<< Varta
//Enter unit:
//<< Item
//Enter available stock:
//<< 900
//Enter comment (optional):
//<<
//Product was added successfully!
//
//
//Please choose what to do (1 - List all items; 2 - Add new delivery; 3 - List deliveries for time period)
//<< 3
//From date:
//<< 01.05.2021
//To date:
//<< 31.05.2021
//List of product transactions for the period 01.05.2021 - 31.05.2021:
//Light bulb - LED 75W | Entry date: 05.05.2021 | Unit: Item | Stock delivered: 200


//Light bulb - LED 75W | Expiry date: n/a | Entry date: 05.05.2021 | Manufacturer: Philips | Unit: Item | Stock: 104 | Position: A3 / 4 / 10 | Available items at shelf: 500 | Comment:

// todo - Expiry date: n/a | Entry date: 05.05.2021 - expirity date can be n/a or null
// todo - readFromDB , writeToDB
// todo ASK:
// unit - ITEM or ?
public class Main {


    // todo checker - [][][] or letter[][][]
    // todo String unit --> boolean unit --> килограми, литри
    // todo Enter available stock: ???
    // todo - color output
    // todo - print in the middle
    // todo - if no expiry date - empty string as answer or n/a?
    // todo - add error printing in data validation --> what's wrong as a parameter
    // todo - don't allow separator to be used in the data.
    // todo - postypvane v sklada - opciq - today
    // todo - opciq cancel - dokato vyvejdame dannite ako sme obyrkali neshto.

    // todo - items per shelf - ako produkta ve4e go ima - da izpishe kolko se sybirat na edin raft.
    // produkta moje da e s promeneni razmeri i da se sybirat po-malko ili pove4e ot nego.

    // todo - print "Product was added successfully! after getAllValidUserInput()"

    // todo - forbiden elements - " and separator/delimiter

    // todo - write tests

    // todo - if entry date earlier than expiry date - warning - stock is expired

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


    public static void main(String[] args) throws IOException, ParseException {
//        String[] test = getAllValidUserInput();
//        System.out.println(Arrays.toString(test));
//        String[] test2 = {"tesla", "n/a", "20.12.2022", "Tesla", "item", "20", "fast car"};
//        writeDataToDB(DB_FILE_NAME, test2);

//        printAlldataFromDB();
//        System.out.println(isPositionValid("A3 / 4 / 10"));
//        System.out.println(isPositionValid("AА3 / 4 / 10"));
//        System.out.println(isPositionValid("A3 / А4 / 10"));
//        System.out.println(isPositionValid("A3 / 4 / А10"));
//        System.out.println(isPositionValid("A3/4/10"));
//        System.out.println(isPositionValid("A3.4/10"));
//        System.out.println(isPositionValid("A4/10"));

//        System.out.println(isNumber("8"));
//        System.out.println(isNumber("128"));
//        System.out.println(isNumber("128f"));
//        System.out.println(isNumber("a12"));
//        System.out.println(isNumber("a"));

//        String[] x = generateAllPossiblePositions();
//        System.out.println(Arrays.toString(x));

//        getValidUserInput("unit");
//            getAllValidUserInput();
//        System.out.println(UNIT_OPTIONS.keySet());
//        getAllLocationsThatHaveAtLeastOneItem();

//        List<String> x = getAllLocationsThatHaveAtLeastOneItem();
//        System.out.println(x);

//        String[] x = getAllValidUserInput();
//        System.out.println(Arrays.toString(x));

//        String[] y = {"32 tv", "n/a", "20.10.2010", "Samsung", "unit", "20", "x"};
//        int yl = y.length;
//        String[] y2 = insertElementInArray(y, "test", yl);
//        System.out.println(Arrays.toString(y2));

//        int unitPosition = Arrays.asList(USER_QUESTIONS).indexOf("unit");
//        System.out.println(unitPosition);
//        int numberOfItemsThatCanFitOnShelf = UNIT_OPTIONS.get(unitPosition);
//        System.out.println(numberOfItemsThatCanFitOnShelf);

//        String[] data = {"Battery CR321", "24.11.2025", "02.06.2021", "Varta", "Unit", "900", "A3 / 4 / 10", "10000", ""};
//        printResults(data);
//        System.out.println(getAllUniqueLocationsForItem(data));

//        printAllDataFromDB();
//        printDataForTimePeriod();
//        printDataForTimePeriod();
//        printDataForTimePeriod2();

        //[Battery CR32 | Expiry date:  24.11.2025 | Entry date:  02.06.2021 | Manufacturer:  Varta | Unit:  Item | Stock:  900 | Position:  A3 / 4 / 10 | Available items at shelf:  10000] |
        //[Battery CR321 | Expiry date:  24.11.2025 | Entry date:  02.06.2021 | Manufacturer:  Varta | Unit:  Item | Stock:  900 | Position:  A3 / 7 / 10 | Available items at shelf:  10000] |
        //[Battery CR321 | Expiry date:  24.11.2025 | Entry date:  02.06.2021 | Manufacturer:  Varta | Unit:  Item | Stock:  10000 | Position:  A3 / 6 / 10 | Available items at shelf:  10000] |
        //[Battery CR321 | Expiry date:  24.11.2025 | Entry date:  02.06.2021 | Manufacturer:  Varta | Unit:  Item | Stock:  1000 | Position:  A3 / 5 / 10 | Available items at shelf:  10000] |
        //
        //Battery CR32 | Expiry date: 24.11.2025 | Entry date: 02.06.2021 | Manufacturer: Varta | Unit: Item | Stock: 900 | Position: A3 / 4 / 10 | Available items at shelf: 10000 |
        //Battery CR321 | Expiry date: 24.11.2025 | Entry date: 02.06.2021 | Manufacturer: Varta | Unit: Item | Stock: 900 | Position: A3 / 7 / 10 | Available items at shelf: 10000 |
        //Battery CR321 | Expiry date: 24.11.2025 | Entry date: 02.06.2021 | Manufacturer: Varta | Unit: Item | Stock: 10000 | Position: A3 / 6 / 10 | Available items at shelf: 10000 |
        //Battery CR321 | Expiry date: 24.11.2025 | Entry date: 02.06.2021 | Manufacturer: Varta | Unit: Item | Stock: 1000 | Position: A3 / 5 / 10 | Available items at shelf: 10000 |

//        printDataForTimePeriod();
//        printAllDataFromDB();
//        getAllLocationsThatHaveAtLeastOneItem();

        writeAllDataToDB();
//        int unitPosition = Arrays.asList(USER_QUESTIONS).indexOf("unit");
//        System.out.println(unitPosition);
//        String currentUnitType = allValidUserInput[unitPosition];
//        List<List<String>> x =getAllData(data);
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

    // todo
    public static List<String> getAllLocationsThatHaveAtLeastOneItem() throws IOException {
        List<String> result = new ArrayList<>();
        String[] DB = getDbDataToStringArray();

        for (String row : DB) {
            result.add(row.split(SEPARATOR)[6]);
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
        for (String location:locations) {
            if (getFreeSpaceAtLocationIfAtLeastOneItem(location) > 0){
                return location;
            }
        }

        // If item not in DB or in DB but all locations are full - get the next free location.
        // todo - check if free location in warehouse
        return getFirstLocationThatDoeNotHaveAnyItems();
    }

//    public static List<String> getPositionToPlaceItems(String[] allValidUserInput) throws IOException {
//        List<String> result = new ArrayList<>();
//        int numItemsStockToAdd = Integer.parseInt(allValidUserInput[5]);
//
//        HashMap<String, Integer> ItemAndExpiryDateAlreadyInDB = getAllUniqueLocationsForItem(allValidUserInput);
////        for (List<String,Integer>item:ItemAndExpiryDateAlreadyInDB) {        }
//
//        for (Map.Entry<String, Integer> entry : ItemAndExpiryDateAlreadyInDB.entrySet()) {
//            String itemPosition = entry.getKey();
//            int positionFreeSpace = entry.getValue();
//
//            // If items can fit - return that position
//            if (positionFreeSpace >= numItemsStockToAdd) {
//                result.add(itemPosition);
//                return result;
//            } else {
//                // todo while loop - all empty - fill in the rest.
//            }
//        }
//    }

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

    // todo
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

        List<String> allValidUserInputList = new ArrayList<>();
        for (String element:allValidUserInput) {
            allValidUserInputList.add(element);
        }

        List<List<String>> newRowsToAdd = new ArrayList<>();

        String itemName = allValidUserInput[0];
        String expiryDate = allValidUserInput[1];

        // get the position where "unit" is in the questions. (Could be hardcoded too)
        // Get the answer at that position and use it to get the value for that answer from the map.
        int numberOfItemsThatCanFitOnShelf = UNIT_OPTIONS.get(allValidUserInput[4]); // only when new location
        String numberOfItemsThatCanFitOnShelfString = String.valueOf(numberOfItemsThatCanFitOnShelf);

        int numItemsRemainingToAdd = Integer.parseInt(allValidUserInput[5]);
        while (numItemsRemainingToAdd > 0) {
            // might be new - with no data in it.
            String positionToPlaceItem = getPositionToPlaceItem(itemName, expiryDate);

            // If item is in DB - get free space in location
            int numItemsThatWillFitInLocation;
            if (isAtLeastOneItemInLocation(positionToPlaceItem)){
                numItemsThatWillFitInLocation = getFreeSpaceAtLocationIfAtLeastOneItem(positionToPlaceItem);
            } else {
                numItemsThatWillFitInLocation = numberOfItemsThatCanFitOnShelf;
            }

            // There might be 100 free spaces but only 10 items need to be added
            int numItemsThatWillBeAddedToLocation;
            if (numItemsRemainingToAdd < numItemsThatWillFitInLocation) {
                numItemsThatWillBeAddedToLocation = numItemsRemainingToAdd;
            } else {
                numItemsThatWillBeAddedToLocation = numItemsThatWillFitInLocation;
            }

            allValidUserInputList.add(6, positionToPlaceItem);
            allValidUserInputList.add(7, numberOfItemsThatCanFitOnShelfString);
            newRowsToAdd.add(allValidUserInputList);

            numItemsRemainingToAdd -= numItemsThatWillBeAddedToLocation;
        }

        return newRowsToAdd;
    }
    

    public static void writeAllDataToDB() throws IOException {
        String[] allValidUserInput = getAllValidUserInput();
        List<List<String>> allData = getAllData(allValidUserInput);
//        String[][] allDataArr = convertListToArray(allData);
//        for (String[] row:allDataArr) {
//            writeDataToDB(row);
//        }
        for (List<String> row:allData) {
            writeDataToDB(row);
        }
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

    // todo - maybe not needed
    public static boolean isItemAndExpiryDateAlreadyInDB(String[] allValidUserInput) throws IOException {
        String itemName = allValidUserInput[0];
        String expiryDate = allValidUserInput[1];
        String[] DB = getDbDataToStringArray();
        for (String row : DB) {
            String currentItemName = row.split(SEPARATOR)[0];
            String currentExpiryDate = row.split(SEPARATOR)[1];
            if ((itemName.equalsIgnoreCase(currentItemName)) && expiryDate.equalsIgnoreCase(currentExpiryDate)) {
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
            String currentItemName = row.split(SEPARATOR)[0];
            String currentExpiryDate = row.split(SEPARATOR)[1];
            if ((itemName.equalsIgnoreCase(currentItemName)) && expiryDate.equals(currentExpiryDate)) {

                String currentPosition = row.split(SEPARATOR)[6];

                // Add position only once - there might be multiple rows with same position.
                if (!positions.contains(currentPosition)) {
                    positions.add(currentPosition);
                }
            }
        }

        return positions;
    }


    public static void printAllDataFromDB() throws IOException {
        String[][] DB = getAllDataFromDB();
        printResults(DB);
    }

    public static String[][] getAllDataFromDB() throws IOException {
        int arrayLen = getLenOfFile(DB_FILE_NAME);
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

    public static int getLenOfFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }

    public static String[] getDbDataToStringArray() throws IOException {
        // Read the DB file row by row and add each row to an Array

        int numRowsInFile = getLenOfFile(DB_FILE_NAME);

        String[] rowsData = new String[numRowsInFile];

        File file = new File(DB_FILE_NAME);
        Scanner sc = new Scanner(file);

        for (int i = 0; i < numRowsInFile; i++) {
            rowsData[i] = sc.nextLine();
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


    public static void getPositionToPlaceItems(String[] freePositions) {
        // need to check if item already exists and is same expiry date in the DB.
        // if not - check next free position and place them there
        System.out.println();
    }

    public static void isEnoughSpaceOnShelf(String positionToCheck) {
        System.out.println();
    }


    public static void getItemPosition(String itemName, String expiryDate) {
        // One item with same expiry date can be in multiple places in the warehouse.
        // Check is shelf is full. If not full - calculate how many items can be placed there are free
        System.out.println();
    }

    public static void isShelfFull(String position) {
        // Check numItems on that location
        // Check how many items fit on the shelf.
        System.out.println();
    }

    public static void isSpaceOnShelfEnough(String position, int numItemsToAdd) {
        System.out.println();
    }


}


// todo - works - keep until all working

//    public static void printDataForTimePeriod2() throws IOException, ParseException {
//        String fromDate = getValidUserInput("From date");
//        String toDate = getValidUserInput("To date");
//
//        String[] DB = getDbDataToStringArray();
//
//        for (String row : DB) {
//            String[] rowArray = row.split(SEPARATOR);
//            String currentEntryDate = rowArray[2];
//            if (isDateBetweenTwoDates(currentEntryDate, fromDate, toDate)) {
//
//                // print the name of the item - and a separator on the back - "Light bulb - LED 75W | "
//                System.out.print(rowArray[0] + SEPARATOR_WHEN_PRINTING);
//
//                for (int i = 1; i < rowArray.length; i++) {
//                    System.out.print(DESCRIPTION_NO_NAME[i - 1] + ": " + rowArray[i] + SEPARATOR_WHEN_PRINTING);
//                }
//
//                System.out.println();
//            }
//        }
//    }


//    public static void printAllDataFromDB2() throws IOException {
//        String[] DB = getDbDataToStringArray();
//
//        for (String row : DB) {
//            String[] rowArray = row.split(SEPARATOR);
//
//            // print the name of the item - and a separator on the back - "Light bulb - LED 75W | "
//            System.out.print(rowArray[0] + SEPARATOR_WHEN_PRINTING);
//
//            for (int i = 1; i < rowArray.length; i++) {
//                System.out.print(DESCRIPTION_NO_NAME[i - 1] + ": " + rowArray[i] + SEPARATOR_WHEN_PRINTING);
//            }
//
//            System.out.println();
//        }
//    }


//    public static void writeDataToDB(String[] row) {
//        try {
//            FileWriter writer = new FileWriter(DB_FILE_NAME, true);
//
//            for (String element : row) {
//                writer.write(element + SEPARATOR);
//            }
//            writer.write("\n");
//
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("An error occurred while writing " + Arrays.toString(row));
//            e.printStackTrace();
//        }
//    }