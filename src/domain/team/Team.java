package domain.team;
//@author Mathias og Sofia
import domain.member.MemberController;
import domain.member.Trainer;
import java.util.ArrayList;

public class Team {
    //attributter
    private String name;
    private String description;
    private ArrayList<Trainer> trainers;
    //konstruktør
    public Team(String name,
                String description,
                ArrayList<Trainer> trainers) {

        this.name = name;
        this.description = description;
    }

    //@Author Sofia
    // getter og setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }
    public void setTrainers(ArrayList<Trainer> trainers) {
        this.trainers = trainers;
    }

    @Override
    public String toString() {
        return "---- Holdoplysninger ----\n" +
                "Holdnavn: " + name +
                "\n Holdbeskrivelse: " + description +
                "\n Træner(e): " + trainers + "\n";
    }

    public String toCSV() {
        return  name + ";" +
                description + ";" +
                trainers;
    }

    //CSV konstruktør
    public Team(String CSV, MemberController memberController) {
    String[] elements = CSV.split(";");
    this.name = elements[0];
    this.description = elements [1];

    if (elements[2].contains(":")){
        String[] trainerCSVs = elements[2].split(":");
        for (String trainerCSV: trainerCSVs) {
            this.trainers.add (memberController.getTrainerFromUUID(trainerCSV));
        }
    } else{
        this.trainers.add (memberController.getTrainerFromUUID(elements[2]));
        }
    }
}


