package domain.member;

import domain.Discipline;
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

    @Test
    void createMemberTest_With1Member(){
    MemberController memberController = new MemberController();
    memberController.addMember(1,
            true,
            "Name",
            "25-10-1996",
            "53663634",
            "Test@gmail.com");
    assertEquals(memberController.getMembers().get(0).getMemberNumber(), 1);
    }

    @Test
    void createMemberTest_With2Member(){
        MemberController memberController = new MemberController();

        memberController.addMember(0,
                true,
                "Name",
                "25-10-1996",
                "53663634",
                "Test@gmail.com");

        memberController.addMember(1,
                true,
                "Name",
                "06-10-1996",
                "53663634",
                "Test@gmail.com");


        assertEquals(memberController.getMembers().get(0).getMemberNumber(), 0);
        assertEquals(memberController.getMembers().get(1).getMemberNumber(), 1);
    }

    //********************
    // *
    // * Create Competitor
    // *
    // *******************

    @Test
    void createCompetitorTest_With1Member(){
        MemberController memberController = new MemberController();

        ArrayList<Discipline> disciplines = new ArrayList<>();
        disciplines.add(Discipline.BREASTSTROKE);
        disciplines.add(Discipline.CRAWL);

        memberController.addCompetitor(1,
                true,
                "Name",
                "25-10-1996",
                "53663634",
                "Test@gmail.com",
                disciplines);



        assertTrue(memberController.getMembers().get(0) instanceof Competitor);
    }

    @Test
    void createCompetitorTest_With2Member(){
        MemberController memberController = new MemberController();

        ArrayList<Discipline> disciplines = new ArrayList<>();
        disciplines.add(Discipline.BREASTSTROKE);
        disciplines.add(Discipline.CRAWL);

        memberController.addMember(0,
                true,
                "Name",
                "25-10-1996",
                "53663634",
                "Test@gmail.com");

        memberController.addCompetitor(1,
                true,
                "Name",
                "06-10-1996",
                "53663634",
                "Test@gmail.com",
                disciplines);


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

        memberController.addMember(0,
                true,
                "Name",
                "25-10-1996",
                "53663634",
                "Test@gmail.com");

        memberController.addCompetitor(1,
                true,
                "Name",
                "06-10-1996",
                "53663634",
                "Test@gmail.com",
                disciplines);

        memberController.addTrainer(2,
                true,
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

    //***********************************
    // *
    // * Check uniqueness of memberNumber
    // *
    // **********************************

    @Test
    void uniquenessCheckTest_With2Member_Illegal(){
        MemberController memberController = new MemberController();

        memberController.addMember(0,
                true,
                "Name",
                "25-10-1996",
                "53663634",
                "Test@gmail.com");

        memberController.addMember(1,
                true,
                "Name",
                "06-10-1996",
                "53663634",
                "Test@gmail.com");

        int toCheck = 1;

        assertThrows(IllegalArgumentException.class, () -> memberController.checkUniquenessOfMemberNumber(toCheck));
    }

    @Test
    void uniquenessCheckTest_With2Member_legal(){
        MemberController memberController = new MemberController();

        memberController.addMember(0,
                true,
                "Name",
                "25-10-1996",
                "53663634",
                "Test@gmail.com");

        memberController.addMember(1,
                true,
                "Name",
                "06-10-1996",
                "53663634",
                "Test@gmail.com");

        int toCheck = 2;

        assertDoesNotThrow(() -> memberController.checkUniquenessOfMemberNumber(toCheck));
    }
}