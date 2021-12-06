package domain.result;

import domain.Discipline;
import domain.User;
import domain.member.Competitor;
import domain.member.MemberController;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResultController {

    MemberController memberController;
    ArrayList<Result> results = new ArrayList<>();

    public ResultController(MemberController memberController) {
        this.memberController = memberController;
    }

    // **************
    // *
    // * Results
    // *
    // **************

    public void addResult(int memberIndex,
                          LocalTime time,
                          LocalDate date,
                          Competition competition,
                          Discipline discipline){
        results.add(new Result((Competitor) memberController.getMember(memberIndex),
                time,
                date,
                competition,
                discipline));
    }

    private void addResultFromCSV(String csv) { // might work, but properly doesn't
        String[] elemets = csv.split(";");

        results.add(new Result(
                UUID.fromString(elemets[0]),
                memberController.getCompetitor(UUID.fromString(elemets[1])),
                LocalTime.parse(elemets[2]),
                LocalDate.parse(elemets[3]),
                getCompetitionFromID(UUID.fromString(elemets[4])),
                Discipline.valueOf(elemets[5])));
    }


    // **************
    // *
    // * Competition
    // *
    // **************

    public void addCompetition (String competitionName,
                                String competitionAdress,
                                String dateOfCompetetition,
                                Discipline competitionDiscipline) {

        addCompetition(new Competition(competitionName, competitionAdress, getDateOfString(dateOfCompetetition),competitionDiscipline));
    }

    public List<Competition> getCompetetions() {
        return competitions;
    }

    public void deleteCompetition(int competitionIndex) {
        competitions.remove(competitionIndex);
    }


    private ArrayList<Competition> competitions = new ArrayList<>();

    public void addCompetition(Competition competition) {
        competitions.add(competition);
    }

    public void deleteCompetetion(Competition competition) {
        competitions.remove(competition);
    }

    public void editCompetition(int competitionIndex, String what, String to) {
        Competition competition = competitions.get(competitionIndex);

        switch (what) {
            case "competitionName" -> competition.setCompetitionName(to);
            case "competitionAdress" -> competition.setCompetitionAdress(to);
            case "dateOfCompetition" -> competition.setDateOfCompetetition(getDateOfString(to));
            case "competitionDiscipline" -> competition.setCompetitionDiscipline((Discipline.valueOf(competition.toString())));

        }
    }

    public Competition getCompetitionFromID(UUID id) {
        for (Competition competition : competitions) {
            if (competition.getId().equals(id)) {
                return competition;
            }
        }
        return null;
    }

    public int getAmountOfCompetition() {
        return competitions.size();
    }

    public LocalDateTime getDateOfString(String dateAndTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(dateAndTime.trim(), formatter);
    }

    public String getStringOfCompetitions() {
            int index = 1;
            StringBuilder sb = new StringBuilder();

            for (Competition competition : competitions) {
                sb.append(index).append(". ").append(competition.simplePrint()).append('\n');
                index++;
            }

            return sb.toString();
        }

    public static List<Competition> getCompetitions() {
        ArrayList<Competition> competitions = new ArrayList<>();
        for (Competition competition : competitions)
            if (competition instanceof Competition) {
                competitions.add(competition);
            }
        return competitions;
    }

    public String competitionsToCSV(){
        StringBuilder sb = new StringBuilder();

        for (Competition competition :
                getCompetitions()) {
            sb.append(competition.toCSV()).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public String resultToCSV(){
        StringBuilder sb = new StringBuilder();

        for (Result result :
               getResults()) {
            sb.append(result.toCSV()).append("\n");
        }
        return sb.toString();
    }
}

