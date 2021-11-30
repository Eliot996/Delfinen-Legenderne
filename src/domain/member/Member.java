package domain.member;

import java.time.LocalDate;
import java.util.UUID;

// @author Mathias og Sofia
public class Member {
    // attributter
    private UUID memberID;
    private boolean isPassiveMember;
    private String name;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;

    //konstruktør
    public Member(boolean isPassiveMember,
                  String name,
                  LocalDate dateOfBirth,
                  String phoneNumber,
                  String email) {

        this.memberID = UUID.randomUUID();
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

    public String getMemberType() {
        if (isSenior()) {
            return  "seniormedlem";
        } else if (isPensioner()) {
            return  "pensionistmedlem";
        } else {
            return  "juniormedlem";
        }
    }

    @Override
    public String toString() {
        return "---- Medlemsoplysninger ----\n" +
                "Fulde navn: " + name +
                "\nAlder: " + dateOfBirth +
                "\nMedlemstype: " + getMemberType() +
                "\nAktivitetsform: " + isPassiveMember +
                "\nTelefonnummer: " + phoneNumber +
                "\nEmail: " + email + "\n";
    }

    public String toCSV() {
        return  memberID + ";"+
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
        this.name = elements[1];
        this.dateOfBirth = LocalDate.parse(elements[2]);
        this.isPassiveMember = Boolean.parseBoolean(elements[3]);
        this.phoneNumber = elements[4];
        this.email = elements[5];
    }

    // getter og setter for atributterne
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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

    public UUID getMemberID() {
        return memberID;
    }
    public void setMemberID(UUID memberID) {
        this.memberID = memberID;
    }
}






