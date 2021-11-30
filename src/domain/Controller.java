package domain;

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

    public void memberMenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            int choice = ui.menu();
            switch (choice) {
                case 1 -> addMember();
                case 2 -> deleteMember();
                case 3 -> editMember();
                case 4 -> memberController.getMembers();
                //case 5 ->  se kontingenter
                //case 6 -> // se svømmeresultater

                case 0 -> keepRunning = false;
            }
        }
    }

    private void editMember() {
        ui.print(memberController.getStringOfMembers());
    }

    private void deleteMember() {
        ui.print("Her kan du se medlemmerne af svømmeklubben: ");
        ui.print(memberController.getStringOfMembers());

        ui.print("Du bedes her indtaste medlemsindex på det medlem du ønsker at slette, eller fortryd ved at skrive '0': ");
        int memberIndex = ui.getInt(0, memberController.getAmountOfMembers());

        if (memberIndex > 0){
            memberController.deleteMember(memberIndex - 1);
        }
    }

    public void addMember() {
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