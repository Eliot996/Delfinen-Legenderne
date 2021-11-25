package domain.member;

import database.MemberDatabase;
import domain.Discipline;

import java.time.LocalDate;
import java.util.List;

public class MemberController{

    private final MemberDatabase memberDB = new MemberDatabase();

    // TODO: 25/11/2021 add date parsing and test uniqness of membernumber
    public void addMember(int memberNumber,
                          boolean isPassiveMember,
                          String name,
                          LocalDate dateOfBirth,
                          String phoneNumber,
                          String email) {
        memberDB.addMember(new Member(memberNumber, isPassiveMember, name, dateOfBirth, phoneNumber, email));
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

    public List<Member> getMembers(){
        return memberDB.getMembers();
    }

}
