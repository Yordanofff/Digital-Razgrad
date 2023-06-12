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

//    int x;


    // Constructor with a parameter
//    public Main(int x) {
//        this.x = x;
//        this.DB_FILE_NAME = "app_data.csv";
//    }
    static Scanner scanner = new Scanner(System.in);
//    static String DB_FILE_NAME = "app_data.csv";
    static String SEPARATOR = ";";
    static String SEPARATOR_WHEN_PRINTING = " | ";
    static String DB_FILE_NAME = "app_data.csv";

//    static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] letterSection = "abc".toCharArray();

    static int numSections = 3;
    static int numShelves = 5;
    static int numNumbers = 5;

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
        generateAllPossiblePositions();


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

//    public static void positionGenerator(String)

    public static void generateAllPossiblePositions() {
//        int[][][] matrix = new int[numSections][numShelves][numNumbers];
        for (char c: letterSection) {
            for (int i = 0; i < numSections; i++) {
                for (int j = 0; j < numShelves; j++) {
                    for (int k = 0; k < numNumbers; k++) {
                        System.out.println(String.valueOf(c) + i + " / " + j + " / " + k);
                    }
                }
            }
        }
    }

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

        // Feb
        if (isLeapYear(year)) {
            return 29;
        } else {
            return 28;
        }
    }

    public static boolean isCurrentMonthInArray(int[] months, int month) {
        for (int m : months) {
            if (m == month) {
                return true;
            }
        }
        return false;
    }


    public static boolean unitValidator(String unit) {
        if (unit.equalsIgnoreCase("item") || unit.equalsIgnoreCase("liter")) {
            return true;
        }
        return false;
    }



    public static String getUserInput(String question) {
        // Will make sure that the data entered by the user is validated.

        System.out.println("Enter " + question + ": ");
        String ans = scanner.nextLine();

        // Make sure that data has been entered for every question except the ones that are optional.
        if (!question.contains("optional")) {
            while (ans.isEmpty()) {
                System.out.println("Empty answer. Please enter " + question + ": ");
                ans = scanner.nextLine();
            }
        }

        // Make sure that the unit question returns a valid answer
        if (question.contains("unit")) {
            boolean isValid = unitValidator(ans);
            while (!isValid) {
                System.out.println("Error! Unit can be \"Item\" or \"Liter\". Please Enter a valid answer: ");
                ans = scanner.nextLine();
                isValid = unitValidator(ans);
            }
        }

        // Make sure that the date question returns a valid answer
        else if (question.equalsIgnoreCase("expiry date")) {
            boolean isValid = dateValidator(ans);
            while (!(isValid || ans.equalsIgnoreCase("n/a"))) {
                System.out.println("Error! Date needs to be in the format \"dd.mm.yyyy\" or \"n/a\". Please Enter a valid answer: ");
                ans = scanner.nextLine();
                isValid = dateValidator(ans);
            }
        }

        // Make sure that the date question returns a valid answer
        else if (question.equalsIgnoreCase("entry date")) {
            boolean isValid = dateValidator(ans);
            while (!isValid) {
                System.out.println("Error! Date needs to be in the format \"dd.mm.yyyy\". Please Enter a valid answer: ");
                ans = scanner.nextLine();
                isValid = dateValidator(ans);
            }
        }

        return ans;
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

        String[] questions = new String[]{"product name", "expiry date", "entry date", "manufacturer", "unit",
                "available stock", "comment (optional)"};


        String[] answers = new String[questions.length];

        for (int i = 0; i < questions.length; i++) {
            String ans = getUserInput(questions[i]);
            answers[i] = ans;
        }
        return answers;
    }

    public static void writeDataToDB(String fileName, String[] rowData){
        appendArrayRowToFile(fileName, rowData);
    }

    // todo position + available items
    public static void printAlldataFromDB() throws IOException {
        String[] dbData = readTxtFileToStringArray(DB_FILE_NAME);

        // Starting at the second element - "Name" not printed.
        String[] description = new String[]{"Expiry date", "Entry date", "Manufacturer", "Unit", "Stock", "Position", "Available items at shelf", "Comment"};

        for (String row:dbData) {
            String[] rowArray = row.split(SEPARATOR);
            //    // Light bulb - LED 75W | Expiry date: n/a | Entry date: 05.05.2021 | Manufacturer: Philips |
            //    Unit: Item | Stock: 104 | Position: A3 / 4 / 10 | Available items at shelf: 500 | Comment:

            // print the name of the item - and a separator on the back - "Light bulb - LED 75W | "
            System.out.print(rowArray[0] + SEPARATOR_WHEN_PRINTING);
            for (int i = 1; i < rowArray.length; i++) {
                System.out.print(description[i-1] +": " + rowArray[i] + SEPARATOR_WHEN_PRINTING);
            }
            System.out.println();
        }
    }

    // 1: name
    // 2: expiryDate
    // 3: entryDate
    // 4: manufacturer
    // 5: unit
    // stock
    // 6: quantity (int)
    // 7: position
    // 8: itemsPerShelf (int)
    // 9: comment

//        HashMap<String, String> dict = new HashMap<String, String>();
//
//        for (int i = 0; i < listQuestions.length; i++) {
//            String curValue = readString("Въведете " + listQuestions[i] + ": ");
//            dict.put(listKeys[i], curValue);
//
//        }



    public static int getLenOfFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }
    public static String[] readTxtFileToStringArray(String fileName) throws IOException {
        // Read a file row by row and add it to an Array

        int rowsInFile = getLenOfFile(fileName);

        String[] toReturn = new String[rowsInFile];

        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        for (int i = 0; i < rowsInFile; i++) {
            toReturn[i] = sc.nextLine();
        }

        return toReturn;
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