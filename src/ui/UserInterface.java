package ui;
import domain.Discipline;
import domain.Roles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Scanner;

// @Author Sofia og Mathias

public class UserInterface {
    Scanner userInput = new Scanner(System.in);

    public void hello() {
        System.out.println("""
                                                    _
                                               _.-~~.)
                         _.--~~~~~---....__  .' . .,'
                       ,'. . . . . . . . . .~- ._ (
                      ( .. .g. . . . . . . . . . .~-._
                   .~__.-~    ~`. . . . . . . . . . . -.
                   `----..._      ~-=~~-. . . . . . . . ~-.
                             ~-._   `-._ ~=_~~--. . . . . .~.
                              | .~-.._  ~--._-.    ~-. . . . ~-.
                               \\ .(   ~~--.._~'       `. . . . .~-.                ,
                                `._\\         ~~--.._    `. . . . . ~-.    .- .   ,'/
                _  . _ . -~\\        _ ..  _          ~~--.`_. . . . . ~-_     ,-','`  .
                             ` ._           ~                ~--. . . . .~=.-'. /. `
                       - . -~            -. _ . - ~ - _   - ~     ~--..__~ _,. /   \\  - ~
                              . __ ..                   ~-               ~~_. (  `
                )`. _ _               `-       ..  - .    . - ~ ~ .    \\    ~-` ` `  `. _
                Velkommen til svømmeklubben Delfinen""");
    }

    public int MainMenu() {
        System.out.println("""
                1. Login
                0. Afslut program""");
        return getInt(0, 1);
    }

    public int adminMenu() {
        System.out.println("""
                1. Medlems menu
                2. Bruger menu
                3. Stævne menu
                4. Hold menu
                
                0. Afbryd""");
        return getInt(0, 4);
    }

    public int memberMenu() {
        System.out.println("""
                -----------------medlemsmenu---------------
                1) Opret medlem
                2) Opret konkurrencesvømmer
                3) Opret træner
                4) Slet medlem
                5) Redigere medlem
                6) Se medlemsoplysninger på et medlem
                                
                0) Afbryd""");

        return getInt(0, 6);
    }

    public int editMemberMenu() {
        System.out.println("""
                Hvad vil du ændre?
                1. Navn
                2. Passivitet
                3. Fødselsdato (dd-mm-åååå)
                4. Telefonnummer
                5. Email
                6. Tilføj disciplin
                7. Fjern disciplin
                8. Tilføj til hold
                9. Fjern fra hold
                                
                0. Annuller""");

        return getInt(0, 9);
    }

    public int competitionMenu() {
        System.out.println("""
                -------Stævnemenu-------
                                
                1) Opret stævne
                2) Slet stævne
                3) Rediger stævne
                4) Se liste med stævner
                
                0) Tilbage til hovedmenu
                                
                """);
        return getInt(0, 4);
    }

    public int editCompetitionMenu(){
        System.out.println("""
                Hvad vil du gerne ændre?
                1) Stævnenavn
                2) Adresse
                3) Dato og tid
                4) Stævnedisciplin
                
                0) Annuller
                """);
        return getInt(0,4);
    }

    public int teamMenu() {
        System.out.println("""
                -------Holdmenu-------
                
                1) Opret hold
                2) Slet hold
                3) Rediger hold
                4) Se alle hold
                
                0) Afbryd
                """);
        return getInt(0,4);
    }

    public int editTeamMenu(){
        System.out.println("""
                Hvad vil du gerne ændre?
                1) Navn
                2) Beskrivelse
                3) Sæt træner på et hold
                4) Slet træner på et hold
                
                0) Annuller""");
        return getInt(0,4);
    }

    public int resultMenu() {
        System.out.println("""
                -------Resultatmenu-------
                
                1) Opret resultat
                2) Slet resultat
                3) Rediger resultat
                4) Se alle resultater
                
                0) Afbryd
                """);
        return getInt(0,4);
    }

    public int editResultMenu(){
        System.out.println("""
                Hvad vil du gerne ændre?
                1) Konkurrencesvømmer
                2) Tid
                3) Dato
                4) Konkurrence
                5) Disciplin
                
                0) Annuller""");
        return getInt(0,5);
    }

    public int userMenu() {
        System.out.println("""
                -------Brugermenu-------
                
                1) Opret bruger
                2) Rediger bruger
                3) Slet bruger
                
                0) Tilbage til hovedmenu
                """);
        return getInt(0, 3);
    }

    public int getInt(int lowerLimit, int upperLimit) {
        int choice = getInt();

        if (choice >= lowerLimit && choice <= upperLimit) {
            return choice;
        } else {
            System.out.println("Skriv venligst et tal mellem " + lowerLimit + " og " + upperLimit);
            return getInt(lowerLimit, upperLimit);
        }
    }

    public int getInt() {
        if (userInput.hasNextInt()){
            int choice = userInput.nextInt();
            userInput.nextLine(); // to prevent scanner bug
            return choice;
        } else {
            System.out.println("Indtast venligst et heltal");
            userInput.nextLine();
            return getInt();
        }
    }

    public String getString() {
        return userInput.nextLine().trim();
    }

    public Boolean getBoolean(){
        String input = userInput.nextLine().trim().toLowerCase();

        if (input.equals("ja")){
            return true;
        } else if (input.equals("nej")) {
            return false;
        } else {
            System.out.println("Indtast venligst enten 'ja' eller 'nej'");
            return getBoolean();
        }
    }

    public Roles getRole() {
        print("Vælg venligst brugerens role:\n 1. Admin\n 2. Kasser\n 3. Træner ");
        switch (getInt(1, 3)) {
            case 1  -> {return Roles.ADMIN;}
            case 2  -> {return Roles.CASHIER;}
            case 3  -> {return Roles.TRAINER;}
            default -> {return null;}
        }
    }

    public Discipline getDiscipline() {
        print(" 1. crawl\n 2. rygcrawl\n 3. brystsvømning\n 3. butterfly");
        switch (getInt(1, 3)) {
            case 1  -> {return Discipline.CRAWL;}
            case 2  -> {return Discipline.BACKCRAWL;}
            case 3  -> {return Discipline.BREASTSTROKE;}
            case 4  -> {return Discipline.BUTTERFLY;}
            default -> {return null;}
        }
    }

    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    private final DateTimeFormatter TIMEFORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");

    public LocalTime getTime() {
        print("Indtast venligst tid (HH:mm:ss,SSS)");
        String input = userInput.nextLine();

        try {
            return LocalTime.parse(input, TIMEFORMATTER);
        } catch (DateTimeParseException e) {
            print("Forkert input, prøv igen");
            return getTime();
        }
    }

    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public LocalDate getDate() {
        print("Indtast venligst tid (dd-MM-yyyy)");
        String input = userInput.nextLine();

        try {
            return LocalDate.parse(input, DATEFORMATTER);
        } catch (DateTimeParseException e) {
            print("Forkert input, prøv igen");
            return getDate();
        }
    }
}


