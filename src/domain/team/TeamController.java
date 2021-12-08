package domain.team;

import domain.member.Member;
import domain.member.MemberController;
import domain.member.Trainer;

import java.util.ArrayList;
import java.util.List;

// @Author Sofia
public class TeamController {
    private final MemberController memberController;
    private final ArrayList<Team> teams = new ArrayList<>();

    public TeamController(MemberController memberController) {
        this.memberController = memberController;
    }

    public void addTeam(String name,
                        String description) {
        addTeam(new Team(name, description));
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

    public List<Team> getTeams() {
        return teams;
    }

    public String getStringOfTeams() {
        return getStringOfTeams(teams);
    }

    public String getStringOfTeams(List<Team> listOfTeams) {
        int index = 1;
        StringBuilder sb = new StringBuilder();

        for(Team team: listOfTeams) {
            sb.append(index).append(". ").append(team.simplePrint()).append('\n');
            index++;
        }
        return sb.toString();
    }

    public String getStringOfTeams(Member excludeMember) {
        int index = 1;
        StringBuilder sb = new StringBuilder();

        for(Team team: teams) {
            if (!team.hasMember(excludeMember)) {
                sb.append(index).append(". ").append(team.simplePrint()).append('\n');
            }
            index++;
        }
        return sb.toString();
    }

    public int getAmountOfTeams() {
        return teams.size();
    }

    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        for(Team team: teams) {
            sb.append(team.toCSV()).append('\n');
        }
        return sb.toString();
    }

    //*********************
    // *
    // * edit team
    // *
    // ********************

    public void editTeam(int teamIndex, String what, String to) {
        Team team = teams.get(teamIndex);

        switch (what) {
            case "name" -> team.setName(to);
            case "description" -> team.setDescription(to);
            case "add trainers" -> team.addTrainer((Trainer) memberController.getMember(Integer.parseInt(to)));
            case "remove trainers" -> team.removeTrainer(Integer.parseInt(to));
            case "add member" -> team.addMember(memberController.getMember(Integer.parseInt(to)));
            case "remove member" -> team.removeMember(Integer.parseInt(to));
        }
    }

    public Team getTeamFromIndex(int teamIntex) {
        return teams.get(teamIntex);
    }

    public String getTeamsWithMember(Member member) {
        ArrayList<Team> teamsWithMember = new ArrayList<>();

        for(Team team: teams) {
            if (team.hasMember(member)) {
                teamsWithMember.add(team);
            }
        }

        return getStringOfTeams(teamsWithMember);
    }

    public void addMemberToTeam(Member member, int teamIndex) {
        teams.get(teamIndex).addMember(member);
    }

    public void removeMemberToTeam(Member member, int teamIndex) {
        teams.get(teamIndex).removeMember(member);
    }

    public String getTrainersOnTeam(int teamIndex) {
        return teams.get(teamIndex).getStringOfTrainers();
    }

    public String getMembersOnTeam(int teamIndex) {
        return teams.get(teamIndex).getStringOfMembers();
    }

    public String teamsToCSV() {
        StringBuilder sb = new StringBuilder();

        for(Team team: teams) {
            sb.append(team.toCSV()).append("\n");
        }
        return sb.toString();
    }

    public void initTeams(List<String> teamsFromFile) {
        for(String teamString: teamsFromFile) {
            teams.add(new Team(teamString));
            Team workingTeam = teams.get(teams.size() - 1);
            String[] parts = teamString.split(";");

            if (parts.length >= 3) {
                if (parts[2].contains(":")) {
                    String[] elements = parts[2].split(":");
                    for(String element: elements) {
                        workingTeam.addTrainer(memberController.getTrainerFromUUID(element));
                    }
                } else if (!parts[2].equals("")) {
                    workingTeam.addTrainer(memberController.getTrainerFromUUID(parts[2]));
                }
            }
            if (parts.length >= 4) {
                if (parts[3].contains(":")) {
                    String[] elements = parts[3].split(":");
                    for(String element: elements) {
                        workingTeam.addMember(memberController.getMemberFromUUID(element));
                    }
                } else if (!parts[3].equals("")) {
                    workingTeam.addMember(memberController.getMemberFromUUID(parts[3]));
                }
            }
        }
    }
}
