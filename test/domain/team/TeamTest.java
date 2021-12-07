package domain.team;
import domain.member.Member;
import domain.member.Trainer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

//@Author Sofia
class TeamTest {

    @Test
    void constructorTest_Name(){
        Team team = new Team("name",
                             "description");
        assertEquals("name",team.getName());
    }

    @Test
    void constructorTest_Description(){
        Team team = new Team("name",
                             "description");
        assertEquals("description",team.getDescription());
    }

    @Test
    void testOfCSV_Single() {
        // make team
        Team team = new Team("name",
                "description");

        // add trainer to team
        Trainer trainer1 = new Trainer(true,
                "TrainerName",
                LocalDate.of(2222, 11, 1),
                "+12340878734",
                "mail");
        team.addTrainer(trainer1);

        // add member to team
        Member member1 = new Member(true,
                "Name1",
                LocalDate.of(2222, 11, 1),
                "+12340878734",
                "mail");
        team.addMember(member1);

        System.out.println(team.toCSV());

        String expected = team.getName() + ";" +
                team.getDescription() + ";" +
                trainer1.getMemberID() + ";" +
                member1.getMemberID();

        assertEquals(expected, team.toCSV());
    }

    @Test
    void testOfCSV_Multi() {
        // make team
        Team team = new Team("name",
                "description");

        // add trainers to team
        Trainer trainer1 = new Trainer(true,
                "TrainerName",
                LocalDate.of(2222, 11, 1),
                "+12340878734",
                "mail");
        team.addTrainer(trainer1);

        Trainer trainer2 = new Trainer(true,
                "TrainerName2",
                LocalDate.of(2222, 11, 1),
                "+12340878734",
                "mail");
        team.addTrainer(trainer2);

        // add members to team
        Member member1 = new Member(true,
                "Name1",
                LocalDate.of(2222, 11, 1),
                "+12340878734",
                "mail");
        team.addMember(member1);

        Member member2 = new Member(true,
                "Name2",
                LocalDate.of(2222, 11, 1),
                "+12340878734",
                "mail");
        team.addMember(member2);

        System.out.println(team.toCSV());

        String expected = team.getName() + ";" +
                team.getDescription() + ";" +
                trainer1.getMemberID() + ":" + trainer2.getMemberID() + ";" +
                member1.getMemberID() + ":" + member2.getMemberID();

        assertEquals(expected, team.toCSV());
    }

    @Test
    void testOfCSVConstructor_Single(){
        Team team = new Team("name;description;671926ae-3e5d-4f5b-9e89-d7dcf042f0f3;602b8587-1a37-452a-88f3-bdada6e87b47");

        assertEquals("name", team.getName());
        assertEquals("description", team.getDescription());
    }

    @Test
    void testOfCSVConstructor_multi(){
        Team team = new Team("name;description;783fcb87-e283-4ec0-b46a-113d4eb384af:1a43b1a5-a11a-409e-8dc5-6bb5ea3b0cf1;fd049d78-0175-4c68-8878-e319c8ff518b:fe41e1e5-cf98-4faf-9f61-36d4a457d563");

        assertEquals("name", team.getName());
        assertEquals("description", team.getDescription());
    }


}