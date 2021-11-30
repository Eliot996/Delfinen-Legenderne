package domain;
import domain.member.Member;
import domain.member.MemberController;
import ui.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//@author Sofia og Mathias

public class Controller {
    Scanner userInput = new Scanner(System.in);
    MemberController memberController = new MemberController();
    UserInterface ui = new UserInterface();

    public void addMember() {
        System.out.print("ID: ");
        int membernumber = userInput.nextInt();
        System.out.print("is passive: ");
        boolean isPassive = userInput.nextBoolean();
        userInput.nextLine(); // scanner bug
        System.out.print("name: ");
        String name = userInput.nextLine();
        System.out.print("DOB: ");
        String DOB = userInput.nextLine();
        System.out.print("phone: ");
        String phone = userInput.nextLine();
        System.out.print("email: ");
        String email = userInput.nextLine();

        memberController.addMember(
                isPassive,
                name,
                DOB,
                phone,
                email);
    }

    public void runMenu() {
        boolean keepRunning = true;
        while (keepRunning) {
            int choice = ui.menu();
            switch (choice) {
                case 1 -> addMember();
                case 2 -> memberController.deleteMember();
                //case 3 -> // rediger medlem
                case 4 -> memberController.getMembers();
                //case 5 -> // se kontingenter
                //case 6 ->  // se svømmeresultater

                case 0 -> System.exit(0);
            }
        }
    }

    private ArrayList<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        members.add(member);
    }

    public void deleteMember(Member member) {
        members.remove(member);
    }

    public Member getMember(String name) {
        for (Member member : members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }
}