package com.sudijovski.fizzbuzz.condition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConditionFactoryTest {
    // there is no need to use the @SpringBootTest annotation because we do not need
    // the entire spring context upp and running

    private ConditionFactory conditionFactory;

    @BeforeEach
    void setUp() {
        this.conditionFactory = new ConditionFactory();
    }

    @Test
    void getCondition() {
        var condition = conditionFactory.getCondition(2, "test");
        assertEquals("", condition.process(1, false));
        assertEquals("test", condition.process(2, false));
        assertEquals("Test", condition.process(4, true));

        condition = conditionFactory.getCondition(5, "fiveTest");
        assertEquals("", condition.process(1, false));
        assertEquals("fiveTest", condition.process(5, false));
        assertEquals("FiveTest", condition.process(10, true));
    }
}
