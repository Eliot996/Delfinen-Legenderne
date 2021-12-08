package domain;


import java.time.LocalDate;
import java.util.HashMap;


public class Contingent {
    HashMap<String, Integer> contingent = new HashMap<String, Integer>();
    LocalDate dateOfBirth;

    public Contingent() {
        contingent.put("Ungdomssvømmere", 1000);
        contingent.put("Seniorsvømmere", 1600);
        contingent.put("Pensionist", 1200);
        contingent.put("Passivt medlem", 500);


    }

    public boolean isSenior() {
        LocalDate eighteenYearsLater = LocalDate.of(dateOfBirth.getYear() + 18, dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        LocalDate sixtyYearsLater = LocalDate.of(dateOfBirth.getYear() + 60, dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());

        LocalDate today = LocalDate.now();

        return eighteenYearsLater.isBefore(today) && sixtyYearsLater.isAfter(today);
    }

    public boolean isPensioner() {
        LocalDate sixtyYearsLater = LocalDate.of(dateOfBirth.getYear() + 60, dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        LocalDate today = LocalDate.now();

        return sixtyYearsLater.isBefore(today);
    }

}