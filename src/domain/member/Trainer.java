package domain.member;

import java.time.LocalDate;

public class Trainer extends Member{

    public Trainer(int memberNumber,
                   boolean isPassiveMember,
                   String name,
                   LocalDate dateOfBirth,
                   String phoneNumber,
                   String email) {
        super(memberNumber, isPassiveMember, name, dateOfBirth, phoneNumber, email);
    }
}
