import java.io.*;
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
            "Kg", 100,
            "Liter", 100,
            "Meter", 10,
            "Unit", 1000
    );

    static String[] USER_QUESTIONS = new String[]{"product name", "expiry date", "entry date", "manufacturer", "unit",
            "available stock", "comment (optional)"};

//    static String[] listKeys = new String[]{"name", "expiryDate", "entryDate", "manufacturer", "unit",
//            "stock", "quantity", "position", "itemsPerShelf", "comment"};


    public static void main(String[] args) throws IOException {
//        String[] test = getUserInputAllData();
//        System.out.println(Arrays.toString(test));
//        String[] test2 = {"tesla", "n/a", "20.12.2022", "Tesla", "item", "20", "fast car"};
//        appendArrayRowToFile(DB_FILE_NAME, test2);

//        printAlldataFromDB();
//        System.out.println(positionValidator("A3 / 4 / 10"));
//        System.out.println(positionValidator("AА3 / 4 / 10"));
//        System.out.println(positionValidator("A3 / А4 / 10"));
//        System.out.println(positionValidator("A3 / 4 / А10"));
//        System.out.println(positionValidator("A3/4/10"));
//        System.out.println(positionValidator("A3.4/10"));
//        System.out.println(positionValidator("A4/10"));

//        System.out.println(isNumber("8"));
//        System.out.println(isNumber("128"));
//        System.out.println(isNumber("128f"));
//        System.out.println(isNumber("a12"));
//        System.out.println(isNumber("a"));

//        String[] x = generateAllPossiblePositions();
//        System.out.println(Arrays.toString(x));

//        getUserInput("unit");
//            getUserInputAllData();
//        System.out.println(UNIT_OPTIONS.keySet());
//        getUsedPositions();

//        List<String> x = getUsedPositions();
//        System.out.println(x);

//        String[] x = getUserInputAllData();
//        System.out.println(Arrays.toString(x));

//        String[] y = {"32 tv", "n/a", "20.10.2010", "Samsung", "unit", "20", "x"};
//        int yl = y.length;
//        String[] y2 = insertElementInArray(y, "test", yl);
//        System.out.println(Arrays.toString(y2));

        int unitPosition = Arrays.asList(USER_QUESTIONS).indexOf("unit");
        System.out.println(unitPosition);
        int numberOfItemsThatCanFitOnShelf = UNIT_OPTIONS.get(unitPosition);
        System.out.println(numberOfItemsThatCanFitOnShelf);

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

    //    public static void registerProduct(String name, String dateExpiry, String dateBought, String unit, int quantity, String placement, int itemsPerShelf, String comment) {
    public static boolean isNumber(String num) {
        try {
            Integer.parseInt(num.strip());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean positionValidator(String placement) {
        // Check if it's made out of секция/рафт/номер
        // A3 / 4 / 10
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

    public static void getPositionToPlaceItems(String[] freePositions) {
        // need to check if item already exists and is same expiry date in the DB.
        // if not - check next free position and place them there
        System.out.println();
    }

    public static void isEnoughSpaceOnShelf(String positionToCheck) {
        System.out.println();
    }

    public static void isItemAndExpiryDateAlreadyInDB(String itemName, String expiryDate) {
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

    public static int getNumberOfItemsThatWillFitOnTheShelf(String position, int numItemsToAdd) {
        // if num items == numItemsToAdd - check in method that calls it. --> all fit
        // else - numIntemsToAdd - what is returned will fit - the rest is reminder
        System.out.println();
        return 1;
    }

    public static List<String> getAllPossiblePositions() {

        List<String> result = new ArrayList<>();

        for (char c : LETTER_SECTION) {
            for (int i = 0; i < NUM_SECTION; i++) {
                for (int j = 0; j < NUM_SHELVES; j++) {
                    for (int k = 0; k < NUM_NUMBERS; k++) {
                        String itemPosition = String.valueOf(c) + i + " / " + j + " / " + k;
                        result.add(itemPosition);
                    }
                }
            }
        }

        return result;
    }

    public static List<String> getUsedPositions() throws IOException {
        List<String> result = new ArrayList<>();
        String[] allData = getDbDataToStringArray();

        for (int i = 0; i < allData.length; i++) {
            // todo - get position of position
            System.out.println(allData[i].split(SEPARATOR)[0]);
        }

        return result;
    }

    public static String getFirstFreePosition() throws IOException {
        List<String> allPositions = getAllPossiblePositions();
        List<String> usedPositions = getUsedPositions();

        for (String usedPosition : usedPositions) {
            allPositions.remove(usedPosition);
        }

        // Need to sort the list to get the first position
        java.util.Collections.sort(allPositions);
        return allPositions.get(0);
    }

//    public static String getformattedPosition(char c, int i, int j, int k) {
//        return String.valueOf(c) + i + " / " + j + " / " + k;
//    }

    public static boolean dateValidator(String date) {
        // String date will be in format "dd.mm.yyyy"
        // Will return True if date is valid, else False.

        String[] parts = date.split("\\.");

        // Check if the formatting is correct - 2 dots -> xx.yy.zzzz
        if (parts.length != 3) {
            return false;
        }

        // Check if all parts can be converted to integers and all are bigger than 1.
        // Day, month or year can't be 0.
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
        // A leap year occurs nearly every 4 years which adds an extra day to February.
        // February is the only month with 28 days.
        // There are 30 days in April, June, September and November.
        // There are 31 days in January, March, May, July, August, October, December.

        int[] months30Days = {4, 6, 9, 11};
        int[] months31Days = {1, 3, 5, 7, 8, 10, 12};

        if (isCurrentMonthInArray(months30Days, month)) {
            return 30;
        }

        if (isCurrentMonthInArray(months31Days, month)) {
            return 31;
        }

        // February will be different if leap year
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

    public static boolean unitValidator(String unit) {
        for (String unitOption : UNIT_OPTIONS.keySet()) {
            if (unit.strip().equalsIgnoreCase(unitOption)) {
                return true;
            }
        }
        return false;
    }

    public static String getUserInput(String question) {
        // Will make sure that the data entered by the user is validated.

        System.out.println("Enter " + question + ": ");
        String ans = scanner.nextLine();
        if (ans.strip().equalsIgnoreCase("reset!")) {
            return ans.strip();
        }
        // Make sure that the unit question returns a valid answer
        if (question.contains("unit")) {
            boolean isValid = unitValidator(ans);
            while (!isValid) {
                String availableOptions = String.join(", ", UNIT_OPTIONS.keySet());
                System.out.println("Error! Please Enter a valid option for Unit: " + availableOptions + ".");
                ans = scanner.nextLine();
                isValid = unitValidator(ans);
            }
        }

        // Make sure that the date question returns a valid answer
        else if (question.equalsIgnoreCase("expiry date")) {
            boolean isValid = dateValidator(ans);
            while (!(isValid || ans.equalsIgnoreCase("n/a"))) {
                System.out.println("Error! Please Enter a date in the format: \"dd.mm.yyyy\" or \"n/a\" if the product doesn't expire.");
                ans = scanner.nextLine();
                isValid = dateValidator(ans);
            }
        }

        // entry date - needs to be filled in.
        else if (question.equalsIgnoreCase("entry date")) {
            boolean isValid = dateValidator(ans);
            while (!isValid) {
                System.out.println("Error! Please Enter a date in the format: \"dd.mm.yyyy\"");
                ans = scanner.nextLine();
                isValid = dateValidator(ans);
            }
        }

        // Allow empty answer if the question is optional. Require an input if not.
        else if (!question.contains("optional")) {
            while (ans.isEmpty()) {
                System.out.println("Empty answer. Please enter " + question + ": ");
                ans = scanner.nextLine();
            }
        }

        return ans.strip();
    }

    public static String[] getUserInputAllData() {

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

//        String[] questions = new String[]{"product name", "expiry date", "entry date", "manufacturer", "unit",
//                "available stock", "comment (optional)"};

        System.out.println("Fill in the data for the items you want to add. Use \"reset!\" to reset the data input.");

        String[] answers = new String[USER_QUESTIONS.length];
        for (int i = 0; i < USER_QUESTIONS.length; i++) {
            String ans = getUserInput(USER_QUESTIONS[i]);
            if (ans.equals("reset!")) {
                getUserInputAllData();
            }
            answers[i] = ans;
        }

        return answers;
    }

    public static String[] getAllData(String[] userInputAllData) throws IOException {
        //Stock: 104 | Position: A3 / 4 / 10 | Available items at shelf: 500 | Comment:
        // User data + first possible position + available items (from map)  === Comment at the end.
        int userInputAllDataLength = userInputAllData.length;

        String positionToPlaceItem = getFirstFreePosition();

        int unitPosition = Arrays.asList(USER_QUESTIONS).indexOf("unit");  // todo
        int numberOfItemsThatCanFitOnShelf = UNIT_OPTIONS.get(unitPosition);
        String numberOfItemsThatCanFitOnShelfString = String.valueOf(numberOfItemsThatCanFitOnShelf);

        String[] arrayAfterFirstAdded = insertElementInArray(userInputAllData, positionToPlaceItem, userInputAllDataLength);
        String[] arrayAfterSecondAdded = insertElementInArray(arrayAfterFirstAdded, numberOfItemsThatCanFitOnShelfString, userInputAllDataLength + 1);

        return arrayAfterSecondAdded;
    }

    public static void writeDataToDB(String fileName, String[] rowData) {
        appendArrayRowToFile(fileName, rowData);
    }

    // todo position + available items
    public static void printAllDataFromDB() throws IOException {
        String[] dbData = getDbDataToStringArray();

        // Starting at the second element - "Name" not printed.
        String[] description = new String[]{"Expiry date", "Entry date", "Manufacturer", "Unit", "Stock", "Position", "Available items at shelf", "Comment"};

        for (String row : dbData) {
            String[] rowArray = row.split(SEPARATOR);
            //    // Light bulb - LED 75W | Expiry date: n/a | Entry date: 05.05.2021 | Manufacturer: Philips |
            //    Unit: Item | Stock: 104 | Position: A3 / 4 / 10 | Available items at shelf: 500 | Comment:

            // print the name of the item - and a separator on the back - "Light bulb - LED 75W | "
            System.out.print(rowArray[0] + SEPARATOR_WHEN_PRINTING);

            for (int i = 1; i < rowArray.length; i++) {
                System.out.print(description[i - 1] + ": " + rowArray[i] + SEPARATOR_WHEN_PRINTING);
            }

            System.out.println();
        }
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

    public static void appendArrayRowToFile(String fileName, String[] row) {
        try {
            FileWriter writer = new FileWriter(fileName, true);

            for (String element : row) {
                writer.write(element + SEPARATOR);
            }
            writer.write("\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing " + Arrays.toString(row));
            e.printStackTrace();
        }
    }

}