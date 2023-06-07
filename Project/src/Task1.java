import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Task1 {
    public static void main(String[] args) throws IOException {
        //Да се направи специален електронен дневник за частно училище Лудогорче. Програмата трябва да има следните функционалности:
        //Да прочита информация от текстов файл (.txt), която съдържа списък с 2 имена и възраст на ученици.
        // На всеки ред има по 2 имена и възраст и има много редове във файла. Програмата трябва да разпределя
        // учениците в класове 8А, 8Б, 9А и 9Б по следните критерии:
        //ако е на 13 или 14 години - в 8 клас
        //ако е на 15 години - в 9 клас
        //ако броя букви в двете имена е под 15 - в А паралелка, ако е 15 или повече буква - в Б паралелка
        //Програмата трябва да генерира произволни оценки на учениците. Като за момичетата генерира
        // оценки между 4 и 6, а за момчетата между 2 и 6. Програмата разпознава дали един ученик е
        // момче или момиче според последната буква на първото име - ако е гласна буква е момиче, ако е
        // съгласна буква - е момче. Като за всеки ученик генерира между 6 и 15 оценки.
        //Програмата записва цялата генериране информаци в CSV файл със следните колони:
        // име на ученик, фамилия, клас (8А / 9Б), оценки, среден успех

        String[] dataFromFile = readTxtFileToStringArray("students-list1.txt");
        generateAndWriteDataToCSV("output2.csv", dataFromFile);
    }

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

    public static void appendRowToFile(String fileName, String row) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(row + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing " + row);
            e.printStackTrace();
        }
    }

    public static String getClassLetter(String fName, String lName) {
        // паралелка
        String classLetter = "";
        if (fName.length() + lName.length() < 15) {
            classLetter = "A";
        } else if (fName.length() + lName.length() >= 15) {
            classLetter = "B";
        }
        return classLetter;
    }

    public static int getClassGrade(int age){
        int grade = 0;
        if (age == 13 || age == 14) {
            grade = 8;
        } else if (age == 15) {
            grade = 9;
        }
        return grade;
    }

    public static boolean isGirl(String fName) {
        String[] vowels = {"а", "ъ", "о", "у", "е", "и"};
        for (int j = 0; j < vowels.length; j++) {
            if (fName.endsWith(vowels[j])) {
                return true;
            }
        }
        return false;
    }

    public static int[] getRandomScores(boolean isGirl) {
        Random rand = new Random();
        int numScores = rand.nextInt(9) + 6;
        int currScore;
        int[] scores = new int[numScores];
        for (int j = 0; j < numScores; j++) {
            if (isGirl) {
                currScore = rand.nextInt(2) + 4;
            } else {
                currScore = rand.nextInt(4) + 2;
            }
            scores[j] = currScore;
        }
        return scores;
    }

    public static float calcAvgScore(int[] scores) {
        int totalScores = 0;
        for (int i = 0; i < scores.length; i++) {
            totalScores += scores[i];
        }
        return (float) totalScores / scores.length;
    }

    public static void generateAndWriteDataToCSV(String fileNameToWrite, String[] rowsDataFromTxtFile) {
        for (int i = 0; i < rowsDataFromTxtFile.length; i++) {

            String[] curUser = rowsDataFromTxtFile[i].split(" ");
            String fName = curUser[0];
            String lName = curUser[1];

            // Get grade - 8 or 9
            int age = Integer.parseInt(curUser[2]);
            int grade = getClassGrade(age);

            // A or B
            String classLetter = getClassLetter(fName, lName);

            // Girl or boy
            boolean isGirl = isGirl(fName);

            // Get all scores (random) - different number for each person
            int[] scores = getRandomScores(isGirl);

            // Get avg score
            float avgScore = calcAvgScore(scores);

            //име на ученик, фамилия, клас (8А / 9Б), оценки, среден успех
            String row = fName + "," + lName + ',' + grade+classLetter + ',' + Arrays.toString(scores) + ',' + avgScore;

            // write to csv file
            appendRowToFile(fileNameToWrite, row);
        }
    }

}
