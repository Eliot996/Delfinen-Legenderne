package domain.member;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TrainerTest {
    @Test
    void constructerTest_isPassiveMember() {
        Trainer trainer = new Trainer(true,
                "name",
                LocalDate.of(2020, 12, 24),
                "12345678",
                "Test@mail.com");
        assertTrue(trainer.isPassiveMember());
    }
    @Test
    void constructerTest_Name() {
        Trainer trainer = new Trainer(true,
                "name",
                LocalDate.of(2020, 12, 24),
                "12345678",
                "Test@mail.com");
        assertEquals("name", trainer.getName());
    }
    @Test
    void constructorTest_DateOfBirth(){
        Trainer trainer = new Trainer(true,
                "name",
                LocalDate.of(2020, 12, 24),
                "12345678",
                "test@mail.com");
        assertEquals(LocalDate.of(2020, 12, 24), trainer.getDateOfBirth());
    }
    @Test
    void constructorTest_PhoneNumber(){
        Trainer trainer = new Trainer(true,
                "name",
                LocalDate.of(2020, 12, 24),
                "12345678",
                "test@mail.com");
        assertEquals("12345678", trainer.getPhoneNumber());
    }
    @Test
    void constructorTest_Email(){
        Trainer trainer = new Trainer(true,
                "name",
                LocalDate.of(2020, 12, 24),
                "12345678",
                "test@mail.com");
        assertEquals("test@mail.com", trainer.getEmail());
    }

}