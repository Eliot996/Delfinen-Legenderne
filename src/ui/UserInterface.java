package ui;
import domain.member.MemberController;
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

        int choice;
        while (true) {
            try {
                choice = userInput.nextInt();
                if (choice <= 6 && choice >= 0) {
                    break;
                } else {
                    System.out.println("Dette input er ikke muligt, du bedes vælge et tal fra 0-6");
                }
            } catch (InputMismatchException e) {
                System.out.println("Dette input er ikke muligt, du bedes vælge et tal fra 0-6");
                userInput.nextLine();
            }
        }
        return choice;
    }
}
