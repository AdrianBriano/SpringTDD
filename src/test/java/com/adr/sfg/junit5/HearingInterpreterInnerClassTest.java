package com.adr.sfg.junit5;

import com.adr.sfg.HearingInterpreter;
import com.adr.sfg.LaurelWordProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("inner-class")
@SpringJUnitConfig(classes = HearingInterpreterInnerClassTest.TestConfig.class)
class HearingInterpreterInnerClassTest {

    @Profile("inner-class")
    @Configuration
    static class TestConfig {
        @Bean
        HearingInterpreter hearingInterpreter(){
            return new HearingInterpreter(new LaurelWordProducer());
        }
    }

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHeard() {
        String word = hearingInterpreter.whatIHeard();

        assertEquals(word, "Laurel");
    }
}