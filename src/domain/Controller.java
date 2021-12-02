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
        users.add(new User("admin;admin;ADMIN"));

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

    private void editTeam() {
        ui.print("Her kan du se svømmeklubbens hold");
        ui.print(memberController.getStringOfTrainers());

        ui.print("Her bedes du indtaste det holdindex på det hold du ønsker at redigere, eller fortryd ved at skrive '0'");

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

    public void addMember() { // TODO: 02/12/2021 Make better!
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

    public void competitionMenu() {
        boolean keepRunning = true;
        while (keepRunning) {
            int choice = ui.competitionMenu();
            switch (choice) {
                case 1 -> addCompetition();
                case 2 -> deleteCompetition();
                case 3 -> editCompetition();
                case 4 -> seeCompetitionMenu();
                case 0 -> keepRunning = false;
            }
        }
    }

    public void addCompetition() {
        ui.print("Stævnenavn: ");
        String competitionName = ui.getString();
        ui.print("Adresse: ");
        String competitionAdress = ui.getString();
        ui.print("Dato og tid for stævnet: ");
        String dateOfCompetition = ui.getString();
        ui.print("Stævne disciplin der skal svømmes i: ");
        String discipline = ui.getString();

        resultController.addCompetition(competitionName,
                competitionAdress,
                dateOfCompetition,
                discipline);
    }

    public void deleteCompetition() {
        ui.print("Vælg hvilket stævne du ønsker at slette ud fra listen herunder\n");
        ui.print("Indtast indekset på det stævne du ønsker at slette");
        int competitionIndex = ui.getInt(0, resultController.getAmountOfCompetition());

        if (competitionIndex > 0) {
            resultController.deleteCompetition(competitionIndex - 1);
        }
    }

    private void editCompetition() {
        ui.print("Her kan du se de stævner der er indskrevet: ");
        ui.print(resultController.getStringOfCompetitions());

        ui.print("Du bedes her skrive det stævneindex på det stævne du ønsker at redigere i");
        int competitionIndex = ui.getInt(0, resultController.getAmountOfCompetition());

        if (competitionIndex != 0) {
            competitionIndex--; // to match with the index

            switch (ui.editCompetitionMenu()) {
                case 1 -> {
                    ui.print("Indtast navn på stævnet");
                    resultController.editCompetition(competitionIndex, "competitionName", ui.getString());
                }
                case 2 -> {
                    ui.print("Indtast adresse på stævnet");
                    resultController.editCompetition(competitionIndex, "competitionAdress", ui.getString());
                }
                case 3 -> {
                    ui.print("Indtast dato og tid på stævnet (dd-mm-åååå tt:mm)");
                    resultController.editCompetition(competitionIndex, "dateOfCompetition", ui.getString());
                }
                case 4 -> {
                    ui.print("Indtast disciplinen på stævnet");
                    resultController.editCompetition(competitionIndex, "competitionDiscipline", ui.getString());
                }
            }
        }
    }


    private void seeCompetitionMenu() {
        ui.print("Her ");
    }


    //**********************
    //*
    //* User
    //*
    //**********************

    private void usermenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            switch (ui.userMenu()) {
                case 1 -> addUser();
                case 2 -> editUser();
                case 3 -> removeUser();
                case 0 -> keepRunning = false;
            }
        }
    }

    // method to add a user to the system
    private void addUser() {
        // get username from user
        ui.print("Indtast venligst brugernavn: ");
        String userName = ui.getString();

        // get password from user
        ui.print("Indtast venligst kodeord: ");
        String password = ui.getString(); // mask userinput?

        // get a role to give to the user
        ui.print("Vælg venligst brugerens role:\n 1. Admin\n 2. Kasser\n 3. Træner ");
        Roles role = null;
        switch (ui.getInt(1, 3)) {
            case 1 -> role = Roles.ADMIN;
            case 2 -> role = Roles.CASHIER;
            case 3 -> role = Roles.TRAINER;
        }

        // create user and add to the arraylist of users
        users.add(new User(userName, password, role));

        // print confirmation message
        ui.print(userName + " er blevet oprettet som " + role.toString().toLowerCase());
    }

    // method to edit a user based on the userindex given by the user
    private void editUser() {
        // print all users and prompt user for the desired user index
        ui.print(getStringOfUsers());
        ui.print("Indtast nummeret på den bruger som du ønsker at redigere, eller fortryd ved at skrive '0': ");
        int choice = ui.getInt(0, users.size());

        // get which attribute the users wants to change
        if (choice != 0) {
            choice--;
            ui.print("Vælg venligst hvad du vil ændre, eller skriv '0' for at afbryde: \n 1. brugernavn\n 2. kodeord\n 3. role ");
            switch (ui.getInt(1, 3)) {
                // set username
                case 1 -> {
                    // get the new username from user and set
                    ui.print("Indtast nyt brugernavn");
                    users.get(choice).setUsername(ui.getString());
                }

                // set password
                case 2 -> {
                    // get the new password from user and set
                    ui.print("Indtast nyt kodeord");
                    users.get(choice).setPassword(ui.getString());
                }

                // set role
                case 3 -> {
                    // get the new role of the user and set, based on the number of the role in the print
                    ui.print("Vælg venligst brugerens nye role:\n 1. Admin\n 2. Kasser\n 3. Træner ");
                    Roles role = null;
                    switch (ui.getInt(1, 3)) {
                        case 1 -> role = Roles.ADMIN;
                        case 2 -> role = Roles.CASHIER;
                        case 3 -> role = Roles.TRAINER;
                    }
                    users.get(choice).setRole(role);
                }
            }
        }
    }

    // method to remove a user from the
    private void removeUser() {
        // print all users and prompt user for the desired user index
        ui.print(getStringOfUsers());
        ui.print("Indtast nummeret på den bruger som du ønsker at slette, eller fortryd ved at skrive '0': ");
        int choice = ui.getInt(0, users.size());

        // if the choice is not 0, then print confirmation message and remove user
        if (choice != 0) {
            choice--;
            ui.print(users.get(choice).getUsername() + " er blevet slettet");
            users.remove(choice);
        }
    }

    private String getStringOfUsers() {
        StringBuilder sb = new StringBuilder();
        int index = 1; // starts at one, for UX and 0 is reserved for aborting the selection

        for(User user: users) {
            sb.append(index).append(". ").append(user).append('\n');
            index++; // increment the index
        }

        return sb.toString();
    }
}