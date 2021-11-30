package domain.team;

import domain.Discipline;
import domain.member.Competitor;
import domain.member.Member;
import domain.member.Trainer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public String getInfo(int teamIndex) {
        Team team = teams.get(teamIndex);
        return team.toString();
    }

    //TODO: fix under
    /*public Team getTeamFromIndex(int teamIntex) {
        int teamIndex;

        for (Team team: teams) {
            if (team.getTe.equals(teamIndex)){
                return (Trainer) team;
            }
        }
        return null;
    }*/

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
    }*/ // getTeam(String name) - skal bruges til at finde navn i fil for et hold.

    //TODO: fix under.
       // edit teams
    public void editTeam(int teamIndex, String what, String to) {
        Team team = teams.get(teamIndex);

        switch (what){
            case "name" -> team.setName(to);
            case "description" -> team.setDescription(to);
            //case "trainers" -> team.addTrainer(); TODO: skal finde træner på indexNummer.
        }

       // if (team instanceof Trainer trainers){
            switch (what) {
                //case "add trainer" -> trainers.ad
                        //competitor.addDisciplines(Discipline.valueOf(to.toUpperCase()));
               // case "remove trainer" -> team.removeTrainer
                      //  competitor.removeDisciplines(Discipline.valueOf(to.toUpperCase()));
            }
        }
    }

