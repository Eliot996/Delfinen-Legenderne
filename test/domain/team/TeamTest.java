package domain.team;
import org.junit.jupiter.api.Test;

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
}