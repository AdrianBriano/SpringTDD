package com.adr.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.text.resources.no.CollationData_no;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @Test
    void index() {
        assertEquals("index", controller.index());
        assertEquals("index", controller.index(), "Wrong view returned");
        assertEquals("index", controller.index(), () -> "Another expensive message, " +
                "make me only if you have to");
    }

    @Test
    void oupsHandler() {
        assertTrue(("notimplemented").equals(controller.oupsHandler()), () -> "Wrong view returned" +
                "Message to build  for my test");

    }
}