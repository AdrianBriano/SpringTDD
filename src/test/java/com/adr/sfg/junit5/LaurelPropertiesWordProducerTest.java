package com.adr.sfg.junit5;

import com.adr.sfg.HearingInterpreter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("classpath:laurel.properties")
@ActiveProfiles("laurel-properties")
@SpringJUnitConfig(classes = LaurelPropertiesWordProducerTest.TestConfig.class)
class LaurelPropertiesWordProducerTest {

    @Profile("laurel-properties")
    @ComponentScan("com.adr.sfg")
    static class TestConfig {
    }

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    public void test(){
        String word = hearingInterpreter.whatIHeard();

        assertEquals(word, "LaUrEl");

    }


}