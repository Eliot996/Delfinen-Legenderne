package domain.member;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class MemberControllerTest {

    @Test
    void insertionTest_With1Member(){
    MemberController memberController = new MemberController();
    memberController.addMember(1,
            true,
            "Name",
            LocalDate.of(1996,10,25),
            "53663634",
            "Test@gmail.com");
    assertEquals(memberController.getMembers().get(0).getMemberNumber(), 1);
    }

    @Test
    void insertionTest_With2Member(){
        MemberController memberController = new MemberController();

        memberController.addMember(0,
                true,
                "Name",
                LocalDate.of(1996,10,25),
                "53663634",
                "Test@gmail.com");

        memberController.addMember(1,
                true,
                "Name",
                LocalDate.of(1996,10,25),
                "53663634",
                "Test@gmail.com");


        assertEquals(memberController.getMembers().get(0).getMemberNumber(), 0);
        assertEquals(memberController.getMembers().get(1).getMemberNumber(), 1);
    }

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


}