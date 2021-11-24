package domain.member;

import java.time.LocalDate;

public class Member {
private int memberNumber;
private boolean isPassiveMember;
private String name;
private LocalDate dateOfBirth;
private String phoneNumber;

    public Member(int memberNumber,
                  boolean isPassiveMember,
                  String name,
                  LocalDate dateOfBirth,
                  String phoneNumber) {
        this.memberNumber = memberNumber;
        this.isPassiveMember = isPassiveMember;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }
}
