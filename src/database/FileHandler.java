package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private static final String USERS = "data/users.csv";
    private static final String MEMBERS = "data/members.csv";
    private static final String TRAINERS = "data/trainers.csv";
    private static final String COMPETITORS = "data/competitors.csv";
    private static final String TEAMS = "data/teams.csv";
    private static final String COMPETITIONS = "data/competitions.csv";
    private static final String COMPETITORS_RESULTS = "data/results.csv";
    private static final String CONTINGENT = "data/contingent.csv";
    private static final String CHARGES = "data/charges.csv";

    //**********************
    //*
    //* read from file
    //*
    //**********************

    private static ArrayList<String> readFromFile(String filePath) throws IOException {
        ArrayList<String> lines = new ArrayList<>();

        try {
            Scanner load = new Scanner(new File(filePath));
            while (load.hasNextLine()) {
                lines.add(load.nextLine());
            }
        } catch (FileNotFoundException e) {
            // do nothing it is not important, and is handled
        }

        return lines;
    }

    public static List<String> getUSERSFromFile() {
        try {
            return readFromFile(USERS);
        } catch (IOException e) {
            return null;
        }
    }

    public static List<String> getMEMBERSFromFile() {
        try {
            return readFromFile(MEMBERS);
        } catch (IOException e) {
            return null;
        }
    }
    public static List<String> getTRAINERSFromFile() {
        try {
            return readFromFile(TRAINERS);
        } catch (IOException e) {
            return null;
        }
    }
    public static List<String> getCOMPETITORSFromFile() {
        try {
            return readFromFile(COMPETITORS);
        } catch (IOException e) {
            return null;
        }
    }
    public static List<String> getTEAMSFromFile() {
        try {
            return readFromFile(TEAMS);
        } catch (IOException e) {
            return null;
        }
    }
    public static List<String> getCOMPETITIONSFromFile() {
        try {
            return readFromFile(COMPETITIONS);
        } catch (IOException e) {
            return null;
        }
    }
    public static List<String> getCOMPETITORS_RESULTFromFile() {
        try {
            return readFromFile(COMPETITORS_RESULTS);
        } catch (IOException e) {
            return null;
        }
    }
    public static List<String> getCONTINGENTFromFile() {
        try {
            return readFromFile(CONTINGENT);
        } catch (IOException e) {
            return null;
        }
    }

    public static List<String> getChargesFromFile() {
        try {
            return readFromFile(CHARGES);
        } catch (IOException e) {
            return null;
        }
    }

    //**********************
    //*
    //* write to file
    //*
    //**********************

    private static void writeToFile(String filepath, String toWrite) {
        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(toWrite);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();
    }

    public static void writeToUsers(String toWrite) {
        writeToFile(USERS, toWrite);
    }

    public static void writeToMembers(String toWrite) {
        writeToFile(MEMBERS, toWrite);
    }

    public static void writeToTrainers(String toWrite) {
        writeToFile(TRAINERS,toWrite);
    }

    public static void writeToCompetitors(String toWrite) {
        writeToFile(COMPETITORS, toWrite);
    }

    public static void writeToTeams(String toWrite) {
        writeToFile(TEAMS, toWrite);
    }

    public static void writeToCompetitions(String toWrite) {
        writeToFile(COMPETITIONS, toWrite);
    }

    public static void writeToResults(String toWrite) {
        writeToFile(COMPETITORS_RESULTS, toWrite);
    }

    public static void writeToContingent(String toWrite) {
        writeToFile(CONTINGENT, toWrite);
    }

    public static void writeToCharges(String toWrite) {
        writeToFile(CHARGES, toWrite);
    }

}
// Delfinen
/*
Dolphine:
https://www.asciiart.eu/animals/dolphins
                                    _
                               _.-~~.)
         _.--~~~~~---....__  .' . .,'
       ,'. . . . . . . . . .~- ._ (
      ( .. .g. . . . . . . . . . .~-._
   .~__.-~    ~`. . . . . . . . . . . -.
   `----..._      ~-=~~-. . . . . . . . ~-.
             ~-._   `-._ ~=_~~--. . . . . .~.
              | .~-.._  ~--._-.    ~-. . . . ~-.
               \ .(   ~~--.._~'       `. . . . .~-.                ,
                `._\         ~~--.._    `. . . . . ~-.    .- .   ,'/
_  . _ . -~\        _ ..  _          ~~--.`_. . . . . ~-_     ,-','`  .
             ` ._           ~                ~--. . . . .~=.-'. /. `
       - . -~            -. _ . - ~ - _   - ~     ~--..__~ _,. /   \  - ~
              . __ ..                   ~-               ~~_. (  `
)`. _ _               `-       ..  - .    . - ~ ~ .    \    ~-` ` `  `. _
 */
