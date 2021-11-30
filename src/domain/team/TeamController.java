package domain.team;

import domain.Discipline;
import domain.member.Competitor;
import domain.member.Member;
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

    public void deleteTeam(int teamIndex) {
        teams.remove(teams.get(teamIndex));
    }

    public List<Team> getTeams(){
        return teams;
    }
    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public String getStringOfTeams() {
           int index = 1;
           StringBuilder sb = new StringBuilder();

           for (Team team : teams) {
               sb.append(index).append(". ").append(team.simplePrint()).append('\n');
               index++;
           }
           return sb.toString();
    }
    public int getAmountOfTeams(){
        return teams.size();
    }
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        for (Team team : teams) {
            sb.append(team.toCSV()).append('\n');
        }
        return sb.toString();
    }
       /* public Team getTeam(String name) {
        for (Team team : teams) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        return null;
    }*/ // getMember(String name) - skal bruges til at finde navn i fil.

       // edit teams
    public void editMember(int memberIndex, String what, String to) {
        Member member = members.get(memberIndex);

        switch (what){
            case "name" -> member.setName(to);
            case "isPassive" -> member.setPassiveMember(Boolean.parseBoolean(to));
            case "dateOfBirth" -> member.setDateOfBirth(getDateFromString(to));
            case "phoneNumber" -> member.setPhoneNumber(to);
            case "email" -> member.setEmail(to);
        }

        if (member instanceof Competitor competitor){
            switch (what) {
                case "add discipline" -> competitor.addDisciplines(Discipline.valueOf(to.toUpperCase()));
                case "remove discipline" -> competitor.removeDisciplines(Discipline.valueOf(to.toUpperCase()));
            }
        }
    }
}
