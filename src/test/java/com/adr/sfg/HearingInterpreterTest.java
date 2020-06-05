package com.adr.sfg;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HearingInterpreterTest {

    HearingInterpreter hearingInterpreter;

    @Before
    public void setUp() throws Exception {
        hearingInterpreter = new HearingInterpreter(new LaurelWordProducer());
    }

    @Test
    public void whatIHeard() {
        String word = hearingInterpreter.whatIHeard();

        assertEquals(word, "Laurel");
    }
}