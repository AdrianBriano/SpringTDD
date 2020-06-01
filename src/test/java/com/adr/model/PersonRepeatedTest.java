package com.adr.model;

import com.adr.ModelRepeatedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public class PersonRepeatedTest implements ModelRepeatedTest {

    @RepeatedTest(value = 10, name = "{displayName}: {currentRepetition} - {totalRepetitions}")
    @DisplayName("My repeated test")
    void myRepeatedTest() {

    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
    }

    @RepeatedTest(value = 5, name = "{displayName}: {currentRepetition} - {totalRepetitions}")
    @DisplayName("My custom repeated test")
    void myCustomRepeatedTest(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("Inside test " + testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
    }
}
