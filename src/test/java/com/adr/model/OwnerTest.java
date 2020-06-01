package com.adr.model;

import com.adr.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest implements ModelTests {

    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1L, "Adrian", "Briano");
        owner.setCity("Mexico");
        owner.setTelephone("1234567890");

        assertAll("Properties test",
                () -> assertAll("Person properties",
                        () -> assertEquals("Adrian", owner.getFirstName(), "First name did not match"),
                        () -> assertEquals("Briano", owner.getLastName())),
                () -> assertAll("Owner properties",
                        () -> assertEquals("Mexico", owner.getCity()),
                        () -> assertEquals("1234567890", owner.getTelephone()))
        );

        assertThat(owner.getCity(), is("Mexico"));
    }

    @DisplayName("Value source test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }



    @DisplayName("Enum source test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }
}