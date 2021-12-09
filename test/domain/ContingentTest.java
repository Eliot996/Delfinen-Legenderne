package domain;

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

}