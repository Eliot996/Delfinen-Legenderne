package domain.result;

import domain.Discipline;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Result {
    private UUID id;
    private LocalTime time;
    private LocalDate date;
    private Competition competition;
    private Discipline discipline;

    public Result(LocalTime time, LocalDate date, Discipline discipline) {
        this.time = time;
        this.date = date;
        this.discipline = discipline;
    }

    public Result(UUID id, LocalTime time, LocalDate date, Discipline discipline) {
        this.id = id;
        this.time = time;
        this.date = date;
        this.discipline = discipline;
    }
}
