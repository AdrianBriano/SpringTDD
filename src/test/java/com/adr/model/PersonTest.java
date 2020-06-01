package com.adr.model;

import com.adr.ModelTests;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTests {

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
                () -> assertEquals("Briano", person.getLastName(), "Last name failed"));
    }


}