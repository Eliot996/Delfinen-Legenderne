package domain.result;

import domain.Discipline;
import domain.member.Competitor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

//@author Mathias og Sofia
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
        String competitionString = competition != null ? competition.getId().toString() : "null";

        return id.toString() + ";"
                + competitor.getMemberID() + ";"
                + time.toString() + ";"
                + date.toString() + ";"
                + competitionString + ";"
                + discipline;
    }

    public String simplePrint() {
        return "ID: " + id +
                ", Konkurrencesvømmer: " + competitor +
                ", Tid: " + time +
                ", Dato: " + date +
                ", Konkurrence: " + competition +
                ", Disciplin: " + discipline;
    }

    @Override
    public String toString() {
        return "Resultat= " +
                "Id: " + id +
                ", Konkurrencesvømmer:" + competitor +
                ", Tid: " + time +
                ", Dato: " + date +
                ", Konkurrence: " + competition +
                ", disciplin: " + discipline +
                ';';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Competitor getCompetitor() {
        return competitor;
    }

    public void setCompetitor(Competitor competitor) {
        this.competitor = competitor;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

}


