package database;

import java.io.File;
import java.io.IOException;

public class fileHandler {
    private final String USERS = "data/users.csv";
    private final String MEMBERS = "data/members.csv";
    private final String TRAINERS = "data/trainers.csv";
    private final String COMPETITORS = "data/competitors.csv";
    private final String TEAMS = "data/teams.csv";
    private final String COMPETITIONS = "data/competitions.csv";
    private final String COMPETITORS_RESULTS = "data/competitor_results.csv";
    private final String CONTINGENT = "data/contingent.csv";

    //**********************
    //*
    //* read from file
    //*
    //**********************

    private String[] readFromFile(String filepath){
        return null;
    }

    private void writeToFile(String filepath, String toWrite) {

    }
    //**********************
    //*
    //* write to file
    //*
    //**********************

    public String[] getUSERSFromFile() {
        return readFromFile(USERS);
    }

    public void writeToUsers(String toWrite) {
        writeToFile(USERS, toWrite);
    }
    public void writeToMembers(String toWrite) {
        writeToFile(MEMBERS, toWrite);
    }
    public void writeToTrainers(String toWrite) {
        writeToFile(TRAINERS,toWrite);
    }
    public void writeToCompetitors(String toWrite) {
        writeToFile(COMPETITORS, toWrite);
    }
    public void WriteToTeams(String toWrite) {
        writeToFile(TEAMS, toWrite);
    }
    public void writeToCompetitions(String toWrite) {
        writeToFile(COMPETITIONS, toWrite);
    }
    public void writeToCompetitorsResults(String toWrite) {
        writeToFile(COMPETITORS_RESULTS, toWrite);
    }
    public void writeToContingent(String toWrite) {
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
