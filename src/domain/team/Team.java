package domain.team;

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
}
