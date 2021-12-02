package domain;

import domain.member.MemberController;
import domain.result.ResultController;
import ui.UserInterface;
import java.util.ArrayList;

//@author Sofia og Mathias
public class Controller {
    ArrayList<User> users = new ArrayList<>();
    User activeUser;
    MemberController memberController = new MemberController();
    ResultController resultController = new ResultController();
    UserInterface ui = new UserInterface();


    public void mainMenu() {
        ui.hello();

        boolean keepRunning = true;

        while (keepRunning) {
            int choice = ui.MainMenu();
            switch (choice) {
                case 1 -> login();

                case 0 -> keepRunning = false;
            }
        }
    }

    private void login() {
        users.add(new User("admin", "admin", Roles.ADMIN));

        ui.print("enter username: ");
        String username = ui.getString();

        ui.print("enter password: ");
        String password = ui.getString();

        for(User user: users) {
            if (user.getUsername().equals(username) && user.matchPassword(password)) {
                activeUser = user;
                break;
            }
        }

        if (activeUser == null) {
            ui.print("wrong username or password");
        } else {
            userMenuSelector();
            activeUser = null;
        }
    }

    public void userMenuSelector() {
        switch (activeUser.getRole()) {
            case ADMIN -> adminMenu();
            //case CASHIER -> ;
            //case TRAINER -> ;
        }
    }

    public void adminMenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            int choice = ui.adminMenu();
            switch (choice) {
                case 1 -> memberMenu();
                case 2 -> usermenu();
                case 0 -> keepRunning = false;
            }
        }
    }


    //**********************
    //*
    //* Member
    //*
    //**********************
    public void memberMenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            int choice = ui.memberMenu();
            switch (choice) {
                case 1 -> addMember();
                case 2 -> deleteMember();
                case 3 -> editMember();
                case 4 -> seeMember();

                case 0 -> keepRunning = false;
            }
        }
    }

    public void seeMember() {
        ui.print("Her kan du se medlemmerne af svømmeklubben: ");
        ui.print(memberController.getStringOfMembers());

        ui.print("Du bedes her indtaste medlemsindex på det medlem du ønsker at redigere, eller fortryd ved at skrive '0': ");
        int memberIndex = ui.getInt(0, memberController.getAmountOfMembers());

        if (memberIndex > 0) {
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

        if (memberIndex > 0) {
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


    //**********************
    //*
    //* Competition
    //*
    //**********************

   /* public void competitionMenu() {
        boolean keepRunning = true;
        while (keepRunning) {
            int choice = ui.competitionMenu();
            switch (choice) {
                case 1 -> addCompetition();
                case 2 -> deleteCompetition();
                case 3 -> editCompetition();
                case 4 -> backToMenu();
            }
        }
    }

    public void addCompetition() {
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

    public void deleteCompetition() {

    }*/


    //**********************
    //*
    //* User
    //*
    //**********************

    private void usermenu() {
        boolean keepRunning = true;

        while (keepRunning){
            switch (ui.userMenu()) {
                case 1 -> addUser();
                //change
                case 3 -> removeUser();
                case 0 -> keepRunning = false;
            }
        }
    }

    private void addUser() {
        ui.print("Indtast venligst brugernavn: ");
        String userName = ui.getString();

        ui.print("Indtast venligst kodeord: ");
        String password = ui.getString(); // mask userinput?

        ui.print("Vælg venligst brugerens role:\n 1. Admin\n 2. Kasser\n 3. Træner ");
        Roles role = null;
        switch (ui.getInt(1,3)){
            case 1 -> role = Roles.ADMIN;
            case 2 -> role = Roles.CASHIER;
            case 3 -> role = Roles.TRAINER;
        }

        users.add(new User(userName, password, role));
        ui.print(userName + " er blevet oprettet som " + role.toString().toLowerCase());
    }

    private void removeUser() {
        ui.print(getStringOfUsers());
        ui.print("Du bedes her indtaste nummeret på den bruger som du ønsker at slette, eller fortryd ved at skrive '0': ");
        int choice = ui.getInt(0, users.size());

        if (choice != 0){
            choice--;
            ui.print(users.get(choice).getUsername() + " er blevet slettet");
            users.remove(choice);
        }
    }

    private String getStringOfUsers() {
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (User user : users) {
            sb.append(index).append(". ").append(user).append('\n');
            index++;
        }
        return sb.toString();
    }


}