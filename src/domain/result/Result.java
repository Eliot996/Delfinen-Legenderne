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

    public Result(UUID id, Competitor competitor, LocalTime time, LocalDate date, Competition competition, Discipline discipline) {
        this.id = id;
        this.competitor = competitor;
        this.time = time;
        this.date = date;
        this.competition = competition;
        this.discipline = discipline;
    }

    public String toCSV() {
        return id.toString() + ";"
                + competitor.getMemberID() + ";"
                + time.toString() + ";"
                + date.toString() + ";"
                + competition.getId() + ";"
                + discipline;
    }
}
