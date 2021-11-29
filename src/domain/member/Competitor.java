package domain.member;

import domain.Discipline;

import java.time.LocalDate;
import java.util.List;

public class Competitor extends Member{

    private List<Discipline> disciplines;

    public Competitor(boolean isPassiveMember,
                      String name,
                      LocalDate dateOfBirth,
                      String phoneNumber,
                      String email,
                      List<Discipline> disciplines) {
        super(isPassiveMember, name, dateOfBirth, phoneNumber, email);
        this.disciplines = disciplines;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
}
