package domain.member;

import java.time.LocalDate;
import java.util.UUID;

// @author Mathias og Sofia
public class Member {
    // attributter
    private int memberNumber;
    private boolean isPassiveMember;
    private String name;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
    private UUID memberID;

    //konstruktør
    public Member(int memberNumber,
                  boolean isPassiveMember,
                  String name,
                  LocalDate dateOfBirth,
                  String phoneNumber,
                  String email) {

        this.memberID = UUID.randomUUID();
        this.memberNumber = memberNumber;
        this.isPassiveMember = isPassiveMember;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public boolean isSenior() {
        LocalDate eightteenYearsLater = LocalDate.of(dateOfBirth.getYear() + 18, dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        LocalDate sixtyYearsLater = LocalDate.of(dateOfBirth.getYear() + 60, dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());

        LocalDate today = LocalDate.now();

        return eightteenYearsLater.isBefore(today) && sixtyYearsLater.isAfter(today);
    }

    public boolean isPensioner() {
        LocalDate sixtyYearsLater = LocalDate.of(dateOfBirth.getYear() + 60, dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        LocalDate today = LocalDate.now();

        return sixtyYearsLater.isBefore(today);
    }

    @Override
    public String toString() {
        String memberType;

        if (isSenior()) {
            memberType = "seniormedlem";
        } else if (isPensioner()) {
            memberType = "pensionistmedlem";
        } else {
            memberType = "juniormedlem";
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
        return  memberID + ";"+
                memberNumber + ";" +
                name + ";" +
                dateOfBirth + ";"+
                isPassiveMember + ";" +
                phoneNumber + ";" +
                email;
    }

    //CSV konstruktør
    public Member(String CSV) {
        String[] elements = CSV.split(";");

        this.memberID = UUID.fromString(elements[0]);
        this.memberNumber = Integer.parseInt(elements[1]);
        this.name = elements[2];
        this.dateOfBirth = LocalDate.parse(elements[3]);
        this.isPassiveMember = Boolean.parseBoolean(elements[4]);
        this.phoneNumber = elements[5];
        this.email = elements[6];
    }

    // getter og setter for atributterne
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }
    public int getMemberNumber() {
        return memberNumber;
    }

    public void setPassiveMember(boolean passiveMember) {
        isPassiveMember = passiveMember;
    }
    public boolean isPassiveMember() {
        return isPassiveMember;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}






