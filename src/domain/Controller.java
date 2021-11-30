package domain;

import domain.member.MemberController;
import ui.UserInterface;
import java.util.ArrayList;

//@author Sofia og Mathias
public class Controller {
    ArrayList<User> users = new ArrayList<>();
    User activeUser;

    MemberController memberController = new MemberController();
    UserInterface ui = new UserInterface();


    public void mainMenu(){
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
        users.add(new User("emilie", "mathias", Roles.ADMIN));

        ui.print("enter username: ");
        String username = ui.getString();

        ui.print("enter password: ");
        String password = ui.getString();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.matchPassword(password)){
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

    public void userMenuSelector(){
        switch (activeUser.getRole()){
            case ADMIN -> adminMenu();
            //case CASHIER -> ;
            //case TRAINER -> ;
        }
    }

    public void adminMenu(){
        boolean keepRunning = true;

        while (keepRunning){
            int choice = ui.adminMenu();
            switch (choice){
                case 1 -> memberMenu();
                case 0 -> keepRunning = false;
            }
        }
    }

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
}