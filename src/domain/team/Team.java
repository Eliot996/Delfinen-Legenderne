package domain.team;

import domain.member.Member;
import domain.member.MemberController;
import domain.member.Trainer;
import java.util.ArrayList;

//@author Mathias og Sofia
public class Team {
    //attributter
    private String name;
    private String description;
    private ArrayList<Trainer> trainers;
    private ArrayList<Member> members;

    //konstruktør
    public Team(String name,
                String description) {

        this.name = name;
        this.description = description;
        this.trainers = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    //CSV konstruktør
    public Team(String teamString) {

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
    public String simplePrint() {
        return "Navn: " + name + ", Beskrivelse: " + description + ", Træner(e): " + trainers;
    }

    public String getStringOfTrainers(){
            int index = 1;
            StringBuilder sb = new StringBuilder();

            for (Member member : trainers) {
                if(member instanceof Trainer) {
                    sb.append(index).append(". ").append(member.simplePrint()).append('\n');
                }
                index++;
            }

            return sb.toString();
    }

    public String getStringOfMembers() {
        int index = 1;
        StringBuilder sb = new StringBuilder();

        for (Member member : members) {
            if(member instanceof Trainer) {
                sb.append(index).append(". ").append(member.simplePrint()).append('\n');
            }
            index++;
        }

        return sb.toString();
    }

    public void addTrainer(Trainer trainer){
        trainers.add(trainer);
    }

    public void removeTrainer(int trainerIndex){
        trainers.remove(trainerIndex);
    }

    public void addMember(Member member){
        members.add(member);
    }

    public void removeMember(Member member){
        members.remove(member);
    }

    public void removeMember(int memberIndex) {
        members.remove(memberIndex);
    }

    public boolean hasMember(Member member){
        return members.contains(member);
    }
}


