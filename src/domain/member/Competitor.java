package domain.member;

import domain.Discipline;

import java.time.LocalDate;
import java.util.List;

public class Competitor extends Member{

    private List<Discipline> disciplines;

    public Competitor(int memberNumber,
                      boolean isPassiveMember,
                      String name,
                      LocalDate dateOfBirth,
                      String phoneNumber, List<Discipline> disciplines) {
        super(memberNumber, isPassiveMember, name, dateOfBirth, phoneNumber);
        this.disciplines = disciplines;
    }
}
