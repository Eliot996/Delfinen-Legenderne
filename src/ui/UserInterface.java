package ui;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

// @Author Sofia

public class UserInterface {
    Scanner userInput = new Scanner(System.in);

    public int menu() {
        System.out.println("""
                Velkommen til svømmeklubben Delfinen
                ----------------menu----------------
                                
                1) Oprette medlem
                2) Slette medlem
                3) Redigere medlem
                4) Se medlemsoplysninger på et medlem
                5) Se kontingenter
                6) Se svømmeresultater
                                
                0) Afslutte programmet""");

        return getInt(0, 6);
    }

    public int editMemberMenu(){
        System.out.println("""
                Hvad vil du ændre?
                1. Navn
                2. Passivitet
                3. Fødselsdato (dd-mm-åååå)
                4. Telefonnummer
                5. Email
                6. Tilføj disciplin
                7. Fjern disciplin
                
                0. Annuler""");

        return getInt(0,7);
    }


    public int competitionMenu(){
        System.out.println("""
                -------Stævnemenu-------
                
                1) Opret stævne
                2) Slet stævne
                3) Rediger stævne
                4) Tilbage til hovedmenu
                
                """);
        return getInt(1,4);
    }

    public int getInt(int lowerLimit, int upperLimit) {
        int choice = getInt();

        if (choice >= lowerLimit && choice <= upperLimit) {
            return choice;
        } else {
            System.out.println("Skriv venligst et tal mellem " + lowerLimit + " og " + upperLimit);
            return getInt(lowerLimit, upperLimit);
        }
    }

    public int getInt() {
        if (userInput.hasNextInt()){
            int choice = userInput.nextInt();
            userInput.nextLine(); // to prevent scanner bug
            return choice;
        } else {
            System.out.println("Indtast venligst et heltal");
            userInput.nextLine();
            return getInt();
        }
    }

    public String getString() {
        return userInput.nextLine().trim();
    }

    public Boolean getBoolean(){
        System.out.println("test");
        String input = userInput.nextLine().trim().toLowerCase();

        if (input.equals("ja")){
            return true;
        } else if (input.equals("nej")) {
            return false;
        } else {
            System.out.println("Indtast venligst enten 'ja' eller 'nej'");
            return getBoolean();
        }
    }

    public void print(String toPrint) {
        System.out.println(toPrint);
    }
}
