package com.adr.sfg.junit5;

import com.adr.sfg.BaseConfig;
import com.adr.sfg.HearingInterpreter;
import com.adr.sfg.YannyConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {BaseConfig.class, YannyConfig.class})
class HearingInterpreterYannyTest {

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHeard() {

        String word = hearingInterpreter.whatIHeard();

        assertEquals(word, "Yanny");

    }
}