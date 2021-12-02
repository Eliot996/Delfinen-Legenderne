package domain.member;

import domain.Discipline;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

//@author Sofia & Mathias
// TODO: 26/11/2021 make an edit function
//                  remove all ui elements from the class

public class MemberController{
    Scanner userInput = new Scanner(System.in);

    // to add a member to the database parses date(of format "dd-MM-yyyy")
    public void addMember(boolean isPassiveMember,
                          String name,
                          String dateOfBirth,
                          String phoneNumber,
                          String email) {


        addMember(new Member(isPassiveMember, name, getDateFromString(dateOfBirth), phoneNumber, email));
    }

    // to add a competitor to the database parses date(of format "dd-MM-yyyy") and checks for uniqueness of membernumber
    public void addCompetitor(boolean isPassiveMember,
                              String name,
                              String dateOfBirth,
                              String phoneNumber,
                              String email,
                              List<Discipline> disciplines) {


        addMember(new Competitor(isPassiveMember, name, getDateFromString(dateOfBirth), phoneNumber, email, disciplines));
    }

    // to add a trainer to the database parses date(of format "dd-MM-yyyy") and checks for uniqueness of membernumber
    public void addTrainer(boolean isPassiveMember,
                           String name,
                           String dateOfBirth,
                           String phoneNumber,
                           String email) {

        addMember(new Trainer(isPassiveMember, name, getDateFromString(dateOfBirth), phoneNumber, email));
    }


    public List<Member> getMembers() {
        return members;
    }

    public void editMember(int memberIndex, String what, String to) {
        Member member = members.get(memberIndex);

        switch (what){
            case "name" -> member.setName(to);
            case "isPassive" -> member.setPassiveMember(Boolean.parseBoolean(to));
            case "dateOfBirth" -> member.setDateOfBirth(getDateFromString(to));
            case "phoneNumber" -> member.setPhoneNumber(to);
            case "email" -> member.setEmail(to);
        }

        if (member instanceof Competitor competitor){
            switch (what) {
                case "add discipline" -> competitor.addDisciplines(Discipline.valueOf(to.toUpperCase()));
                case "remove discipline" -> competitor.removeDisciplines(Discipline.valueOf(to.toUpperCase()));
            }
        }
    }

    public String getStringOfMembers() {
        int index = 1;
        StringBuilder sb = new StringBuilder();

        for (Member member : members) {
            sb.append(index).append(". ").append(member.simplePrint()).append('\n');
            index++;
        }

        return sb.toString();
    }
    public String getStringOfTrainers(){
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

    public int getAmountOfMembers() {
        return members.size();
    }

    // methods from MemberDatabase
    // @author Mathias
    private ArrayList<Member> members = new ArrayList<>();

    public void addMember(Member member){
        members.add(member);
    }

    public void deleteMember(int memberIndex){
        members.remove(members.get(memberIndex));
    }

    public Member getMember(String name){
        for (Member member : members) {
            if (member.getName().equals(name)){
                return member;
            }
        }
        return null;
    }

    public Member getMember(int memberIndex){
       return members.get(memberIndex);
    }

    public String toCSV() {
        StringBuilder sb = new StringBuilder();

        for (Member member : members) {
            sb.append(member.toCSV()).append('\n');
        }

        return sb.toString();
    }

    public LocalDate getDateFromString(String date) {
        // make a pattern to parse the given dates from
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // get the dates from the user and parse
        return LocalDate.parse(date.trim(), formatter);
    }

    public String getInfo(int memberIndex) {
        Member member = members.get(memberIndex);
        return member.toString();
    }

    public Trainer getTrainerFromUUID(String uuidString) {
        UUID uuid;
        uuid = UUID.fromString(uuidString);
        for (Member member: members) {
            if (member.getMemberID().equals(uuid)){
                return (Trainer) member;
            }
        }
        return null;
    }
}

