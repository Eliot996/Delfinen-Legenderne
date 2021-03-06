package domain;

import domain.member.Member;
import domain.member.MemberController;
import domain.result.ResultController;
import domain.team.TeamController;
import ui.UserInterface;
import database.FileHandler;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

//@author Sofia og Mathias
public class Controller {
    ArrayList<User> users = new ArrayList<>();
    User activeUser;
    MemberController memberController = new MemberController();
    ResultController resultController = new ResultController(memberController);
    TeamController teamController = new TeamController(memberController);
    Contingent contingentController = new Contingent(this);
    UserInterface ui = new UserInterface();


    public void mainMenu() {
        initializeUsers();
        initializaData();
        //makeMockData();

        memberController.setTeamController(teamController);
        ui.hello();

        boolean keepRunning = true;

        while (keepRunning) {
            int choice = ui.MainMenu();
            switch (choice) {
                case 1 -> login();

                case 0 -> keepRunning = false;
            }
        }

        saveData();
    }

    private String userToCSV() {
        StringBuilder sb = new StringBuilder();

        for(User user: users) {
            sb.append(user.toCSV()).append("\n");
        }
        return sb.toString();
    }

    private void login() {
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

    private void userMenuSelector() {
        switch (activeUser.getRole()) {
            case ADMIN   -> adminMenu();
            case CASHIER -> contingentMenu();
            case TRAINER -> trainerMenu();
        }
    }

    private void trainerMenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            int choice = ui.trainerMenu();
            switch (choice) {
                case 1 -> competitionMenu();
                case 2 -> teamMenu();
                case 3 -> resultMenu();
                case 0 -> keepRunning = false;
            }
        }
    }

    private void adminMenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            int choice = ui.adminMenu();
            switch (choice) {
                case 1 -> memberMenu();
                case 2 -> usermenu();
                case 3 -> competitionMenu();
                case 4 -> teamMenu();
                case 5 -> resultMenu();
                case 6 -> contingentMenu();
                case 0 -> keepRunning = false;
            }
        }
    }


    //**********************
    //*
    //* Member
    //*
    //**********************
    private void memberMenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            int choice = ui.memberMenu();
            switch (choice) {
                case 1 -> addMember();
                case 2 -> addCompetitor();
                case 3 -> addTrainer();
                case 4 -> deleteMember();
                case 5 -> editMember();
                case 6 -> seeMember();

                case 0 -> keepRunning = false;
            }
        }
    }

    private void addTrainer() {
        ui.print("Vil du konverer et eksiterende medlem til en tr??ner?: ");
        if (ui.getBoolean()) {
            ui.print("Indtast nummeret p?? det medlem du ??nsker at konvertere til en tr??ner, eller fortryd ved at skrive '0': ");
            ui.print(memberController.getStringOfMembers());
            int memberIndex = ui.getInt(0, memberController.getAmountOfMembers());

            if (memberIndex != 0) {
                memberIndex--;
                memberController.addTrainer(memberIndex);
            }
        } else {
            ui.print("Indtast navn p?? tr??neren");
            String name = ui.getString();

            ui.print("Indtast f??dselsdato(dd-mm-????????)");
            LocalDate DOB = ui.getDate();

            ui.print("Indtast telefonnummer");
            String phoneNumber = ui.getString();

            ui.print("Indtast email");
            String email = ui.getString();

            ui.print("er medlemmet passiv?");
            boolean isPassive = ui.getBoolean();

            memberController.addTrainer(isPassive, name, DOB, phoneNumber, email);
            contingentController.addCharge(memberController.getMember(memberController.getAmountOfMembers()- 1));

            ui.print(name + " er blevet oprettet som tr??ner");
        }
    }

    private void addCompetitor() {
        ui.print("Vil du konverer et eksiterende medlem til en konkurrencesv??mmer?: ");
        if (ui.getBoolean()) {
            ui.print("Indtast nummeret p?? det medlem du ??nsker at konvertere til en konkurrencesv??mmer, eller fortryd ved at skrive '0': ");
            ui.print(memberController.getStringOfMembers());
            int memberIndex = ui.getInt(0, memberController.getAmountOfMembers());

            if (memberIndex != 0) {
                memberIndex--;
                memberController.addCompetitor(memberIndex);
            }
        } else {
            ui.print("Indtast navn");
            String name = ui.getString();

            ui.print("Indtast f??dselsdato(dd-mm-????????)");
            LocalDate DOB = ui.getDate();

            ui.print("Indtast telefonnummer");
            String phoneNumber = ui.getString();

            ui.print("Indtast email");
            String email = ui.getString();

            ui.print("er medlemmet passiv?");
            boolean isPassive = ui.getBoolean();

            memberController.addCompetitor(isPassive, name, DOB, phoneNumber, email);
            contingentController.addCharge(memberController.getMember(memberController.getAmountOfMembers()- 1));

            ui.print(name + " er blevet oprettet som konkurrencesv??mmer");
        }
    }

    private void seeMember() {
        ui.print("Her kan du se medlemmerne af sv??mmeklubben: ");
        ui.print(memberController.getStringOfMembers());

        ui.print("Du bedes her indtaste medlemsindex p?? det medlem du ??nsker at redigere, eller fortryd ved at skrive '0': ");
        int memberIndex = ui.getInt(0, memberController.getAmountOfMembers());

        if (memberIndex > 0) {
            ui.print(memberController.getInfo(memberIndex - 1));
        }
    }

    private void editMember() {
        ui.print("Her kan du se medlemmerne af sv??mmeklubben: ");
        ui.print(memberController.getStringOfMembers());

        ui.print("Du bedes her indtaste medlemsindex p?? det medlem du ??nsker at redigere, eller fortryd ved at skrive '0': ");
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
                    ui.print("Indtast f??dselsdato (dd-mm-????????):");
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
                    ui.print("V??lg disciplin til at tilf??je (kun mulig p?? en konkurrencesv??mmer):");
                    memberController.editMember(memberIndex, "add discipline", ui.getDiscipline().toString());
                }
                case 7 -> {
                    ui.print("V??lg disciplin til at fjerne (kun mulig p?? en konkurrencesv??mmer):");
                    memberController.editMember(memberIndex, "remove discipline", ui.getDiscipline().toString());
                }
                case 8 -> {
                    ui.print("Indtast nummeret p?? det hold som du vil tilf??je medlemmet til, eller skriv '0' for at anullere: ");
                    ui.print(teamController.getStringOfTeams(memberController.getMember(memberIndex)));
                    int teamindex = ui.getInt(0, teamController.getAmountOfTeams());

                    if (teamindex != 0) {
                        teamindex--;
                        memberController.editMember(memberIndex, "add to team", Integer.toString(teamindex));
                    }
                }
                case 9 -> {
                    ui.print("Indtast nummeret p?? det hold som du vil fjerne medlemmet fra, eller skriv '0' for at anullere: ");
                    ui.print(teamController.getTeamsWithMember(memberController.getMember(memberIndex)));
                    int teamindex = ui.getInt(0, teamController.getAmountOfTeams());

                    if (teamindex != 0) {
                        teamindex--;
                        memberController.editMember(memberIndex, "remove from team", Integer.toString(teamindex));
                    }
                }
            }
        }
    }

    private void deleteMember() {
        ui.print("Her kan du se medlemmerne af sv??mmeklubben: ");
        ui.print(memberController.getStringOfMembers());

        ui.print("Du bedes her indtaste medlemsindex p?? det medlem du ??nsker at slette, eller fortryd ved at skrive '0': ");
        int memberIndex = ui.getInt(0, memberController.getAmountOfMembers());

        if (memberIndex > 0) {
            memberController.deleteMember(memberIndex - 1);
        }
    }

    private void addMember() {
        ui.print("Er brugeren passive? ");
        boolean isPassive = ui.getBoolean();
        ui.print("Indtast navn p?? medlemmet: ");
        String name = ui.getString();
        ui.print("Indtast f??dselsdato p?? medlemmet: ");
        LocalDate DOB = ui.getDate();
        ui.print("Indtast telefonnummer p?? medlemmet: ");
        String phone = ui.getString();
        ui.print("Indtast email p?? medlemmet: ");
        String email = ui.getString();

        memberController.addMember(
                isPassive,
                name,
                DOB,
                phone,
                email);
        contingentController.addCharge(memberController.getMember(memberController.getAmountOfMembers()- 1));
    }

    //**********************
    //*
    //* Team
    //*
    //**********************

    private void teamMenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            int choice = ui.teamMenu();
            switch (choice) {
                case 1 -> addTeam();
                case 2 -> deleteTeam();
                case 3 -> editTeam();
                case 4 -> seeTeam();
                case 0 -> keepRunning = false;
            }
        }
    }

    private void addTeam() {
        ui.print("Navn: ");
        String name = ui.getString();
        ui.print("Beskrivelse: ");
        String description = ui.getString();

        teamController.addTeam(name, description);
    }

    private void deleteTeam() {
        ui.print("Her kan du se sv??mmeklubbens hold: ");
        ui.print(teamController.getStringOfTeams());

        ui.print("Du bedes indtaste holdindex for det hold du ??nsker at slette, eller fortryd ved at skrive '0': ");
        int teamIndex = ui.getInt(0, teamController.getAmountOfTeams());

        if (teamIndex > 0) {
            teamController.deleteTeam(teamIndex - 1);
        }
    }

    private void editTeam() {
        ui.print("Her kan du se sv??mmeklubbens hold: ");
        ui.print(teamController.getStringOfTeams());

        ui.print("Her bedes du indtaste det nummeret p?? det hold du ??nsker at redigere, eller fortryd ved at skrive '0': ");
        int teamIndex = ui.getInt(0, teamController.getAmountOfTeams());

        if (teamIndex != 0) {
            teamIndex--;
            switch (ui.editTeamMenu()) {
                case 1 -> {
                    ui.print("Indtast navn; ");
                    teamController.editTeam(teamIndex, "name", ui.getString());
                }
                case 2 -> {
                    ui.print("Indtast beskrivelse");
                    teamController.editTeam(teamIndex, "description", ui.getString());
                }
                case 3 -> {
                    ui.print(memberController.getStringOfTrainers());
                    ui.print("Indtast hvilken tr??ner du gerne vil have p?? et hold");
                    teamController.editTeam(teamIndex, "add trainers", ui.getString());
                }
                case 4 -> {
                    ui.print(teamController.getTrainersOnTeam(teamIndex));
                    ui.print("Indtast hvilken tr??ner du gerne vil fjerne p?? et hold");
                    teamController.editTeam(teamIndex, "remove trainers", ui.getString());
                }
                case 5 -> {
                    ui.print(memberController.getStringOfMembers());
                    ui.print("Indtast nummeret p?? medlemmet du vil tilf??je til holdet: ");
                    int memberIndex = ui.getInt(0, memberController.getAmountOfMembers());
                    teamController.editTeam(teamIndex, "add member", Integer.toString(memberIndex));
                }
                case 6 -> {
                    ui.print(teamController.getMembersOnTeam(teamIndex));
                    ui.print("Indtast nummeret p?? medlemmet du vil fjerne fra holdet: ");
                    int memberIndex = ui.getInt(0, memberController.getAmountOfMembers());
                    teamController.editTeam(teamIndex, "remove member", Integer.toString(memberIndex));
                }
            }
        }
    }

    private void seeTeam() {
        ui.print("Her kan du se sv??mmeklubbens hold: ");
        ui.print(teamController.getStringOfTeams());

        ui.print("Du bedes her indtaste holdindex p?? det hold du ??nsker at se, eller fortryd ved at skrive '0'");
        int teamIndex = ui.getInt(0, teamController.getAmountOfTeams());

        if (teamIndex > 0) {
            ui.print(teamController.getInfo(teamIndex - 1));
        }
    }

    //**********************
    //*
    //* Competition
    //*
    //**********************

    private void competitionMenu() {
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

    private void addCompetition() {
        ui.print("St??vnenavn: ");
        String competitionName = ui.getString();
        ui.print("Adresse: ");
        String competitionAdress = ui.getString();
        LocalDateTime dateTime = ui.getDateAndTime();
        ui.print("St??vne disciplin der skal sv??mmes i");
        Discipline competitionDiscipline = ui.getDiscipline();
        resultController.addCompetition(competitionName, competitionAdress, dateTime, competitionDiscipline);

    }

    private void deleteCompetition() {
        ui.print("V??lg hvilket st??vne du ??nsker at slette ud fra listen herunder\n");
        ui.print(resultController.getStringOfCompetitions());
        ui.print("Indtast indekset p?? det st??vne du ??nsker at slette");
        int competitionIndex = ui.getInt(0, resultController.getAmountOfCompetition());

        if (competitionIndex > 0) {
            resultController.deleteCompetition(competitionIndex - 1);
        }
    }

    private void editCompetition() {
        ui.print("Her kan du se de st??vner der er indskrevet: ");
        ui.print(resultController.getStringOfCompetitions());

        ui.print("Du bedes her skrive det st??vneindex p?? det st??vne du ??nsker at redigere i");
        int competitionIndex = ui.getInt(0, resultController.getAmountOfCompetition());

        if (competitionIndex != 0) {
            competitionIndex--; // to match with the index

            switch (ui.editCompetitionMenu()) {
                case 1 -> {
                    ui.print("Indtast nyt navn p?? st??vnet");
                    resultController.editCompetition(competitionIndex, "competitionName", ui.getString());
                }
                case 2 -> {
                    ui.print("Indtast ny adresse p?? st??vnet");
                    resultController.editCompetition(competitionIndex, "competitionAdress", ui.getString());
                }
                case 3 -> {
                    ui.print("Indtast dato og tid p?? st??vnet (dd-mm-???????? tt:mm)");
                    resultController.editCompetition(competitionIndex, "dateOfCompetition", ui.getString());
                }
                case 4 -> {
                    ui.print("V??lg en disciplin du ??nsker at fjerne");
                    ui.getDiscipline();
                    resultController.editCompetition(competitionIndex, "removeDiscipline", ui.getDiscipline().toString());
                }
                case 5 -> {
                    ui.print("V??lg nu den disciplin du ??nsker at tilf??je");

                    resultController.editCompetition(competitionIndex, "addDiscipline", ui.getDiscipline().toString());
                }
            }
        }
    }


    private void seeCompetitionMenu() {
        ui.print("Her kan du se listen over hvilke st??vner I er tilmeldt");
        ui.print(resultController.getStringOfCompetitions());
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
        Roles role = ui.getRole();

        // create user and add to the arraylist of users
        users.add(new User(userName, password, role));

        // print confirmation message
        ui.print(userName + " er blevet oprettet som " + role.toString().toLowerCase());
    }

    // method to edit a user based on the userindex given by the user
    private void editUser() {
        // print all users and prompt user for the desired user index
        ui.print(getStringOfUsers());
        ui.print("Indtast nummeret p?? den bruger som du ??nsker at redigere, eller fortryd ved at skrive '0': ");
        int choice = ui.getInt(0, users.size());

        // get which attribute the users wants to change
        if (choice != 0) {
            choice--;
            ui.print("V??lg venligst hvad du vil ??ndre, eller skriv '0' for at afbryde: \n 1. brugernavn\n 2. kodeord\n 3. role ");
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
                    ui.print("V??lg venligst brugerens nye role:\n 1. Admin\n 2. Kasser\n 3. Tr??ner ");
                    users.get(choice).setRole(ui.getRole());
                }
            }
        }
    }

    // method to remove a user from the
    private void removeUser() {
        // print all users and prompt user for the desired user index
        ui.print(getStringOfUsers());
        ui.print("Indtast nummeret p?? den bruger som du ??nsker at slette, eller fortryd ved at skrive '0': ");
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

    //**********************
    //*
    //* results
    //*
    //**********************

    private void resultMenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            switch (ui.resultMenu()) {
                case 1 -> addResult();
                case 2 -> deleteResult();
                case 3 -> editResult();
                case 4 -> viewResults();
                case 5 -> getTopFive();

                case 0 -> keepRunning = false;
            }
        }
    }

    private void addResult() {
        // gets member
        ui.print("Indtaste nummeret p?? det medlem du ??nsker at oprette et resultat for, eller fortryd ved at skrive '0': ");
        ui.print(memberController.getStringOfCompetitors());
        int memberIndex = ui.getInt(0, memberController.getAmountOfMembers());

        // gets time
        LocalTime time = ui.getTime();

        // gets date
        LocalDate date = ui.getDate();

        // get competition
        int competitionIndex = -1;
        ui.print("Vil du tilf??je et st??vne til resultatet?");
        if (ui.getBoolean()) {
            ui.print(resultController.getStringOfCompetitions());
            ui.print("Indtaste nummeret p?? det st??vne du ??nsker at tilf??je til resultatet: ");
            competitionIndex = ui.getInt(1, resultController.getAmountOfCompetition());
        }

        // get discipline
        ui.print("Indtast resultatets disciplin");
        Discipline discipline = ui.getDiscipline();

        // add to results
        resultController.addResult(memberIndex, time, date, competitionIndex, discipline);
    }

    private void deleteResult() {
        ui.print("Her kan du se resultaterne for konkurrencesv??mmerne af sv??mmeklubben: ");
        ui.print(resultController.getStringOfResults());

        ui.print("Du bedes her indtaste resultatindex p?? det resultat du ??nsker at slette, eller fortryd ved at skrive '0': ");
        int resultIndex = ui.getInt(0, resultController.getAmountOfResults());

        if (resultIndex > 0) {
            resultController.deleteResult(resultIndex - 1);
        }
    }

    private void editResult() {
        ui.print("Her kan du se resultater for konkurrencesv??mmerne af sv??mmeklubben: ");
        ui.print(resultController.getStringOfResults());

        ui.print("Du bedes her indtast resultatindex p?? p?? det resultat du ??nsker at redigere, eller fortryd ved at skrive '0': ");
        int resultIndex = ui.getInt(0, resultController.getAmountOfResults());

        if (resultIndex != 0) {
            resultIndex--; // for at matche med index man f??r ind.
        }

        switch (ui.editResultMenu()) {
            case 1 -> {
                ui.print("Indtast konkurrencesv??mmer: ");
                resultController.editResult(resultIndex, "competitor", ui.getString());
            }
            case 2 -> {
                ui.print("Indtast tid: ");
                resultController.editResult(resultIndex, "time", ui.getString());
            }
            case 3 -> {
                ui.print("Indtast dato (hh-mm-ss): ");
                resultController.editResult(resultIndex, "date", ui.getString());
            }
            case 4 -> {
                ui.print("Indtast konkurrence: ");
                resultController.editResult(resultIndex, "competition", ui.getString());
            }
            case 5 -> {
                ui.print("Indtast disciplin: ");
                resultController.editResult(resultIndex, "disciplin", ui.getString());
            }
        }
    }

    private void viewResults() {
        ui.print("Her kan du se resultaterne for konkurrencesv??mmer af sv??mmeklubben: ");
        ui.print(resultController.getStringOfResults());

        ui.print("Du bedes her indtaste resultatindex p?? det resultat du ??nsker at se, eller fortryd ved at skrive '0': ");
        int resultIndex = ui.getInt(0, resultController.getAmountOfResults());

        if (resultIndex > 0) {
            ui.print(resultController.getInfo(resultIndex - 1));
        }
    }

    private void getTopFive() {
        ui.print("Hvilken disiplin vil du finde resultater fra?");
        Discipline discipline = ui.getDiscipline();

        ui.print("Hvilken medlemstype vil du s??ge p??? \n 1. Junior\n 2. Senior\n 3. Pensionist");
        String ageGroup = null;
        switch (ui.getInt(1,3)){
            case 1 -> ageGroup = "junior";
            case 2 -> ageGroup = "senior";
            case 3 -> ageGroup = "pensioner";
        }

        ui.print(resultController.getTopFive(discipline, ageGroup));
    }

    //**********************
    //*
    //* Contingent
    //*
    //**********************

    private void contingentMenu() {
        boolean keepRunning = true;

        while (keepRunning) {
            switch (ui.contingentMenu()) {
                case 1 -> addPayment();
                case 2 -> seeContigents();
                case 3 -> seeLatePayments();
                case 4 -> memberContingentPaid();
                case 5 -> editCharge();
                case 6 -> seeContingentPrices();
                case 7 -> editContingentPrices();
                case 8 -> contingentStatistics();

                case 0 -> keepRunning = false;
            }
        }
    }

    private void addPayment() {
        ui.print("Her kan du se alle medlemmerne i klubben");
        ui.print(memberController.getStringOfMembers());

        ui.print("Indtast det medlem du vil tilf??je en opkr??vning p??");
        int memberIndex = ui.getInt(0, memberController.getAmountOfMembers()) - 1;

        ui.print("Hvor meget skal der opkr??ves?");
        int amount = ui.getInt(0, 99999);

        contingentController.addCharge(memberController.getMember(memberIndex), amount);
    }

    private void seeContigents() {
        ui.print(contingentController.getStringOfCharges());
    }

    private void seeLatePayments() {
        ui.print(contingentController.getStringOfLatePayments());
    }

    private void memberContingentPaid() {
        ui.print(contingentController.getStringOfUnpaidPayments());
        ui.print("Indtast nummeret p?? den betaling som skal markeres som betalt, eller skriv '0' for at afbryde:");
        int choice = ui.getInt(0, contingentController.getAmountOfCharges()) - 1;

        if (choice != -1) {
            contingentController.markPaid(choice);
            ui.print("Betaling registreret");
        }
    }

    private void editCharge() {
        ui.print(contingentController.getStringOfCharges());
        ui.print("Hvilken opkr??vning vil du redigere?, eller skriv '0' for at afbryde");
        int chargeIndex = ui.getInt(0, contingentController.getAmountOfCharges()) - 1;

        if (chargeIndex != -1) {
            switch (ui.editContingentmenu()) {
                case 1 -> {
                    ui.print("Hvad vil du ??ndre prisen til?");
                    contingentController.editCharge(chargeIndex, "price",
                            Integer.toString(ui.getInt(0, 99999)));
                }
                case 2 -> {
                    ui.print("Hvilken dato skal den ??ndres til?");
                    LocalDate date = ui.getDate();
                    contingentController.editCharge(chargeIndex, "date", date.toString());
                }
                case 3 -> {
                    ui.print("Er denne opkr??vning betalt?");
                    contingentController.editCharge(chargeIndex, "paid", ui.getBoolean().toString());
                }
                case 4 -> {
                    ui.print("Er du sikker p?? at du vil slette denne opkr??vning?");
                    boolean choice = ui.getBoolean();
                    if (choice) {
                        contingentController.editCharge(chargeIndex, "delete", "delete");
                    }
                }
            }
        }

    }

    private void seeContingentPrices() {
        ui.print(contingentController.getStringOfContingents());
    }

    private void editContingentPrices() {
        ui.print(contingentController.getStringOfContingents());
        ui.print("Hvilket Kontingent vil du ??ndre prisen p???, eller skriv '0' for at afbryde");
        int contintgentIndex = ui.getInt(0, 4);

        if (contintgentIndex != 0) {
            String choice = null;
            switch (contintgentIndex){
                case 1 -> choice = "passivt medlem";
                case 2 -> choice = "seniormedlem";
                case 3 -> choice = "juniormedlem";
                case 4 -> choice = "pensionistmedlem";
            }
            ui.print("Hvad skal den nye pris v??re?");
            contingentController.setContingentPrice(choice, ui.getInt(0, 99999));
        }

    }

    private void contingentStatistics() {
        boolean keepRunning = true;

        while (keepRunning) {
            switch (ui.ContintgentStatistics()) {
                case 1 -> ui.print("Der forventes at indkr??ves " + contingentController.getExpectedIncome() + "kr.");
                case 2 -> ui.print("??rlige kontingent indt??gt: " +
                        contingentController.yearlyContingent(memberController.getMembers()) + "kr.");
                case 3 -> ui.print("Den gennemsnitlige opkr??vning lyder p?? " +
                        contingentController.getAveragePayment()  + "kr.");
                case 4 -> ui.print("Der er " + contingentController.getLatePaymentTotal() + "kr. i restance");
                case 5 -> ui.print(String.format("Restance listen udg??re %.2f%% af den forventede indt??gt",
                        contingentController.getRelativeLatePayments()));
            }
        }
    }

    // **************
    // *
    // * DATA
    // *
    // **************

    private void saveData() {
        FileHandler.writeToUsers(userToCSV());
        FileHandler.writeToMembers(memberController.memberToCSV());
        FileHandler.writeToCompetitors(memberController.competitorsToCSV());
        FileHandler.writeToTrainers(memberController.trainersToCSV());
        FileHandler.writeToTeams(teamController.teamsToCSV());
        FileHandler.writeToCompetitions(resultController.competitionsToCSV());
        FileHandler.writeToResults(resultController.resultToCSV());
        FileHandler.writeToContingent(contingentController.ContigentToCSV());
        FileHandler.writeToCharges(contingentController.chargesToCSV());
    }

    public void initializaData() {
        memberController.initMembers(FileHandler.getMEMBERSFromFile());
        memberController.initCompetitors(FileHandler.getCOMPETITORSFromFile());
        memberController.initTrainers(FileHandler.getTRAINERSFromFile());
        teamController.initTeams(FileHandler.getTEAMSFromFile());
        resultController.initCompetitions(FileHandler.getCOMPETITIONSFromFile());
        resultController.initResults(FileHandler.getCOMPETITORS_RESULTFromFile());
        contingentController.initContingents(FileHandler.getCONTINGENTFromFile());
        contingentController.initCharges(FileHandler.getChargesFromFile());
    }

    private void initializeUsers() {
        for(String userString: FileHandler.getUSERSFromFile()) {
            users.add(new User(userString));
        }
    }

    private void makeMockData() {
        // members
        for (int i = 0; i < 50; i++) {
            memberController.addMember(
                    !(Math.random() > 0.25),
                    "name" + i,
                    LocalDate.of((int) Math.ceil(Math.random() * 70) + 1940, (int) Math.ceil(Math.random() * 12), (int) Math.ceil(Math.random() * 28)),
                    "+45" + (int) (Math.random() * 99999999),
                    "mail@mail.com");
            System.out.println(memberController.getMember(memberController.getAmountOfMembers()-1));
        }

        // trainers
        for (int i = 0; i < 5; i++) {
            memberController.addTrainer(
                    !(Math.random() > 0.25),
                    "trainer" + i,
                    LocalDate.of((int) Math.ceil(Math.random() * 70) + 1940, (int) Math.ceil(Math.random() * 12), (int) Math.ceil(Math.random() * 28)),
                    "+45" + (int) (Math.random() * 99999999),
                    "mail@mail.com");
            System.out.println(memberController.getMember(memberController.getAmountOfMembers()-1));
        }

        // competitors
        for (int i = 0; i < 25; i++) {
            memberController.addCompetitor(
                    !(Math.random() > 0.25),
                    "Competitor" + i,
                    LocalDate.of((int) Math.ceil(Math.random() * 70) + 1940, (int) Math.ceil(Math.random() * 12), (int) Math.ceil(Math.random() * 28)),
                    "+45" + (int) (Math.random() * 99999999),
                    "mail@mail.com");

            if (Math.random() < 0.5) memberController.editMember(memberController.getAmountOfMembers() - 1, "add discipline", Discipline.CRAWL.toString());
            if (Math.random() < 0.5) memberController.editMember(memberController.getAmountOfMembers() - 1, "add discipline", Discipline.BACKCRAWL.toString());
            if (Math.random() < 0.5) memberController.editMember(memberController.getAmountOfMembers() - 1, "add discipline", Discipline.BUTTERFLY.toString());
            if (Math.random() < 0.5) memberController.editMember(memberController.getAmountOfMembers() - 1, "add discipline", Discipline.BREASTSTROKE.toString());

            System.out.println(memberController.getMember(memberController.getAmountOfMembers()-1));
        }

        // Competitions
        Discipline discipline = Discipline.CRAWL;
        for (int i = 0; i < 10; i++) {
            if (Math.random() < 0.25)discipline = Discipline.CRAWL;
            if (Math.random() < 0.25)discipline = Discipline.BACKCRAWL;
            if (Math.random() < 0.25)discipline = Discipline.BREASTSTROKE;
            if (Math.random() < 0.25)discipline = Discipline.BUTTERFLY;
            resultController.addCompetition("Competition " + i,
                    "address",
                    LocalDateTime.of((int) Math.ceil(Math.random() * 5) + 2015,
                            (int) Math.ceil(Math.random() * 12),
                            (int) Math.ceil(Math.random() * 28),
                            (int) (Math.random() * 24),
                            (int) (Math.random() * 60)),
                    discipline);
            System.out.println(resultController.getCompetition(resultController.getAmountOfCompetition() - 1));
        }

        // results
        for (int i = 0; i < 101; i++) {
            if (Math.random() < 0.25)discipline = Discipline.CRAWL;
            if (Math.random() < 0.25)discipline = Discipline.BACKCRAWL;
            if (Math.random() < 0.25)discipline = Discipline.BREASTSTROKE;
            if (Math.random() < 0.25)discipline = Discipline.BUTTERFLY;

            resultController.addResult((int) Math.ceil(Math.random() * 25) + memberController.getAmountOfMembers() - 26,
                    LocalTime.of(0,
                            (int) (Math.random() * 60),
                            (int) (Math.random() * 60)),
                    LocalDate.of(
                            (int) Math.ceil(Math.random() * 5) + 2015,
                            (int) Math.ceil(Math.random() * 12),
                            (int) Math.ceil(Math.random() * 28)),
                    resultController.getAmountOfCompetition() - 1,
                    discipline);
            System.out.println(resultController.getResult(resultController.getAmountOfResults() - 1));
        }

        // teams
        for (int i = 0; i < 6; i++) {
            teamController.addTeam("team " + i, "description for team " + i);

            for (int j = 0; j < Math.random() * 15; j++) {
                teamController.editTeam(
                        teamController.getAmountOfTeams() - 1,
                        "add member",
                        Integer.toString((int) Math.ceil(Math.random() * 25) + memberController.getAmountOfMembers() - 26));
            }

            for (int j = 0; j < 2; j++) {
                teamController.editTeam(
                        teamController.getAmountOfTeams() - 1,
                        "add trainers", Integer.toString((int) Math.ceil(Math.random() * 5) + memberController.getAmountOfMembers() - 31));
            }

            System.out.println(teamController.getTeamFromIndex(teamController.getAmountOfTeams() - 1));
        }
    }

    public Member getMember(String memberID) {
        return memberController.getMemberFromUUID(memberID);
    }
}