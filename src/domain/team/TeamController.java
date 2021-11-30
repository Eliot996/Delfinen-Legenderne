package domain.team;

import domain.member.Trainer;
import java.util.ArrayList;
import java.util.List;

// @Author Sofia

public class TeamController {
    private ArrayList<Team> teams = new ArrayList<>();

    public void addTeam(String name,
                        String description,
                        ArrayList<Trainer> trainers) {
        addTeam(new Team(name, description, trainers));
    }

    private void addTeam(Team team) {
        teams.add(team);
    }
    public void deleteTeam(domain.team.Team team) {
        teams.remove(team);
    }

    public List<Team> getTeams(){
        return teams;
    }
    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
}
