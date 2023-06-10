import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//            ArrayList<Integer> nums = genNumbers(5);
//
//            printArrayList(nums);
//            printArrayList2(nums);
//
//            Vector<Integer> nums2 = generateVectorOfNumbers(5);
//            System.out.println();
//            printVector(nums2);
//        task_1("text_file.txt");
//        task_2();

        HashMap<String, String[]> buildings = new HashMap<>();
        String[] buildingInfo = new String[2];

        buildingInfo[0] = "ул. Мусала 2, ет. 5";
        buildingInfo[1] = "8:30 - 17:30";
        buildings.put("Филхармония Пеперудка", buildingInfo);
        addNewBuilding("Театър Радост и Тъга", buildingInfo, buildings);

        buildingInfo[0] = "бул. Иван Петров 8";
        buildingInfo[1] = "6:30 - 22:30";
        addNewBuilding("Театър Радост и Тъга", buildingInfo, buildings);
        printBuildingDetails(buildings, "Филхармония Пеперудка");
        printBuildingDetails(buildings, "Театър Радост и Тъга");

    }
    public static void task_1(String fileName) {
        //Да се напише програма, която прочита текстов файл .txt и записва всяка дума от
        // файла в отделен елемент на ArrayList. Подредете думите по азбучен ред.
        //б) Да се добави и метод за търсене в ArrayList-a. Потребителя въвежда дума str
        // и се проверява дали тази дума се съдържа в списъка.
        //в) Да се направи нова структура от данни, която съдържа всяка дума от текста само по веднъж
        ArrayList<String> fileData = readFileToArray(fileName);
        printArrayListString(fileData, "; ");
        System.out.println();
        printArrayListStringSorted(fileData, "; ");
        HashSet<String> textToSet = arrayToSet(fileData);
        System.out.println();
        System.out.println(textToSet);
    }

    public static HashSet<String> arrayToSet(ArrayList<String> list) {
        HashSet<String> set = new HashSet<>(list);
        return set;
    }
    public static ArrayList<String> readFileToArray(String fileName) {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {

                for (String word: sCurrentLine.split(" ")) {
                    list.add(word);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void printArrayListString(ArrayList<String> list, String separator) {
        if (separator == null) {
            separator = " ";
        }
        for (String element: list) {
            System.out.print(element + separator);
        }
    }
    public static void printArrayListStringSorted(ArrayList<String> list, String separator) {
        Collections.sort(list);
        printArrayListString(list, separator);
    }


    public static void task_2() {
        //Направете HashMap съхраняващ информация от тълковен речник: дума и нейното значение.
        HashMap<String, Integer> veggies = new HashMap<>();
        veggies.put("Mango", 10);
        veggies.put("Banana", 15);
        veggies.put("Carrots", 12);
        printEveryKeyValueInDict(veggies);
        printFruitQuantity(veggies, "Banana");
        printFruitQuantity(veggies, "Watermelon");
    }
    public static void printEveryKeyValueInDict(HashMap<String, Integer> dict) {
        for (String key: dict.keySet()) {
            System.out.println(key + " -> " + dict.get(key));
        }
    }
    public static void printFruitQuantity(HashMap<String, Integer> vegDict, String vegName) {
        if (vegDict.containsKey(vegName)) {
            System.out.println(vegName + " availability: " + vegDict.get(vegName));
        } else {
            System.out.println(vegName + " not found.");
        }
    }


    public static HashMap<String, String[]> addNewBuilding(String buildingName, String[] buildingInfo, HashMap<String, String[]> buildings) { // ) {
        //* (бонус) Използвайки структури от данни направете програма, която съхранява
        // информация за всички обекти в страната, които предлагат билети за концерти.
        // Може да има неограничен брой такива обекти и за
        // всеки обект се пази следната информация:
        // име на обект, адрес, работно време (свободен текст)
        buildings.put(buildingName, buildingInfo);

        return buildings;
    }

    public static void printBuildingDetails(HashMap<String, String[]> allBuildings ,String buildingName) {
        String[] buildingInfo = allBuildings.get(buildingName);
        System.out.println("Сграда: " + buildingName);
        System.out.println("Адрес: " + buildingInfo[0]);
        System.out.println("Работно време: " + buildingInfo[1]);
        System.out.println();
    }



    
    public static void printVector(Vector<Integer> vector) {
        for (Integer element:vector) {
            System.out.println(element);
        }
    }

    public static Vector<Integer> generateVectorOfNumbers(int n) {
        Vector <Integer> numbers = new Vector<>();
        for (int i = 0; i < n; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    public static ArrayList<Integer> genNumbers(int n) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    public static void printArrayList(ArrayList<Integer> list) {
        for (Integer element: list) {
            System.out.print(element + " ");
        }
    }

    public static void printArrayList2(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

}