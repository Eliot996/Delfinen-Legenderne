package domain.result;

import domain.Discipline;
import domain.member.Competitor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Result {
    private UUID id;
    private Competitor competitor;
    private LocalTime time;
    private LocalDate date;
    private Competition competition;
    private Discipline discipline;

    public Result(Competitor competitor, LocalTime time, LocalDate date, Competition competition, Discipline discipline) {
        this.id = UUID.randomUUID();
        this.competitor = competitor;
        this.time = time;
        this.date = date;
        this.competition = competition;
        this.discipline = discipline;
    }

    public Result(String csv) {
        String[] elemets = csv.split(";");

        this.id = UUID.fromString(elemets[0]);
        this.time = LocalTime.parse(elemets[1]);
        this.date = LocalDate.parse(elemets[2]);
        //this.competition = Competition; todo connect to competion
        this.discipline = Discipline.valueOf(elemets[4]);
    }

    public String toCSV() {
        return id.toString() + ";"
                + competitor + ";"
                + time.toString() + ";"
                + date.toString() + ";"
                + competition.getId() + ";"
                + discipline;
    }
}
