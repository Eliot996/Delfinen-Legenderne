package ui;
import java.util.InputMismatchException;
import java.util.Scanner;

// @Author Sofia

public class UserInterface {
    Scanner userInput = new Scanner(System.in);

    public int menu() {
        System.out.println("""
                Velkommen til svømmeklubben Delfinen
                -----------------menu---------------
                                
                1) Oprette medlem
                2) Slette medlem
                3) Redigere medlem
                4) Se medlemsoplysninger på et medlem
                5) Se kontingenter
                6) Se svømmeresultater
                                
                0) Afslutte programmet""");

        return getInt(0, 6);
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
}
