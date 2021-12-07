package domain.team;

import domain.member.Member;
import domain.member.MemberController;
import domain.member.Trainer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// @Author Sofia

class TeamControllerTest {

    //****************
    // *
    // * Create Team
    // *
    // ***************

    // test for creating 1 team
    @Test
    void createTeamTest_With1Team() {
        TeamController teamController = new TeamController(new MemberController());
        teamController.addTeam("name",
                "description");
        assertNotNull(teamController.getTeams().get(0));
    }

    // test for creating 2 teams
    @Test
    void createTeamTest_With2Teams() {
        TeamController teamController = new TeamController(new MemberController());
        teamController.addTeam("name1",
                "description1");

        teamController.addTeam("name2",
                "description2");

        assertNotNull(teamController.getTeams().get(0));
        assertNotNull(teamController.getTeams().get(1));
    }

    @Test
    void testInitTeams_NoMembersOrTrainers() {
        MemberController memberController = new MemberController();
        TeamController teamController = new TeamController(memberController);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("name;description;;");

        teamController.initTeams(strings);

        assertNotNull(teamController.getTeamFromIndex(0));
    }

    @Test
    void testInitTeams_SingleMemberAndTrainer() {
        MemberController memberController = new MemberController();
        TeamController teamController = new TeamController(memberController);

        Member member1 = new Member(true,
                "Name1",
                LocalDate.of(2222, 11, 1),
                "+12340878734",
                "mail");
        memberController.addMember(member1);

        Trainer trainer1 = new Trainer(true,
                "TrainerName2",
                LocalDate.of(2222, 11, 1),
                "+12340878734",
                "mail");
        memberController.addTrainer(trainer1);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("name;description;" + trainer1.getMemberID() + ";" + member1.getMemberID());

        teamController.initTeams(strings);

        assertEquals(teamController.getTeamFromIndex(0).getTrainers().get(0), trainer1);
        assertEquals(teamController.getTeamFromIndex(0).getMembers().get(0), member1);
    }

    @Test
    void testInitTeams_MultipleMembersAndTrainers() {
        MemberController memberController = new MemberController();
        TeamController teamController = new TeamController(memberController);

        Member member1 = new Member(true,
                "Name1",
                LocalDate.of(2222, 11, 1),
                "+12340878734",
                "mail");
        memberController.addMember(member1);

        Member member2 = new Member(true,
                "Name1",
                LocalDate.of(2222, 11, 1),
                "+12340878734",
                "mail");
        memberController.addMember(member2);

        Trainer trainer1 = new Trainer(true,
                "TrainerName1",
                LocalDate.of(2222, 11, 1),
                "+12340878734",
                "mail");
        memberController.addTrainer(trainer1);

        Trainer trainer2 = new Trainer(true,
                "TrainerName1",
                LocalDate.of(2222, 11, 1),
                "+12340878734",
                "mail");
        memberController.addTrainer(trainer2);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("name;description;" +
                trainer1.getMemberID() + ":" + trainer2.getMemberID() + ";" +
                member1.getMemberID() + ":" + member2.getMemberID());

        teamController.initTeams(strings);

        assertEquals(teamController.getTeamFromIndex(0).getTrainers().get(0), trainer1);
        assertEquals(teamController.getTeamFromIndex(0).getTrainers().get(1), trainer2);

        assertEquals(teamController.getTeamFromIndex(0).getMembers().get(0), member1);
        assertEquals(teamController.getTeamFromIndex(0).getMembers().get(1), member2);
    }
}