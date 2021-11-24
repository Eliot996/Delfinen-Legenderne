package domain.member;

import java.time.LocalDate;

public class Competitor extends Member{
    public Competitor(int memberNumber,
                      boolean isPassiveMember,
                      String name,
                      LocalDate dateOfBirth,
                      String phoneNumber) {
        super(memberNumber, isPassiveMember, name, dateOfBirth, phoneNumber);
    }
}
