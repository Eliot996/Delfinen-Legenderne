package ui;
import java.util.Scanner;

// @Author Sofia

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
                0. Afbryd""");
        return getInt(0, 2);
    }

    public int memberMenu() {
        System.out.println("""
                -----------------medlemsmenu---------------
                1) Oprette medlem
                2) Slette medlem
                3) Redigere medlem
                4) Se medlemsoplysninger på et medlem
                                
                0) Afbryd""");

        return getInt(0, 4);
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
                                
                0. Annuler""");

        return getInt(0, 7);
    }


    public int competitionMenu() {
        System.out.println("""
                -------Stævnemenu-------
                                
                1) Opret stævne
                2) Slet stævne
                3) Rediger stævne
                4) Tilbage til hovedmenu
                                
                """);
        return getInt(1, 4);
    }

    public int teamMenu() {
        System.out.println("""
                -------Holdmenu-------
                
                1) Opret hold
                2) Slet hold
                3) Rediger hold
                4) Sæt træner på et hold
                5) Tilbage til hovedmenu
                """);
        return getInt(1, 5);
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
        System.out.println("test");
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

    public void print(String toPrint) {
        System.out.println(toPrint);
    }
}
