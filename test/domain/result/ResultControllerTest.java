package domain.result;

import domain.Discipline;
import domain.member.Competitor;
import domain.member.MemberController;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResultControllerTest {

    @Test
    void CSVConstructorTest_WithoutCompetition() {
        MemberController memberController = new MemberController();
        ResultController resultController = new ResultController(memberController);
        ArrayList<String> arrayList = new ArrayList<>();

        Competitor competitor = new Competitor(true,
                "name",
                LocalDate.of(1212, 12 ,12),
                "+12342134",
                "maiil.com");
        memberController.addCompetitor(competitor);

        Result result = new Result(competitor,
                LocalTime.of(0,10,55),
                LocalDate.of(1994,12,12),
                null,
                Discipline.CRAWL);

        String toCreateFrom = result.getId() + ";" +
                competitor.getMemberID() + ";" +
                LocalTime.of(0,10,55) + ";" +
                LocalDate.of(1994,12,12) + ";" +
                "null;CRAWL";

        arrayList.add(toCreateFrom);

        resultController.initResults(arrayList);

        assertEquals(result.getId(), resultController.getResult(0).getId());
        assertEquals(result.getCompetitor(), resultController.getResult(0).getCompetitor());
    }

    @Test
    void CSVConstructorTest_WithCompetition(){
        MemberController memberController = new MemberController();
        ResultController resultController = new ResultController(memberController);
        ArrayList<String> arrayList = new ArrayList<>();

        Competitor competitor = new Competitor(true,
                "name",
                LocalDate.of(1212, 12 ,12),
                "+12342134",
                "maiil.com");
        memberController.addCompetitor(competitor);


        Competition competition = new Competition("name",
                "desc",
                LocalDateTime.of(199,12,12,12,12),
                Discipline.CRAWL);

        resultController.addCompetition(competition);

        Result result = new Result(competitor,
                LocalTime.of(0,10,55),
                LocalDate.of(1994,12,12),
                competition,
                Discipline.CRAWL);

        String toCreateFrom = result.getId() + ";" +
                competitor.getMemberID() + ";" +
                LocalTime.of(0,10,55) + ";" +
                LocalDate.of(1994,12,12) + ";" +
                competition.getId() + ";" +
                "CRAWL";

        arrayList.add(toCreateFrom);

        resultController.initResults(arrayList);

        assertEquals(result.getId(), resultController.getResult(0).getId());
        assertEquals(competition, resultController.getResult(0).getCompetition());
    }

}