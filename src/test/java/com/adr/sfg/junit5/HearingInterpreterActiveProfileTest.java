package com.adr.sfg.junit5;

import com.adr.sfg.HearingInterpreter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("yanny")
@SpringJUnitConfig(classes = HearingInterpreterActiveProfileTest.TestConfig.class)
class HearingInterpreterActiveProfileTest {

    @Configuration
    @ComponentScan("com.adr.sfg")
    static class TestConfig{
    }

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHeard() {
        String word = hearingInterpreter.whatIHeard();

        assertEquals(word, "Yanny");
    }

}