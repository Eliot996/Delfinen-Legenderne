package domain;

import domain.member.Member;
import domain.member.MemberController;
import domain.result.ResultController;
import ui.UserInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//@author Sofia og Mathias

public class Controller {
    Scanner userInput = new Scanner(System.in);
    MemberController memberController = new MemberController();
    ResultController resultController = new ResultController();
    UserInterface ui = new UserInterface();

    public void memberMenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            int choice = ui.menu();
            switch (choice) {
                case 1 -> addMember();
                case 2 -> deleteMember();
                case 3 -> editMember();
                case 4 -> seeMember();

                case 0 -> keepRunning = false;
            }
        }
    }

    public void seeMember(){
        ui.print("Her kan du se medlemmerne af svømmeklubben: ");
        ui.print(memberController.getStringOfMembers());

        ui.print("Du bedes her indtaste medlemsindex på det medlem du ønsker at redigere, eller fortryd ved at skrive '0': ");
        int memberIndex = ui.getInt(0, memberController.getAmountOfMembers());

        if (memberIndex > 0){
            ui.print(memberController.getInfo(memberIndex - 1));
        }
    }

    private void editMember() {
        ui.print("Her kan du se medlemmerne af svømmeklubben: ");
        ui.print(memberController.getStringOfMembers());

        ui.print("Du bedes her indtaste medlemsindex på det medlem du ønsker at redigere, eller fortryd ved at skrive '0': ");
        int memberIndex = ui.getInt(0, memberController.getAmountOfMembers());

        if (memberIndex != 0) {
            memberIndex--; // to match with the index in

            switch (ui.editMemberMenu()) {
                case 1 -> {
                    ui.print("Indtast navn:");
                    memberController.editMember(memberIndex, "name", ui.getString());
                }
                case 2 -> {
                    ui.print("Indtast status (enten ja eller nej):");
                    memberController.editMember(memberIndex, "isPassive", ui.getBoolean().toString());
                }
                case 3 -> {
                    ui.print("Indtast fødselsdato (dd-mm-åååå):");
                    memberController.editMember(memberIndex, "dateOfBirth", ui.getString());
                }
                case 4 -> {
                    ui.print("Indtast telefonnummer:");
                    memberController.editMember(memberIndex, "phoneNumber", ui.getString());
                }
                case 5 -> {
                    ui.print("Indtast email:");
                    memberController.editMember(memberIndex, "email", ui.getString());
                }
                case 6 -> {
                    ui.print("Indtast disciplin til at tilføje (kun mulig på en konkurrencesvømmer:");
                    memberController.editMember(memberIndex, "add discipline", ui.getString());
                }
                case 7 -> {
                    ui.print("Indtast disciplin til at fjerne (kun mulig på en konkurrencesvømmer:");
                    memberController.editMember(memberIndex, "remove discipline", ui.getString());
                }
            }
        }
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
        boolean isPassive = ui.getBoolean();
        System.out.print("name: ");
        String name = ui.getString();
        System.out.print("DOB: ");
        String DOB = ui.getString();
        System.out.print("phone: ");
        String phone = ui.getString();
        System.out.print("email: ");
        String email = ui.getString();

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

    public void competitionMenu(){
        boolean keepRunning = true;
        while (keepRunning){
            int choice = ui.competitionMenu();
            switch (choice) {
                case 1 -> addCompetition();
                case 2 -> deleteCompetition;
                case 3 -> editCompetition;
                case 4 -> backToMenu;
            }
        }
    }

    public void addCompetition(){
        System.out.println("Stævnenavn: ");
        String competitionName = userInput.nextLine();
        System.out.println("Adresse: ");
        String competitionAdress = userInput.nextLine();
        System.out.println("Dato og tid for stævnet: ");
        String dateOfCompetition = userInput.nextLine();
        System.out.println("Stævne disciplin der skal svømmes i: ");
        String discipline = userInput.nextLine();

        resultController.addCompetition(competitionName,
                                        competitionAdress,
                                        dateOfCompetition,
                                        discipline);
    }
    public void deleteCompetition(){

    }


}