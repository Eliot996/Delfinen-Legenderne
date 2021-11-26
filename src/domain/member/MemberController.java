package domain.member;

import database.MemberDatabase;
import domain.Controller;
import domain.Discipline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


// TODO: 26/11/2021 add trainer and test
//                  make an edit function
//                  make delete function

public class MemberController{

    private final MemberDatabase memberDB = new MemberDatabase();

    // to add a member to the database parses date(of format "dd-MM-yyyy") and checks for uniqueness of membernumber
    public void addMember(int memberNumber,
                          boolean isPassiveMember,
                          String name,
                          String dateOfBirth,
                          String phoneNumber,
                          String email) {

        checkUniquenessOfMemberNumber(memberNumber);

        memberDB.addMember(new Member(memberNumber, isPassiveMember, name, getDateFromString(dateOfBirth), phoneNumber, email));
    }

    // to add a competitor to the database parses date(of format "dd-MM-yyyy") and checks for uniqueness of membernumber
    public void addCompetitor(int memberNumber,
                              boolean isPassiveMember,
                              String name,
                              String dateOfBirth,
                              String phoneNumber,
                              String email,
                              List<Discipline> disciplines) {

        checkUniquenessOfMemberNumber(memberNumber);

        memberDB.addMember(new Competitor(memberNumber, isPassiveMember, name, getDateFromString(dateOfBirth),
                                          phoneNumber, email, disciplines));
    }

    // to add a trainer to the database parses date(of format "dd-MM-yyyy") and checks for uniqueness of membernumber
    public void addTrainer(int memberNumber,
                           boolean isPassiveMember,
                           String name,
                           String dateOfBirth,
                           String phoneNumber,
                           String email) {

        checkUniquenessOfMemberNumber(memberNumber);

        memberDB.addMember(new Trainer(memberNumber, isPassiveMember, name, getDateFromString(dateOfBirth), phoneNumber, email));
    }

    public LocalDate getDateFromString(String date){
        // make a pattern to parse the given dates from
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // get the dates from the user and parse
        return LocalDate.parse(date, formatter);
    }

    public void checkUniquenessOfMemberNumber(int memberNumber){
        for (Member member : getMembers()) {
            if (member.getMemberNumber() == memberNumber){
                throw new IllegalArgumentException("That memberNumber is allready in use!");
            }
        }
    }

    public List<Member> getMembers(){
        return memberDB.getMembers();
    }
    public void addMember(){
        Controller controller = new Controller();
        controller.addMember();
    }


}
