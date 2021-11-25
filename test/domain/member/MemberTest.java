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
                "+4533445566");

        assertEquals("name", member.getName());
    }

    @Test
    void constructorTest_memberNumber(){
        Member member = new Member(1,
                false,
                "name",
                LocalDate.of(2020, 12, 24),
                "+4533445566");

        assertEquals(1, member.getMemberNumber());
    }

    @Test
    void constructorTest_IsPassiveMember(){
        Member member = new Member(1,
                false,
                "name",
                LocalDate.of(2020, 12, 24),
                "+4533445566");

        assertFalse(member.isPassiveMember());
    }

    @Test
    void constructorTest_DOB(){
        Member member = new Member(1,
                false,
                "name",
                LocalDate.of(2020, 12, 24),
                "+4533445566");

        assertEquals(LocalDate.of(2020, 12, 24), member.getDateOfBirth());
    }
}