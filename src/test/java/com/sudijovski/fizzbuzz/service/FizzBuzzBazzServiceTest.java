package com.sudijovski.fizzbuzz.service;

import com.sudijovski.fizzbuzz.condition.ConditionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzBazzServiceTest {

    private FizzBuzzBazzService fizzBuzzBazzService;

    @BeforeEach
    void setUp() {
        this.fizzBuzzBazzService = new FizzBuzzBazzService(new ConditionFactory());
    }

    @Test
    void fizzbuzz() {
        assertEquals(List.of("1", "2", "fizz", "buzz", "Bazz", "fizzbuzz", "FizzBazz", "BuzzBazz", "FizzBuzzBazz"),
                fizzBuzzBazzService.fizzbuzz(List.of(1, 2, 3, 5, 7, 15, 21, 35, 105)));
    }
}
