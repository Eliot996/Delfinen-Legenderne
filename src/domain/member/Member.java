package domain.member;

import java.time.LocalDate;

public class Member {
private int memberNumber;
private boolean isPassiveMember;
private String name;
private LocalDate dateOfBirth;
private String phoneNumber;
private String email;

    public Member(int memberNumber,
                  boolean isPassiveMember,
                  String name,
                  LocalDate dateOfBirth,
                  String phoneNumber,
                  String email) {
        this.memberNumber = memberNumber;
        this.isPassiveMember = isPassiveMember;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
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

    @Override
    public String toString() {
        String memberType = "";

        if (dateOfBirth.getYear() < 18) {
            memberType = "juniormedlem";
        } else if (dateOfBirth.getYear() >= 18) {
            memberType = "seniormedlem";
        }

        return "---- Medlemsoplysninger ----\n" +
                "Fulde navn: " + name +
                "\nAlder: " + dateOfBirth +
                "\nMedlemstype: " + memberType +
                "\nAktivitetsform: " + isPassiveMember +
                "\nMedlemsnummer: " + memberNumber +
                "\nTelefonnummer: " + phoneNumber +
                "\nEmail: " + email + "\n";
    }

    public String toCSV() {
        return  name + ";"+
                dateOfBirth + ";"+
                isPassiveMember + ";"+
                memberNumber + ";"+
                phoneNumber + ";"+
                email + ";";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}






