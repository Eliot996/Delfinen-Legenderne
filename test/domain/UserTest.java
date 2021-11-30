package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void createTest_userName() {
        User user = new User("Name", "password", Roles.ADMIN);

        assertEquals("Name", user.getUsername());
    }

    @Test
    void createTest_userPassword() {
        User user = new User("Name", "password", Roles.ADMIN);

        assertTrue(user.matchPassword("password"));
    }

    @Test
    void createTest_userRole() {
        User user = new User("Name", "password", Roles.ADMIN);

        assertEquals(Roles.ADMIN, user.getRole());
    }

    @Test
    void userTest_changePassword() {
        User user = new User("Name", "somethingelse", Roles.ADMIN);

        user.setPassword("password");

        assertTrue(user.matchPassword("password"));
    }

    @Test
    void userTest_changeRole() {
        User user = new User("Name", "password", Roles.ADMIN);

        user.setRole(Roles.TRAINER);

        assertEquals(Roles.TRAINER, user.getRole());
    }

}