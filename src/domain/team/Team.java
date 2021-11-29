package domain.team;
//@author Mathias
import domain.member.Trainer;
import java.util.ArrayList;

public class Team {
    private String name;
    private String description;
    private ArrayList<Trainer> trainers;

    public Team(String name, String description){
        this.name = name;
        this.description = description;
    }

    //@Author Sofia

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
                "\n Trænere: " + trainers + "\n";
    }

    public String toCSV() {
        return  name + ";" +
                description + ";" +
                trainers;
    }

    public Team(String CSV) {
    String[] elements = CSV.split(";");
    this.name = elements[0];
    this.description = elements [1];
    //this.trainers = elements[1];
    }
}


