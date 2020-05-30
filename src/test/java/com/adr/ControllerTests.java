package com.adr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@Tag("controller")
public interface ControllerTests {

    @BeforeAll
    default void beforeAll(){
        System.out.println("Lets do something here");
    }

}
