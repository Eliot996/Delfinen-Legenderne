package domain.member;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void constructorTest_Name(){
        Member member = new Member(1,
                false,
                "name",
                LocalDate.of(2020, 12, 24),
                "+4533445566",
                "test@gmail.com");

        assertEquals("name", member.getName());
    }

    @Test
    void constructorTest_memberNumber(){
        Member member = new Member(1,
                false,
                "name",
                LocalDate.of(2020, 12, 24),
                "+4533445566",
                "test@gmail.com");

        assertEquals(1, member.getMemberNumber());
    }

    @Test
    void constructorTest_IsPassiveMember(){
        Member member = new Member(1,
                false,
                "name",
                LocalDate.of(2020, 12, 24),
                "+4533445566",
                "test@gmail.com");

        assertFalse(member.isPassiveMember());
    }

    @Test
    void constructorTest_DOB(){
        Member member = new Member(1,
                false,
                "name",
                LocalDate.of(2020, 12, 24),
                "+4533445566",
                "test@gmail.com");

        assertEquals(LocalDate.of(2020, 12, 24), member.getDateOfBirth());
    }

    @Test
    void constructorTest_email(){
        Member member = new Member(1,
                false,
                "name",
                LocalDate.of(2020, 12, 24),
                "+4533445566",
                "test@gmail.com");

        assertEquals("test@gmail.com", member.getEmail());
    }

    @Test
    void methodTest_isSenior_over18(){
        Member member = new Member(1,
                false,
                "name",
                LocalDate.of(1996, 10, 25),
                "+4533445566",
                "test@gmail.com");

        assertTrue(member.isSenior());
    }

    @Test
    void methodTest_isSenior_under18(){
        Member member = new Member(1,
                false,
                "name",
                LocalDate.of(2020, 10, 25),
                "+4533445566",
                "test@gmail.com");

        assertFalse(member.isSenior());
    }
}