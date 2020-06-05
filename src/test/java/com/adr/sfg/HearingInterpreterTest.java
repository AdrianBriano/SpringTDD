package com.adr.sfg;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HearingInterpreterTest {

    HearingInterpreter hearingInterpreter;

    @Test
    public void whatIHeardLaurel() {

        hearingInterpreter = new HearingInterpreter(new LaurelWordProducer());

        String word = hearingInterpreter.whatIHeard();

        assertEquals(word, "Laurel");
    }

    @Test
    public void whatIHeardYanny() {

        hearingInterpreter = new HearingInterpreter(new YannyWordProducer());

        String word = hearingInterpreter.whatIHeard();

        assertEquals(word, "Yanny");
    }
}