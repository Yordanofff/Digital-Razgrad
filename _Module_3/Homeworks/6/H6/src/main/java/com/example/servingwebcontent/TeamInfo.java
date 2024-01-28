package com.example.servingwebcontent;

public class TeamInfo {
    private String teamName;
    private String coach;
    private String stadium;
    private String ranking;

    public String getTeamName() {
        return teamName;
    }

    public String getCoach() {
        return coach;
    }

    public String getStadium() {
        return stadium;
    }

    public String getRanking() {
        return ranking;
    }

    public TeamInfo(String teamName, String coach, String stadium, String ranking) {
        this.teamName = teamName;
        this.coach = coach;
        this.stadium = stadium;
        this.ranking = ranking;
    }
}