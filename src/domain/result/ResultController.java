package domain.result;

import domain.Discipline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ResultController {

    public void addCompetition (String competitionName,
                                String competitionAdress,
                                String dateOfCompetetition,
                                String competitionDiscipline) {

        addCompetition(new Competition(competitionName, competitionAdress, getDateOfString(dateOfCompetetition),competitionDiscipline));
    }

    public List<Competition> getCompetetions() {
        return competitions;
    }

   public void deleteCompetition(){
        System.out.println("Vælg hvilket stævne du ønsker at slette ud fra listen herunder\n" + getCompetetions());
        System.out.println("Indtast det indekset på det stævne du ønsker at slette");
            competitions.remove(competitions.get());
        }

    private ArrayList<Competition> competitions = new ArrayList<>();

    public void addCompetition(Competition competition){
        competitions.add(competition);
    }

    public void deleteCompetetion(Competition competition){
        competitions.remove(competition);
    }

    public LocalDateTime getDateOfString(String dateAndTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(dateAndTime.trim(), formatter);
    }

    public Discipline getDisciplineToString(String discipline){
        return Discipline.valueOf();
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
    }

