package domain.result;

import domain.Discipline;
import domain.member.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResultController {

    public void addCompetition (String competitionName,
                                String competitionAdress,
                                LocalDateTime dateOfCompetetition,
                                Discipline competitionDiscipline) {

        addCompetition(new Competition(competitionName, competitionAdress, dateOfCompetetition,competitionDiscipline));
    }

    public List<Competition> getCompetetions() {
        return competitions;
    }

    public void deleteCompetitions(){
        
    }


    private ArrayList<Competition> competitions = new ArrayList<>();

    public void addCompetition(Competition competition){
        competitions.add(competition);
    }

    public void deleteCompetetion(Competition competition){
        competitions.remove(competition);
    }
}
