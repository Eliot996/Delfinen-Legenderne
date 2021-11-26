package domain;
import domain.member.MemberController;
import java.time.LocalDate;
import java.util.Scanner;

//@author Sofia

public class Controller {
    Scanner userInput = new Scanner(System.in);

    public void addMember(){
        MemberController memberController = new MemberController();
        memberController.addMember(userInput.nextInt(),
                                   userInput.nextBoolean(),
                                   userInput.nextLine(),
                                   LocalDate.parse(userInput.nextLine()),
                                   userInput.nextLine(),
                                   userInput.nextLine());
    }
}
