package Task2.Homework10;


import java.util.Arrays;

public class VolleyballITeam implements ITeam {
    public static final int NUMBER_OF_PLAYERS = 6;
    private String teamName;
    private String stadiumName;
    private String trainerName;
    private VolleyballPlayer[] players = new VolleyballPlayer[NUMBER_OF_PLAYERS];

    @Override
    public String toString() {
        return "VolleyballTeam{" +
                "teamName='" + teamName + '\'' +
                ", stadiumName='" + stadiumName + '\'' +
                ", trainerName='" + trainerName + '\'' +
                ", players=" + Arrays.toString(players) +
                '}';
    }

    public double calculateStrength() {
        int all_scores = 0;
        for (VolleyballPlayer player : players) {
            all_scores += player.getSkills();
        }
        return (double) all_scores / NUMBER_OF_PLAYERS;
    }

    public boolean canWholeTeamTrain() {
        for (VolleyballPlayer player : players) {
            if (!player.canTrain()) {
                return false;
            }
        }
        return true;
    }

    public boolean teamTraining() {
        if (canWholeTeamTrain()) {
            System.out.println("Team [" + this.getTeamName() + "] is training.");
            for (VolleyballPlayer player : players) {
                player.train(false);
            }
            return true;
        } else {
            System.out.println("At least one player of the team is not in good condition and the whole team can't play.");
            return false;
        }
    }

    public void teamRest() {
        System.out.println("Team [" + this.getTeamName() + "] is resting.");
        for (VolleyballPlayer player : players) {
            player.rest(false);
        }
    }

    public void printTeamStraight() {
        System.out.println("The average straight of team [" + teamName + "] is: "
                + StringFormatting.formatToDecimalPoints(2, this.calculateStrength()));
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public VolleyballPlayer[] getPlayers() {
        return players;
    }

    public String[] getPlayerNames() {
        String[] playerNames = new String[NUMBER_OF_PLAYERS];
        int index = 0;
        for (VolleyballPlayer player : getPlayers()) {
            playerNames[index] = player.getName();
            index++;
        }
        return playerNames;
    }

    public String getPlayerNamesCommaSeparated() {
        String playersCommaSeparated = "";
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (players[i] != null) {
                playersCommaSeparated += players[i].getName();

                // Don't add comma at the end:
                if (i != NUMBER_OF_PLAYERS - 1) {
                    playersCommaSeparated += ", ";
                }
            }
        }
        return playersCommaSeparated;
    }

    public void setPlayers(VolleyballPlayer[] players) {
        this.players = players;
    }

    public VolleyballITeam(String teamName, String stadiumName, String trainerName, VolleyballPlayer[] players) {
        this.teamName = teamName;
        this.stadiumName = stadiumName;
        this.trainerName = trainerName;
        // Check if all players are added - break if not.
        if (players.length == NUMBER_OF_PLAYERS) {
            this.players = players;
        } else {
            throw new IllegalArgumentException("Number of players should be " + NUMBER_OF_PLAYERS);
        }
    }

    public VolleyballITeam(String teamName, String stadiumName, String trainerName) {
        this.teamName = "N/A";
        this.stadiumName = "N/A";
        this.trainerName = "N/A";
    }

    // Initialize with the default values
    public VolleyballITeam() {
    }
}
