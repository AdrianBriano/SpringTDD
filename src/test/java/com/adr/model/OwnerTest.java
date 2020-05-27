package com.adr.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

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
    }
}