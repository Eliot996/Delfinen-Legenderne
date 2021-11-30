package domain.team;
import org.junit.jupiter.api.Test;
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
    void createTeamTest_With1Team(){
    TeamController teamController = new TeamController();
    teamController.addTeam("name",
                           "description");
    assertNotNull(teamController.getTeams().get(0));
}

    // test for creating 2 teams
@Test
    void createTeamTest_With2Teams(){
    TeamController teamController = new TeamController();
    teamController.addTeam("name1",
                           "description1");

    teamController.addTeam("name2",
                           "description2");

    assertNotNull(teamController.getTeams().get(0));
    assertNotNull(teamController.getTeams().get(1));
}
}