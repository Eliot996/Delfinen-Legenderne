package domain.member;

import domain.Discipline;
import domain.team.TeamController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@author Sofia & Mathias
// TODO: 26/11/2021 remove all ui elements from the class

public class MemberController {
    TeamController teamController;

    public void setTeamController(TeamController teamController) {
        this.teamController = teamController;
    }

    // to add a member to the database parses date(of format "dd-MM-yyyy")
    public void addMember(boolean isPassiveMember,
                          String name,
                          LocalDate dateOfBirth,
                          String phoneNumber,
                          String email) {


        addMember(new Member(isPassiveMember, name, dateOfBirth, phoneNumber, email));
    }

    // to add a competitor to the database parses date(of format "dd-MM-yyyy") and checks for uniqueness of membernumber
    public void addCompetitor(boolean isPassiveMember,
                              String name,
                              LocalDate dateOfBirth,
                              String phoneNumber,
                              String email) {


        addMember(new Competitor(isPassiveMember, name, dateOfBirth, phoneNumber, email));
    }

    public void addCompetitor(int memberIndex) {
        Member member = members.get(memberIndex);

        addMember(new Competitor(member.isPassiveMember(), member.getName(), member.getDateOfBirth(), member.getPhoneNumber(), member.getEmail()));

        members.remove(member);
    }

    public void addCompetitor(Competitor competitor) {
        members.add(competitor);
    }

    // to add a trainer to the database parses date(of format "dd-MM-yyyy") and checks for uniqueness of membernumber
    public void addTrainer(boolean isPassiveMember,
                           String name,
                           LocalDate dateOfBirth,
                           String phoneNumber,
                           String email) {

        addMember(new Trainer(isPassiveMember, name, dateOfBirth, phoneNumber, email));
    }

    public void addTrainer(int memberIndex) {
        Member member = members.get(memberIndex);

        addMember(new Trainer(member.isPassiveMember(), member.getName(), member.getDateOfBirth(), member.getPhoneNumber(), member.getEmail()));

        members.remove(member);
    }

    public void addTrainer(Trainer trainer) {
        members.add(trainer);
    }


    public List<Member> getMembers() {
        return members;
    }

    public List<Trainer> getTrainers() {
        ArrayList<Trainer> trainers = new ArrayList<>();
        for(Member member: members)
            if (member instanceof Trainer trainer) {
                trainers.add(trainer);
            }
        return trainers;
    }

    public List<Competitor> getCompetitors() {
        ArrayList<Competitor> competitors = new ArrayList<>();
        for(Member member: members)
            if (member instanceof Competitor competitor) {
                competitors.add(competitor);
            }
        return competitors;
    }

    // Method to edit a member object in the members list,
    // takes the index of the member to change, and a string of what to change, as well as another string of
    // what to change it to
    public void editMember(int memberIndex, String what, String to) {
        Member member = members.get(memberIndex);

        switch (what) {
            case "name" -> member.setName(to);
            case "isPassive" -> member.setPassiveMember(Boolean.parseBoolean(to));
            case "dateOfBirth" -> member.setDateOfBirth(getDateFromString(to));
            case "phoneNumber" -> member.setPhoneNumber(to);
            case "email" -> member.setEmail(to);
            case "add to team" -> teamController.addMemberToTeam(member, Integer.parseInt(to));
            case "remove from team" -> teamController.removeMemberToTeam(member, Integer.parseInt(to));
        }

        if (member instanceof Competitor competitor) {
            switch (what) {
                case "add discipline" -> competitor.addDisciplines(Discipline.valueOf(to.toUpperCase()));
                case "remove discipline" -> competitor.removeDisciplines(Discipline.valueOf(to.toUpperCase()));
            }
        }
    }

    public String getStringOfMembers() {
        int index = 1;
        StringBuilder sb = new StringBuilder();

        for(Member member: members) {
            sb.append(index).
                    append(". ").
                    append(member.simplePrint()).
                    append('\n');
            index++;
        }

        return sb.toString();
    }

    public String getStringOfTrainers() {
        int index = 1;
        StringBuilder sb = new StringBuilder();

        for(Member member: members) {
            if (member instanceof Trainer) {
                sb.append(index).append(". ").append(member.simplePrint()).append('\n');
            }
            index++;
        }
        return sb.toString();
    }

    public int getAmountOfMembers() {
        return members.size();
    }

    // methods from MemberDatabase
    // @author Mathias
    private ArrayList<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        members.add(member);
    }

    public void deleteMember(int memberIndex) {
        members.remove(members.get(memberIndex));
    }

    public Member getMember(int memberIndex) {
        return members.get(memberIndex);
    }

    public String toCSV() {
        StringBuilder sb = new StringBuilder();

        for(Member member: members) {
            sb.append(member.toCSV()).append('\n');
        }

        return sb.toString();
    }

    public LocalDate getDateFromString(String date) {
        // make a pattern to parse the given dates from
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // get the dates from the user and parse
        return LocalDate.parse(date.trim(), formatter);
    }

    public String getInfo(int memberIndex) {
        Member member = members.get(memberIndex);

        return member.toString() + "Medlem af hold:\n " + teamController.getTeamsWithMember(member);
    }

    public Member getMemberFromUUID(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        for(Member member: members) {
            if (member.getMemberID().equals(uuid)) {
                return member;
            }
        }
        return null;
    }

    public Trainer getTrainerFromUUID(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        for(Member member: members) {
            if (member.getMemberID().equals(uuid) && member instanceof Trainer trainer) {
                return trainer;
            }
        }
        return null;
    }

    public String memberToCSV() {
        StringBuilder sb = new StringBuilder();

        for(Member member: members) {
            if (!(member instanceof Competitor) && !(member instanceof Trainer))
                sb.append(member.toCSV()).append("\n");
        }
        return sb.toString();
    }

    public String trainersToCSV() {
        StringBuilder sb = new StringBuilder();

        for(Member member: members) {
            if (member instanceof Trainer trainer)
                sb.append(trainer.toCSV()).append("\n");
        }
        return sb.toString();
    }

    public String competitorsToCSV() {
        StringBuilder sb = new StringBuilder();

        for(Member member: members) {
            if (member instanceof Competitor competitor)
                sb.append(competitor.toCSV()).append("\n");
        }
        return sb.toString();
    }

    public void initMembers(List<String> membersFromFile) {
        for(String memberString: membersFromFile) {
            members.add(new Member(memberString));
        }
    }

    public void initCompetitors(List<String> competitorsFromFile) {
        for(String competitorString: competitorsFromFile) {
            members.add(new Competitor(competitorString));
        }
    }

    public void initTrainers(List<String> trainersFromFile) {
        for(String trainerString: trainersFromFile) {
            members.add(new Trainer(trainerString));
        }
    }

    public Competitor getCompetitor(UUID id) {
        for(Member member: members) {
            if (member.getMemberID().equals(id) && member instanceof Competitor competitor) {
                return competitor;
            }
        }
        return null;
    }

    public String getStringOfCompetitors() {
        int index = 1;
        StringBuilder sb = new StringBuilder();

        for(Member member: members) {
            if (member instanceof Competitor) {
                sb.append(index).append(". ").append(member.simplePrint()).append('\n');
            }
            index++;
        }
        return sb.toString();
    }

    public Competitor getCompetitor(int memberIndex) {
        Member member = getMember(memberIndex);
        if (member instanceof Competitor) {
            return (Competitor) getMember(memberIndex);
        } else {
            return null;
        }
    }
}

