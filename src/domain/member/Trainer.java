package domain.member;

import java.time.LocalDate;

public class Trainer extends Member {

    public Trainer(boolean isPassiveMember,
                   String name,
                   LocalDate dateOfBirth,
                   String phoneNumber,
                   String email) {
        super(isPassiveMember, name, dateOfBirth, phoneNumber, email);
    }
}