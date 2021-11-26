package domain;
import domain.member.MemberController;
import ui.UserInterface;

import java.util.Scanner;

//@author Sofia

public class Controller {
    Scanner userInput = new Scanner(System.in);
    MemberController memberController = new MemberController();
    UserInterface ui = new UserInterface();

    public void addMember(){
        memberController.addMember(userInput.nextInt(),
                                   userInput.nextBoolean(),
                                   userInput.nextLine(),
                                   userInput.nextLine(),
                                   userInput.nextLine(),
                                   userInput.nextLine());
    }

    public void runMenu(){
        boolean keepRunning = true;
        while (keepRunning) {
            int choice = ui.menu();
            switch (choice) {
                case 1 -> memberController.addMember();
                //case 2 -> //slet medlem
                //case 3 -> // rediger medlem
                case 4 -> memberController.getMembers();
                //case 5 -> // se kontingenter
                //case 6 ->  // se svømmeresultater

                case 0 -> System.exit(0);
            }
        }
    }
}