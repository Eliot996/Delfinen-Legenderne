package domain;

import database.FileHandler;
import domain.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ContingentTest {

    private Controller mc;

    @BeforeEach
    void setup() {
        mc = new Controller();
        mc.initializaData();
    }

    @Test
    void TestOfChargeCSVConstructor() {
        Contingent contingent = new Contingent(mc);

        ArrayList<String> csv = new ArrayList<>();
        csv.add("hej");
        csv.add("1600;" +
                mc.getMember("d54f8ec5-4538-4727-944f-087f39cdd9cd").getMemberID() + ";" +
                LocalDate.of(2021,12,1) + ";" +
                false);

        contingent.initCharges(csv);

        assertNotNull( contingent.getCharge(contingent.getAmountOfCharges() - 1));
    }

    // test om de får rigtige kontingent.
    @Test
    void TestIfMemberGetsTheCorrectContingent_Junior() {
        Contingent contingent = new Contingent(mc);
        contingent.initContingents(FileHandler.getCONTINGENTFromFile());

        Member member = new Member(false,"navn", LocalDate.now().minusYears(10),"12345678", "testmail@gmail.com");
        contingent.addCharge(member);

        assertEquals(1000, contingent.getChargeAmount(0));
    }

    @Test
    void TestIfMemberGetsTheCorrectContingent_Senior() {
        Contingent contingent = new Contingent(mc);
        contingent.initContingents(FileHandler.getCONTINGENTFromFile());

        Member member = new Member(false,"navn", LocalDate.now().minusYears(20),"12345678", "testmail@gmail.com");
        contingent.addCharge(member);

        assertEquals(1600, contingent.getChargeAmount(0));
    }

    @Test
    void TestIfMemberGetsTheCorrectContingent_Pensioner() {
        Contingent contingent = new Contingent(mc);
        contingent.initContingents(FileHandler.getCONTINGENTFromFile());

        Member member = new Member(false,"navn", LocalDate.now().minusYears(70),"12345678", "testmail@gmail.com");
        contingent.addCharge(member);

        assertEquals(1200, contingent.getChargeAmount(0));
    }

    @Test
    void TestIfMemberGetsTheCorrectContingent_Passive() {
        Contingent contingent = new Contingent(mc);
        contingent.initContingents(FileHandler.getCONTINGENTFromFile());

        Member member = new Member(true,"navn", LocalDate.now().minusYears(20),"12345678", "testmail@gmail.com");
        contingent.addCharge(member);

        assertEquals(500, contingent.getChargeAmount(0));
    }
}