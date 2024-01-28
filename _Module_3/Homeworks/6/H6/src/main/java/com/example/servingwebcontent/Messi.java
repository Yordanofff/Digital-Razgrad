package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Messi {

    @GetMapping("/mesi")
    public String mesi() {
        return "mesi";
    }

    @GetMapping("/mesi/goals")
    public String mesiGoals(
            @RequestParam(
                    name = "name", required = false, defaultValue = "World"
            ) String name, Model model) {
        model.addAttribute("name", name);
        return "greeting_file";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/national-teams")
    public String getNationalTeamInfo(@RequestParam(name = "team") String team, Model model) {
        TeamInfo teamInfo = getTeamInfo(team);

        model.addAttribute("teamInfo", teamInfo);

        return "nationalTeamInfo";
    }

    private TeamInfo getTeamInfo(String countryCode) {
        // Get data from DB emulator
//        System.out.printf(countryCode);
        if (countryCode.equalsIgnoreCase("bg")) {
            return new TeamInfo("Bulgaria", "Todor Zhivkov", "Vasil Levski National Stadium", "15th");
        } else if (countryCode.equalsIgnoreCase("fr")) {
            return new TeamInfo("United Kingdom", "Gareth Southgate", "Wembley Stadium", "4th");
        } else if (countryCode.equalsIgnoreCase("uk")) {
            return new TeamInfo("France", "Didier Deschamps", "Stade de France", "7th");
        }
        return new TeamInfo(countryCode, "Dummy Coach", "Dummy Stadium", "Dummy Ranking");
    }

}
