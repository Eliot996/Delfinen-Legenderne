package domain.member;

import database.MemberDatabase;

import java.time.LocalDate;

public class MemberController{

    private MemberDatabase memberDB = new MemberDatabase();

    // TODO: 25/11/2021 add date parsing
    public void addMember(int memberNumber,
                          boolean isPassiveMember,
                          String name,
                          LocalDate dateOfBirth,
                          String phoneNumber,
                          String email) {
        memberDB.addMember(new Member(memberNumber, isPassiveMember, name, dateOfBirth, phoneNumber, email));
    }



}
