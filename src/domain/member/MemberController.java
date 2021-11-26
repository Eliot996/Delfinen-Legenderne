package domain.member;

import database.MemberDatabase;
import domain.Discipline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


// TODO: 26/11/2021 add competitor and test
//                  add trainer and test
//                  Make date parser
//                  make an edit function
//                  make delete function

public class MemberController{

    private final MemberDatabase memberDB = new MemberDatabase();

    // TODO: 25/11/2021 add date parsing and test uniqness of membernumber
    public void addMember(int memberNumber,
                          boolean isPassiveMember,
                          String name,
                          String dateOfBirth,
                          String phoneNumber,
                          String email) {

        checkUniquenessOfMemberNumber(memberNumber);

        memberDB.addMember(new Member(memberNumber, isPassiveMember, name, getDateFromString(dateOfBirth), phoneNumber, email));
    }

    public void addCompetitor(int memberNumber,
                              boolean isPassiveMember,
                              String name,
                              LocalDate dateOfBirth,
                              String phoneNumber,
                              String email,
                              List<Discipline> disciplines) {
        memberDB.addMember(new Competitor(memberNumber, isPassiveMember, name, dateOfBirth, phoneNumber, email, disciplines));
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

}
