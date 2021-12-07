package domain.result;

import domain.Discipline;
import domain.Roles;
import jdk.dynalink.beans.StaticClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@author Oliver
public class Competition {

    private UUID id;
    private String competitionName;
    private String competitionAdress;
    private LocalDateTime dateOfCompetetition;
    private Discipline competitionDiscipline;

    private List<Discipline> disciplines = new ArrayList<>();

    //Konstruktør
    public Competition(String competitionName, String competitionAdress,
                       LocalDateTime dateOfCompetetition, Discipline competitionDiscipline) {
        this.id = UUID.randomUUID();
        this.competitionName = competitionName;
        this.competitionAdress = competitionAdress;
        this.dateOfCompetetition = dateOfCompetetition;
        this.competitionDiscipline = Discipline.valueOf(competitionDiscipline.toString());
    }
//@Sofia og Mathias
    //CSV konstruktør
    public Competition(String competitionString) {
        // splits the CSV
        String[] elements = competitionString.split(";");

        //assigns base on position
        this.id = UUID.fromString(elements[0]);
        this.competitionName = elements[1];
        this.competitionAdress = elements[2];
        this.dateOfCompetetition = LocalDateTime.parse(elements[3]);
        this.competitionDiscipline = Discipline.valueOf(elements[4]);
    }

    public String simplePrint() {
        return "Stævnenavn: " + competitionName + " Adresse: " + competitionAdress +
                " Dato og tid: " + dateOfCompetetition + " Disciplin: " + competitionDiscipline;
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

    public LocalDateTime getDateOfCompetetition(LocalDateTime dateOfCompetetition) {
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
        String disciplineToPrint;
        switch (competitionDiscipline) {
            case BREASTSTROKE -> disciplineToPrint = "Brystsvømning";
            case CRAWL -> disciplineToPrint = "Crawl";
            case BACKCRAWL -> disciplineToPrint = "Ryg crawl";
            case BUTTERFLY -> disciplineToPrint = "Butterfly";
            default -> disciplineToPrint = "Disciplin er ikke blevet defineret";
        }
        return disciplineToPrint;
    }

    public LocalDateTime getDateAndTimeOfString(String dateAndTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(dateAndTime.trim(), formatter);
    }

    public UUID getId() {
        return id;
    }

    public String toCSV() {
        return id + ";" +
                competitionName + ";" +
                competitionAdress + ";" +
                dateOfCompetetition + ";" +
                competitionDiscipline;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public void addDisciplines(Discipline discipline) {
        this.disciplines.add(discipline);
    }

    public void removeDisciplines(Discipline discipline) {
        this.disciplines.remove(discipline);
    }
}
