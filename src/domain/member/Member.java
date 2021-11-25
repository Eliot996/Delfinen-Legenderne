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

    public int getMemberNumber() {
        return memberNumber;
    }

    public boolean isPassiveMember() {
        return isPassiveMember;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String toFile() {
        return name+";"+email+";"+birthday+";"+active+";"+elite;
    }
}
