package com.adr;

/**
 * Hello world!
 */
public class Greating {

    private static final String HELLO = "Hello";
    private static final String WORLD = "World";

    public String helloWorld() {
        return HELLO + " " + WORLD;
    }

    public String helloName(String name) {
        return HELLO + " " + name;
    }
}
