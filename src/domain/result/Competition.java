package domain.result;

import domain.Discipline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@author Oliver
public class Competition {

    private final UUID id;
    private String competitionName;
    private String competitionAddress;
    private LocalDateTime dateOfCompetition;

    private List<Discipline> disciplines = new ArrayList<>();

    //Konstruktør
    public Competition(String competitionName, String competitionAddress,
                       LocalDateTime dateOfCompetition) {
        this.id = UUID.randomUUID();
        this.competitionName = competitionName;
        this.competitionAddress = competitionAddress;
        this.dateOfCompetition = dateOfCompetition;

    }

    //@Sofia og Mathias
    //CSV konstruktør
    public Competition(String competitionString) {
        // splits the CSV
        String[] elements = competitionString.split(";");

        //assigns base on position
        this.id = UUID.fromString(elements[0]);
        this.competitionName = elements[1];
        this.competitionAddress = elements[2];
        this.dateOfCompetition = LocalDateTime.parse(elements[3]);
    }

    public String simplePrint() {
        return "Stævnenavn: " + competitionName + " Adresse: " + competitionAddress +
                " Dato og tid: " + dateOfCompetition + " Discipliner: "; // todo fix and add + competitionDiscipline;
    }

    //Getter og Setter af Attributter
    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionAddress() {
        return competitionAddress;
    }

    public void setCompetitionAddress(String competitionAddress) {
        this.competitionAddress = competitionAddress;
    }

    public LocalDateTime getDateOfCompetition(LocalDateTime dateOfCompetition) {
        return dateOfCompetition;
    }

    public void setDateOfCompetition(LocalDateTime dateOfCompetition) {
        this.dateOfCompetition = dateOfCompetition;
    }

    public String disicplineToString(Discipline discipline) {
        String disciplineToPrint;
        switch (discipline) {
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
                competitionAddress + ";" +
                dateOfCompetition + ";"
                ; // add disciplines
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
