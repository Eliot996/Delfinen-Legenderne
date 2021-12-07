package domain.team;

import domain.member.Member;
import domain.member.Trainer;
import java.util.ArrayList;

//@author Mathias og Sofia
public class Team {
    //attributter
    private String name;
    private String description;
    private ArrayList<Trainer> trainers = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();

    //konstruktør
    public Team(String name,
                String description) {

        this.name = name;
        this.description = description;
    }

    //CSV konstruktør
    public Team(String csv) {
        String[] elements = csv.split(";");
        this.name = elements[0];
        this.description = elements [1];
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

    public ArrayList<Member> getMembers() {
        return members;
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
        return name + ";" +
                description + ";" +
                listOfTrainersToCSV(trainers) + ";" +
                listToCSV(members);
    }

    private String listToCSV(ArrayList<Member> list) {
        StringBuilder sb = new StringBuilder();
        if (list.size() > 0) {
            sb.append((list.get(0).getMemberID()));
            if (list.size() > 1) {
                for (int i = 1; i < list.size(); i++) {
                    sb.append(":").append(list.get(i).getMemberID());
                }
            }
        }

        return sb.toString();
    }

    private String listOfTrainersToCSV(ArrayList<Trainer> listOfTrainers) {
        ArrayList<Member> list = new ArrayList<>(listOfTrainers);
        return listToCSV(list);
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


