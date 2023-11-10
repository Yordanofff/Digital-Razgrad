package Task2.Homework10;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    // Да се създаде клас VolleyballPlayer с полета: име, възраст, позиция, умения (от 1 до 10), кондиция (от 1
    //до 5). И метод train(), който увеличава уменията му с 1 и намаля кондицията с 1. Ако кондицията е 1 -
    //не може да тренира. И метод rest(), който увеличава кондицията му с 1.
    //● Да се създаде клас волейболен отбор с полета: име, стадион, треньор и играчи. Каква структура от
    //данни ще използвате за играчите? Класът има и методи calculateStrength(), който изчислява силата на
    //отбор като средно аритметично от уменията на играчите. Както и методи teamTraining() и teamRest() -
    //целият отбор тренира или почива.
    //● Направете метод playMatch(team1, team2), който определя резултат от мач на база общите умения на
    //отборите и произволно число (от 1 до 25): team1.strength + random1 VS team2.strength + random2
    //● Ползвайте поне 2 интерфейса и всички добри практики за ООП код
    //● Добавете подходящи изключения на поне 2 места.
    //● Направете CSV файлове, които да съдържат информация за поне 6 играча и 2 отбора. Четете от тях
    //първоначалните данни. Резултатите записвайте в друг файл results.txt
    //● Също така да може да се записва новото състояние на играчите (с обновени показатели за умения и
    //кондиция)

    public static void main(String[] args) {

        // Bulgarian Team
        String[] bgTeamNames = {"Градинаров", "Гоцев", "Пенчев", "Братоев", "Тодоров", "Станев"};
        VolleyballPlayer[] bgTeamPlayers = generateListOfTeamPlayers(bgTeamNames);
        VolleyballITeam bgTeam = new VolleyballITeam("BG team", "Васил Левски", "Николай Желязков", bgTeamPlayers);

        // English Team
        String[] enTeamNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis"};
        VolleyballPlayer[] enTeamPlayers = generateListOfTeamPlayers(enTeamNames);
        VolleyballITeam enTeam = new VolleyballITeam("EN team", "Jack Sparrow", "Jon Doe", enTeamPlayers);

        prepareForMatch(bgTeam, 9);
        prepareForMatch(enTeam, 4.2);

        playMatch(bgTeam, enTeam);
    }

    public static void playMatch(VolleyballITeam team1, VolleyballITeam team2) {
        team1.printTeamStraight();
        team2.printTeamStraight();

        double t1TotalScore = getTeamScores(team1);
        double t2TotalScore = getTeamScores(team2);

        printTotalScores(team1, t1TotalScore, team2, t2TotalScore);

        if (t1TotalScore > t2TotalScore) {
            printWinner(team1);
        } else {
            printWinner(team2);
        }
    }

    public static void prepareForMatch(VolleyballITeam team, double desiredTeamStrength) {
        if (desiredTeamStrength > VolleyballPlayer.MAX_SKILLS_SCORE) {
            System.out.println("Team strength can be a maximum of " + VolleyballPlayer.MAX_SKILLS_SCORE);
            desiredTeamStrength = 10;
        }


        printSeparator();
        System.out.println("Preparing team [" + team.getTeamName() + "] for a match. Desired strength: " + desiredTeamStrength);
        if (team.calculateStrength() >= desiredTeamStrength) {
            System.out.println("The team's is already strong enough [" + team.calculateStrength() + "] and doesn't need more training.");
            printSeparator();
            return;
        }
        printSeparator();
        System.out.println("Current team score: " + StringFormatting.formatToDecimalPoints(2, team.calculateStrength()));

        while (team.calculateStrength() < desiredTeamStrength) {
            team.teamRest();
            team.teamTraining();

            System.out.println("Current team score: " + StringFormatting.formatToDecimalPoints(2, team.calculateStrength()));
        }

        System.out.println("Team score after preparation: " + StringFormatting.formatToDecimalPoints(2, team.calculateStrength()));
        printSeparator();
    }

    public static void prepareForMatch(VolleyballITeam team) {
        prepareForMatch(team, 10);
    }

    public static void printTotalScores(VolleyballITeam team1, double team1Scores, VolleyballITeam team2, double team2Scores) {
        printSeparator();
        System.out.println("Total scores: " + StringFormatting.formatToDecimalPoints(2, team1Scores + team2Scores));
        printSeparator(25);
        printScores(team1, team1Scores);
        printScores(team2, team2Scores);
        printSeparator(25);
    }

    public static void printScores(VolleyballITeam team, double scores) {
        System.out.println("[" + team.getTeamName() + "] : " + StringFormatting.formatToDecimalPoints(2, scores));
    }

    public static void printWinner(VolleyballITeam team) {
        printSeparator();
        System.out.println("Team [" + team.getTeamName() + "] won the game.");
        System.out.println("All players: " + team.getPlayerNamesCommaSeparated() + " are celebrating. ");
        printSeparator();
    }

    public static void printSeparator() {
        printSeparator(80);
    }

    public static void printSeparator(int numberOfSymbols) {
        System.out.println("*".repeat(numberOfSymbols));
    }

    public static double getTeamScores(VolleyballITeam team) {
        double teamAvgScore = team.calculateStrength();
        int teamRandomNumber = new Random().nextInt(1, 26);
        return teamAvgScore + teamRandomNumber;
    }

    public static void printPlayers(VolleyballPlayer[] team) {
        // System.out.println(Arrays.toString(bgTeamPlayers)); - is much harder to read
        for (VolleyballPlayer player : team) {
            System.out.println(player);
        }
    }

    public static VolleyballPlayer[] generateListOfTeamPlayers(String[] playerNames) {
        VolleyballPlayer[] volleyballPlayers = new VolleyballPlayer[VolleyballITeam.NUMBER_OF_PLAYERS];

        List<String> names = new ArrayList<>(List.of(playerNames));
        for (String playerName : names) {
            int age = new Random().nextInt(18, 35);
            int position = names.indexOf(playerName) + 1;
            int skill = new Random().nextInt(1, VolleyballPlayer.MAX_SKILLS_SCORE + 1);
            int condition = new Random().nextInt(1, VolleyballPlayer.MAX_CONDITION_SCORE + 1);

            VolleyballPlayer currentPlayer = new VolleyballPlayer(playerName, age, position, skill, condition);

            // Add the player to an array
            volleyballPlayers[position - 1] = currentPlayer;
        }
        return volleyballPlayers;
    }
}
