package domain.result;

import domain.Discipline;
import domain.member.Competitor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {
    @Test
    void testOfConstructor() {
        Competitor competitor = new Competitor(true,
                "name",
                LocalDate.of(1212, 12 ,12),
                "+12342134",
                "maiil.com");

        Result result = new Result(competitor,
                LocalTime.of(0,10,55),
                LocalDate.of(1994,12,12),
                null,
                Discipline.CRAWL);

        assertEquals(competitor, result.getCompetitor());
    }

    @Test
    void toCSVTest() {
        Competitor competitor = new Competitor(true,
                "name",
                LocalDate.of(1212, 12 ,12),
                "+12342134",
                "maiil.com");

        Result result = new Result(competitor,
                LocalTime.of(0,10,55),
                LocalDate.of(1994,12,12),
                null,
                Discipline.CRAWL);

        String expected = result.getId() + ";" +
                competitor.getMemberID() + ";" +
                LocalTime.of(0,10,55) + ";" +
                LocalDate.of(1994,12,12) + ";" +
                "null;CRAWL";

        assertEquals(expected, result.toCSV());
    }
}