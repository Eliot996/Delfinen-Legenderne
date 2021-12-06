package database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class fileHandler {
    private static final String USERS = "data/users.csv";
    private static final String MEMBERS = "data/members.csv";
    private static final String TRAINERS = "data/trainers.csv";
    private static final String COMPETITORS = "data/competitors.csv";
    private static final String TEAMS = "data/teams.csv";
    private static final String COMPETITIONS = "data/competitions.csv";
    private static final String COMPETITORS_RESULTS = "data/competitor_results.csv";
    private static final String CONTINGENT = "data/contingent.csv";

    //**********************
    //*
    //* read from file
    //*
    //**********************

    private String[] readFromFile(String filepath){
        return null;
    }

    public String[] getUSERSFromFile() {
        return readFromFile(USERS);
    }
    public String[] getMEMBERSFromFile() {
        return readFromFile(MEMBERS);
    }
    public String [] getTRAINERSFromFile() {
        return readFromFile(TRAINERS);
    }
    public String [] getCOMPETITORSFromFile() {
        return readFromFile(COMPETITORS);
    }
    public String [] getTEAMSFromFile() {
        return readFromFile(TEAMS);
    }
    public String [] getCOMPETITIONSFromFile() {
        return readFromFile(COMPETITIONS);
    }
    public String [] getCOMPETITORS_RESULTFromFile() {
        return readFromFile(COMPETITORS_RESULTS);
    }
    public String [] getCONTINGENTFromFile() {
        return readFromFile(CONTINGENT);
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
    public static void WriteToTeams(String toWrite) {
        writeToFile(TEAMS, toWrite);
    }
    public static void writeToCompetitions(String toWrite) {
        writeToFile(COMPETITIONS, toWrite);
    }
    public static void writeToCompetitors_Results(String toWrite) {
        writeToFile(COMPETITORS_RESULTS, toWrite);
    }
    public static void writeToContingent(String toWrite) {
        writeToFile(CONTINGENT, toWrite);
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
