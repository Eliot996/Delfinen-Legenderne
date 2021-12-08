package domain.result;

import domain.Discipline;
import domain.member.Competitor;
import domain.member.MemberController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

// @author Sofia, Mathias og Oliver

public class ResultController {

    private final MemberController memberController;
    private final ArrayList<Result> results = new ArrayList<>();
    private final ArrayList<Competition> competitions = new ArrayList<>();

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
                          int competitionIndex,
                          Discipline discipline) {
        if (competitionIndex == -1) {
            results.add(new Result((Competitor) memberController.getMember(memberIndex),
                    time,
                    date,
                    null,
                    discipline));
        } else {
            results.add(new Result((Competitor) memberController.getMember(memberIndex),
                    time,
                    date,
                    competitions.get(competitionIndex),
                    discipline));
        }
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public int getAmountOfResults() {
        return results.size();
    }

    public void deleteResult(int resultIndex) {
        results.remove(results.get(resultIndex));
    }

    public void editResult(int resultIndex, String what, String to) {
        Result result = results.get(resultIndex);

        switch (what) {
            case "competitor" -> result.setCompetitor(memberController.getCompetitor(Integer.parseInt(to)));
            case "time" -> result.setTime(LocalTime.parse(to));
            case "date" -> result.setDate(LocalDate.parse(to));
            case "competition" -> result.setCompetition(getCompetition(Integer.parseInt(to)));
            case "disciplin" -> result.setDiscipline(Discipline.valueOf(to));
        }
    }

    public void initResults(List<String> competitors_resultFromFile) {
        for (String resultsString: competitors_resultFromFile) {
            String[] elements = resultsString.split(";");


            results.add(new Result(UUID.fromString(elements[0]),
                    memberController.getCompetitor(UUID.fromString(elements[1])),
                    LocalTime.parse(elements[2]),
                    LocalDate.parse(elements[3]),
                    elements[4].equals("null") ? null : getCompetitionFromID(UUID.fromString(elements[4])),
                    Discipline.valueOf(elements[5])));
        }
    }

    public String getStringOfResults() {
        int index = 1;
        StringBuilder sb = new StringBuilder();

        for(Result result: results) {
            sb.append(index).append(". ").append(result.simplePrint()).append('\n');
            index++;
        }

        return sb.toString();
    }

    public Result getResult(int index) {
        return results.get(index);
    }

    // **************
    // *
    // * Competition
    // *
    // **************

    public void addCompetition(String competitionName,
                               String competitionAdress,
                               LocalDateTime dateOfCompetition,
                               Discipline competitionDiscipline) {

        addCompetition(new Competition(competitionName, competitionAdress, dateOfCompetition));
        competitions.get(competitions.size()-1).addDisciplines(competitionDiscipline);
    }

    public void deleteCompetition(int competitionIndex) {
        competitions.remove(competitionIndex);
    }

    public void addCompetition(Competition competition) {
        competitions.add(competition);
    }

    public void deleteCompetition(Competition competition) {
        competitions.remove(competition);
    }

    public void editCompetition(int competitionIndex, String what, String to) {
        Competition competition = competitions.get(competitionIndex);

        switch (what) {
            case "competitionName" -> competition.setCompetitionName(to);
            case "competitionAdress" -> competition.setCompetitionAddress(to);
            case "dateOfCompetition" -> competition.setDateOfCompetition(getDateOfString(to));
            case "removeDiscipline" -> competition.removeDisciplines(Discipline.valueOf(to.toUpperCase()));
            case "addDiscipline" -> competition.addDisciplines(Discipline.valueOf(to.toUpperCase()));

        }
    }

    public Competition getCompetitionFromID(UUID id) {
        for(Competition competition: competitions) {
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

        for(Competition competition: competitions) {
            sb.append(index).append(". ").append(competition.simplePrint()).append('\n');
            index++;
        }

        return sb.toString();
    }


    public String getInfo(int resultIndex) {
        Result result = results.get(resultIndex);
        return result.toString();
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public String competitionsToCSV() {
        StringBuilder sb = new StringBuilder();

        for(Competition competition: getCompetitions()) {
            sb.append(competition.toCSV()).append("\n");
        }
        return sb.toString();
    }

    public Competition getCompetition(int competitionIndex) {
        return competitions.get(competitionIndex);
    }

    public String resultToCSV() {
        StringBuilder sb = new StringBuilder();

        for(Result result:
                getResults()) {
            sb.append(result.toCSV()).append("\n");
        }
        return sb.toString();
    }

    public void initCompetitions(List<String> competitionsFromFile) {
        for(String competitionString: competitionsFromFile) {
            competitions.add(new Competition(competitionString));
            //TODO add disciplins
        }
    }

    public String getTopFive(Discipline discipline, String ageGroup) {
        ArrayList<Result> list = new ArrayList<>();
        for (Result result : results) {
            if (result.getDiscipline().equals(discipline)){
                if (ageGroup.equals("junior")
                        && !result.getCompetitor().isSenior()
                        && !result.getCompetitor().isPensioner()) {
                    list.add(result);
                } else if (ageGroup.equals("senior")
                        && result.getCompetitor().isSenior()) {
                    list.add(result);
                }
                else if (ageGroup.equals("pensioner")
                        && result.getCompetitor().isPensioner()) {
                    list.add(result);
                }
            }
        }
        list.sort(Comparator.comparing(Result::getTime));

        ArrayList<Result> finalList = new ArrayList();
        boolean existingInList = false;

        for (Result result : list) {
            if (finalList.size() < 5) {
                for (Result finalResult : finalList) {
                    if (finalResult.getCompetitor() == result.getCompetitor()) {
                        existingInList = true;
                        break;
                    }
                }
                if (!existingInList) {
                    finalList.add(result);
                }
                existingInList = false;
            }
        }

        StringBuilder sb = new StringBuilder();

        if (finalList.size() > 0) {
            for (Result result : finalList) {
                sb.append("Tid: ").append(result.getTime()).append('\n');
                sb.append(result.getCompetitor().simplePrint()).append('\n');
            }
        }

        return sb.toString();
    }
}

