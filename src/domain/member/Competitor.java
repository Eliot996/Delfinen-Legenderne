package domain.member;

import domain.Discipline;

import java.time.LocalDate;
import java.util.List;

public class Competitor extends Member {

    private List<Discipline> disciplines;

    public Competitor(boolean isPassiveMember,
                      String name,
                      LocalDate dateOfBirth,
                      String phoneNumber,
                      String email) {
        super(isPassiveMember, name, dateOfBirth, phoneNumber, email);
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
