package domain.member;

import domain.Discipline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MemberControllerTest {

    //@author Sofia & Mathias

    //****************
    // *
    // * Create member
    // *
    // ***************

    private MemberController memberController;

    @BeforeEach
    public  void setup(){
        memberController = new MemberController();
    }

    @Test
    void createMemberTest_With1Member() {
    memberController.addMember(true,
            "Name",
            "25-10-1996",
            "53663634",
            "Test@gmail.com");
    assertNotNull(memberController.getMembers().get(0));
    }

    @Test
    void createMemberTest_With2Member(){
        memberController.addMember(true,
                "Name",
                "25-10-1996",
                "53663634",
                "Test@gmail.com");

        memberController.addMember(true,
                "Name",
                "06-10-1996",
                "53663634",
                "Test@gmail.com");


        assertNotNull(memberController.getMembers().get(0));
        assertNotNull(memberController.getMembers().get(1));
        assertEquals(memberController.getMembers().get(0).getDateOfBirth(), LocalDate.of(1996,10,25));
        assertEquals(memberController.getMembers().get(1).getDateOfBirth(), LocalDate.of(1996,10,6));
    }

    //********************
    // *
    // * Create Competitor
    // *
    // *******************

    @Test
    void createCompetitorTest_With1Member(){
        MemberController memberController = new MemberController();

        memberController.addCompetitor(true,
                "Name",
                "25-10-1996",
                "53663634",
                "Test@gmail.com");



        assertTrue(memberController.getMembers().get(0) instanceof Competitor);
    }

    @Test
    void createCompetitorTest_With2Member(){
        MemberController memberController = new MemberController();

        ArrayList<Discipline> disciplines = new ArrayList<>();
        disciplines.add(Discipline.BREASTSTROKE);
        disciplines.add(Discipline.CRAWL);

        memberController.addMember(true,
                "Name",
                "25-10-1996",
                "53663634",
                "Test@gmail.com");

        memberController.addCompetitor(true,
                "Name",
                "06-10-1996",
                "53663634",
                "Test@gmail.com");


        assertTrue(memberController.getMembers().get(0) instanceof Member);
        assertTrue(memberController.getMembers().get(1) instanceof Competitor);
    }

    //********************
    // *
    // * Create Competitor
    // *
    // *******************

    @Test
    void createTrainerTest_With2Member() {
        MemberController memberController = new MemberController();

        ArrayList<Discipline> disciplines = new ArrayList<>();
        disciplines.add(Discipline.BREASTSTROKE);
        disciplines.add(Discipline.CRAWL);

        memberController.addMember(true,
                "Name",
                "25-10-1996",
                "53663634",
                "Test@gmail.com");

        memberController.addCompetitor(true,
                "Name",
                "06-10-1996",
                "53663634",
                "Test@gmail.com");

        memberController.addTrainer(true,
                "Trainer",
                "06-10-1996",
                "53663634",
                "trainer@gmail.com");


        assertTrue(memberController.getMembers().get(0) instanceof Member);
        assertTrue(memberController.getMembers().get(1) instanceof Competitor);
        assertTrue(memberController.getMembers().get(2) instanceof Trainer);
    }

    //**************************
    // *
    // * Parse date from string
    // *
    // *************************

    @Test
    void dateParserTest_legal() {
        MemberController memberController = new MemberController();

        String dateToParse = "06-10-1996";
        LocalDate expected = LocalDate.of(1996, 10, 6);

        LocalDate result = memberController.getDateFromString(dateToParse);

        assertEquals(expected, result);
    }

    @Test
    void dateParserTest_illegal() {
        MemberController memberController = new MemberController();

        String dateToParse = "06/10/1996";

        assertThrows(DateTimeParseException.class, () -> memberController.getDateFromString(dateToParse));
    }

    @Test
    void dateParserTest_reverseOrder() {
        MemberController memberController = new MemberController();

        String dateToParse = "1996-10-06";

        assertThrows(DateTimeParseException.class, () -> memberController.getDateFromString(dateToParse));
    }

    //********************
    // *
    // * edit member
    // *
    // *******************

    @Test
    void editMemberTest_Name(){
        MemberController memberController = new MemberController();
        String current = "Name";
        String changeTo = "Changed";

        memberController.addMember(true,
                current,
                "25-10-1996",
                "53663634",
                "Test@gmail.com");


        assertEquals(current, memberController.getMembers().get(0).getName());

        memberController.editMember(0,"name", changeTo);

        assertEquals(changeTo, memberController.getMembers().get(0).getName());
    }

    @Test
    void editMemberTest_isPassive(){
        MemberController memberController = new MemberController();
        String current = "false";
        String changeTo = "true";

        memberController.addMember(Boolean.parseBoolean(current),
                "name",
                "25-10-1996",
                "53663634",
                "Test@gmail.com");


        assertFalse(memberController.getMembers().get(0).isPassiveMember());

        memberController.editMember(0,"isPassive", changeTo);

        assertTrue(memberController.getMembers().get(0).isPassiveMember());
    }

    @Test
    void editMemberTest_DOB(){
        MemberController memberController = new MemberController();
        String current = "25-10-1996";
        String changeTo = "26-10-1996";

        memberController.addMember(false,
                "name",
                current,
                "53663634",
                "Test@gmail.com");


        assertEquals(memberController.getDateFromString(current),memberController.getMembers().get(0).getDateOfBirth());

        memberController.editMember(0,"dateOfBirth", changeTo);

        assertEquals(memberController.getDateFromString(changeTo),memberController.getMembers().get(0).getDateOfBirth());
    }

    @Test
    void editMemberTest_phoneNumber(){
        MemberController memberController = new MemberController();
        String current = "phone number";
        String changeTo = "Changed phone number";

        memberController.addMember(true,
                "name",
                "25-10-1996",
                current,
                "Test@gmail.com");


        assertEquals(current, memberController.getMembers().get(0).getPhoneNumber());

        memberController.editMember(0,"phoneNumber", changeTo);

        assertEquals(changeTo, memberController.getMembers().get(0).getPhoneNumber());
    }

    @Test
    void editMemberTest_email(){
        MemberController memberController = new MemberController();
        String current = "email";
        String changeTo = "Changed email";

        memberController.addMember(true,
                "name",
                "25-10-1996",
                "phone number",
                current);


        assertEquals(current, memberController.getMembers().get(0).getEmail());

        memberController.editMember(0,"email", changeTo);

        assertEquals(changeTo, memberController.getMembers().get(0).getEmail());
    }

    @Test
    void editCompetitorTest_addDisciplne(){
        MemberController memberController = new MemberController();

        ArrayList<Discipline> current = new ArrayList<>();
        current.add(Discipline.BREASTSTROKE);
        current.add(Discipline.CRAWL);

        String toAdd = "backcrawl";

        ArrayList<Discipline> changeTo = new ArrayList<>();
        changeTo.add(Discipline.BREASTSTROKE);
        changeTo.add(Discipline.CRAWL);
        changeTo.add(Discipline.BACKCRAWL);

        memberController.addCompetitor(true,
                "Name",
                "25-10-1996",
                "53663634",
                "Test@gmail.com");

        Competitor competitor = (Competitor) memberController.getMembers().get(0);

        competitor.addDisciplines(Discipline.BREASTSTROKE);
        competitor.addDisciplines(Discipline.CRAWL);

        assertEquals(current, competitor.getDisciplines());

        memberController.editMember(0,"add discipline", toAdd);

        assertEquals(changeTo, competitor.getDisciplines());
    }

    @Test
    void editCompetitorTest_removeDisciplne(){
        MemberController memberController = new MemberController();

        ArrayList<Discipline> current = new ArrayList<>();
        current.add(Discipline.BREASTSTROKE);
        current.add(Discipline.CRAWL);

        String toRemove = "crawl";

        ArrayList<Discipline> changeTo = new ArrayList<>();
        changeTo.add(Discipline.BREASTSTROKE);

        memberController.addCompetitor(true,
                "Name",
                "25-10-1996",
                "53663634",
                "Test@gmail.com");

        Competitor competitor = (Competitor) memberController.getMembers().get(0);

        competitor.addDisciplines(Discipline.BREASTSTROKE);
        competitor.addDisciplines(Discipline.CRAWL);

        assertEquals(current, competitor.getDisciplines());

        memberController.editMember(0,"remove discipline", toRemove);

        assertEquals(changeTo, competitor.getDisciplines());
    }

}