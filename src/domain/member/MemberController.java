package domain.member;

import database.MemberDatabase;
import domain.Controller;
import domain.Discipline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

//@author Sofia & Mathias
// TODO: 26/11/2021 make an edit function
//                  make delete function

//@author Sofia & Mathias
public class MemberController{

    private final MemberDatabase memberDB = new MemberDatabase();
    Scanner userInput = new Scanner(System.in);

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

    public LocalDate getDateFromString(String date) {
        // make a pattern to parse the given dates from
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // get the dates from the user and parse
        return LocalDate.parse(date.trim(), formatter);
    }

    public void checkUniquenessOfMemberNumber(int memberNumber) {
        for (Member member : getMembers()) {
            if (member.getMemberNumber() == memberNumber) {
                throw new IllegalArgumentException("That memberNumber is allready in use!");
            }
        }
    }

    public List<Member> getMembers() {
        return memberDB.getMembers();
    }

    public void deleteMember() {
        System.out.println("Her kan du se medlemmerne af svømmeklubben: " + memberDB.getMembers());
        System.out.println("Du bedes her indtaste medlemsnummer på det medlem du ønsker at slette: ");
        int memberNumber = userInput.nextInt();
        userInput.nextLine();

        Member member = memberDB.getMember(memberNumber);

        if (member == null){
            System.out.println("no member was found");
        }else {
            memberDB.deleteMember(member);
        }
    }
}
