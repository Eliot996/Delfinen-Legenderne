package domain.result;

import domain.Discipline;

import java.time.LocalDateTime;

//@author Oliver
public class Competition {

    private String competitionName;
    private String competitionAdress;
    private LocalDateTime dateOfCompetetition;
    private Discipline competitionDiscipline;

    //Konstruktør
    public Competition(String competitionName, String competitionAdress,
                       LocalDateTime dateOfCompetetition, String competitionDiscipline) {
        this.competitionName = competitionName;
        this.competitionAdress = competitionAdress;
        this.dateOfCompetetition = dateOfCompetetition;
        this.competitionDiscipline = competitionDiscipline;
    }

    public String simplePrint() {
        return "Stævnenavn: " + competitionName + "Adresse: " + competitionAdress +
                "Dato og tid: " + dateOfCompetetition + "Disciplin: " + competitionDiscipline;
    }

    //Getter og Setter af Attributter
    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionAdress() {
        return competitionAdress;
    }

    public void setCompetitionAdress(String competitionAdress) {
        this.competitionAdress = competitionAdress;
    }

    public LocalDateTime getDateOfCompetetition() {
        return dateOfCompetetition;
    }

    public void setDateOfCompetetition(LocalDateTime dateOfCompetetition) {
        this.dateOfCompetetition = dateOfCompetetition;
    }

    public Discipline getCompetitionDiscipline() {
        return competitionDiscipline;
    }

    public void setCompetitionDiscipline(Discipline competitionDiscipline) {
        this.competitionDiscipline = competitionDiscipline;
    }

    @Override
    public String toString() {
        return "----Stævneoplysninger----\n"+
               "Stævne: " + competitionName +
               "\nAdresse: " + competitionAdress +
               "\nDato og tidspunkt for stævnestart: " + dateOfCompetetition +
               "\nStævnediscipliner: " + competitionDiscipline + "\n";
    }
}
