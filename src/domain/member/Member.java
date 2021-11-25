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

    public String toFile() {
        return name+";"+email+";"+birthday+";"+active+";"+elite;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public boolean isPassiveMember() {
        return isPassiveMember;
    }

    public void setPassiveMember(boolean passiveMember) {
        isPassiveMember = passiveMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
