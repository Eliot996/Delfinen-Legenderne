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

    public void deleteMember() {
        System.out.println("Her kan du se medlemmerne af svømmeklubben: " + getMembers());
        System.out.println("Du bedes her indtaste medlemsindex på det medlem du ønsker at slette: ");
        int memberIndex = userInput.nextInt();
        userInput.nextLine();

        Member member = members.get(memberIndex);

        if (member == null){
            System.out.println("no member was found");
        }else {
            deleteMember(member);
        }
    }

    public void editMember(int memberIndex, String what, String to) {
        Member member = members.get(memberIndex);

        switch (what){
            case "name" -> member.setName(to);
            case "isPassive" -> member.setPassiveMember(Boolean.parseBoolean(to));
            case "dateOfBirth" -> member.setDateOfBirth(getDateFromString(to));
            case "phonenumber" -> member.setPhoneNumber(to);
            case "email" -> member.setEmail(to);
        }

        if (member instanceof Competitor competitor){
            switch (what) {
                case "add discipline" -> competitor.addDisciplines(Discipline.valueOf(to));
                case "remove discipline" -> competitor.removeDisciplines(Discipline.valueOf(to));
            }
        }
    }

    // methods from MemberDatabase
    // @author Mathias
    private ArrayList<Member> members = new ArrayList<>();

    public void addMember(Member member){
        members.add(member);
    }

    public void deleteMember(Member member){
        members.remove(member);
    }

    public Member getMember(String name){
        for (Member member : members) {
            if (member.getName().equals(name)){
                return member;
            }
        }
        return null;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
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

