package com.adr.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void groupAssertions() {
        //give
        Person person = new Person(1L, "Adrian", "Briano");

        //then
        assertAll("Test props set",
                () -> assertEquals("Adrian", person.getFirstName()),
                () -> assertEquals("Briano", person.getLastName()));
    }

    @Test
    void groupAssertions2() {
        //give
        Person person = new Person(1L, "Adrian", "Briano");

        //then
        assertAll("Test props set",
                () -> assertEquals("Adrian", person.getFirstName(), "First name failed"),
                () -> assertEquals("Briano", person.getLastName(),  "Last name failed"));
    }
}