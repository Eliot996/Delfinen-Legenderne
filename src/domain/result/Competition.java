package domain.result;

import domain.Discipline;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//@author Oliver
public class Competition {

    private final UUID id;
    private String competitionName;
    private String competitionAddress;
    private LocalDateTime dateOfCompetition;

    List<Discipline> disciplines = new ArrayList<>();

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
        String discipline = disciplines.stream().map(this::disciplineToString)
                .collect(Collectors.joining(", "));
        return "Stævnenavn: " + competitionName + " Adresse: " + competitionAddress +
                " Dato og tid: " + dateOfCompetition + " Discipliner: " + discipline;
    }



    //Getter og Setter af Attributter

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public void setCompetitionAddress(String competitionAddress) {
        this.competitionAddress = competitionAddress;
    }

    public void setDateOfCompetition(LocalDateTime dateOfCompetition) {
        this.dateOfCompetition = dateOfCompetition;
    }

    public String disciplineToString(Discipline discipline) {
        String disciplineToPrint;
        switch (discipline) {
            case BREASTSTROKE -> disciplineToPrint = "Brystsvømning";
            case CRAWL -> disciplineToPrint = "Crawl";
            case BACKCRAWL -> disciplineToPrint = "Rygcrawl";
            case BUTTERFLY -> disciplineToPrint = "Butterfly";
            default -> disciplineToPrint = "Disciplin er ikke blevet defineret";
        }
        return disciplineToPrint;
    }

    public UUID getId() {
        return id;
    }

    public String toCSV() {
        StringBuilder stringOfDisciplines = new StringBuilder();

        if (disciplines.size() > 0) {

            stringOfDisciplines.append(disciplines.get(0));

            if (disciplines.size() > 1){
                for (int i = 1; i < disciplines.size(); i++) {
                    stringOfDisciplines.append(':').append(disciplines.get(i).toString());
                }
            }
        } else {
          stringOfDisciplines.append("null");
        }

        return id + ";" +
                competitionName + ";" +
                competitionAddress + ";" +
                dateOfCompetition + ";" +
                stringOfDisciplines;
    }

    public void addDisciplines(Discipline discipline) {
        this.disciplines.add(discipline);
    }

    public void removeDisciplines(Discipline discipline) {
        this.disciplines.remove(discipline);
    }
}
