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
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//            getData();

//        System.out.println(positionValidator("A3 / 4 / 10"));
//        System.out.println(positionValidator("AА3 / 4 / 10"));
//        System.out.println(positionValidator("A3 / А4 / 10"));
//        System.out.println(positionValidator("A3 / 4 / А10"));
//        System.out.println(positionValidator("A3/4/10"));
//        System.out.println(positionValidator("A3.4/10"));
//        System.out.println(positionValidator("A4/10"));

        System.out.println(isNumber("8"));
        System.out.println(isNumber("128"));
        System.out.println(isNumber("128f"));
        System.out.println(isNumber("a12"));
        System.out.println(isNumber("a"));

    }

    // todo checker - [][][] or letter[][][]
    // todo String unit --> boolean unit --> килограми, литри
    public static String readString(String question) {
        System.out.print(question);
        return scanner.nextLine().strip();
        // validate if null
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

        if (parts.length != 3) {
            return false;
        }
        if (!(isNumber(parts[1]) && isNumber(parts[2]))) {
            return false;
        }

        try {
            // split the first part in String/Integer
            // \D matches all non-digit characters, while \d matches all digit characters
            String[] firstPart = parts[0].strip().split("(?<=\\D)(?=\\d)");

            String letterPart = firstPart[0].strip();
            if (letterPart.length() == 1){
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

    //Light bulb - LED 75W | Expiry date: n/a | Entry date: 05.05.2021 |
    // Manufacturer: Philips | Unit: Item | Stock: 104 |
    // Position: A3 / 4 / 10 | Available items at shelf: 500 | Comment:
//    public static void getData(String[] keys) {
//        String[] questions = new String[]{"product name", "expiry date", "entry date", "manufacturer", "unit",
//                "available stock", "comment (optional)"};
//
//        String[] listKeys = new String[]{"name", "expiryDate", "entryDate", "manufacturer", "unit",
//                "stock", "quantity", "position", "itemsPerShelf", "comment"};
//
//        while (true) {
//            for (String question: questions) {
//                System.out.println("Enter " + question + ":");
//                String ans = scanner.nextLine();
//
//
//            }
//        }
//
//
//        // 1: name
//        // 2: expiryDate
//        // 3: entryDate
//        // 4: manufacturer
//        // 5: unit
//        // stock
//        // 6: quantity (int)
//        // 7: position
//        // 8: itemsPerShelf (int)
//        // 9: comment
//        HashMap<String, String> dict = new HashMap<String, String>();
//
//        for (int i = 0; i < listQuestions.length; i++) {
//            String curValue = readString("Въведете " + listQuestions[i] + ": ");
//            dict.put(listKeys[i], curValue);
//
//        }
//    }

}