package com.sudijovski.fizzbuzz;

import com.sudijovski.fizzbuzz.condition.ConditionFactory;
import com.sudijovski.fizzbuzz.controller.FizzBuzzBazzController;
import com.sudijovski.fizzbuzz.exception.FizzBuzzBazzAdviceTrait;
import com.sudijovski.fizzbuzz.service.FizzBuzzBazzService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FizzBuzzApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ConditionFactory conditionFactory;

    @Autowired
    private FizzBuzzBazzController fizzBuzzBazzController;

    @Autowired
    private FizzBuzzBazzAdviceTrait adviceTrait;

    @Autowired
    private FizzBuzzBazzService fizzBuzzBazzService;

    @Test
    void contextLoads() {
        assertThat(conditionFactory).isNotNull();
        assertThat(fizzBuzzBazzController).isNotNull();
        assertThat(adviceTrait).isNotNull();
        assertThat(fizzBuzzBazzService).isNotNull();
    }

    @Test
    void fizzbuzz() throws Exception {
        mvc.perform(get("/fizzbuzz").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        mvc.perform(get("/fizzbuzz?entry=").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        var expectedResult = List.of("1", "2", "fizz", "buzz", "Bazz", "fizzbuzz", "FizzBazz", "BuzzBazz", "FizzBuzzBazz");
        mvc.perform(get("/fizzbuzz?entry=1,2,3,5,7,15,21,35,105").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", is(expectedResult)));

        mvc.perform(get("/fizzbuzz?entry=1,notInt").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.title", is("Bad Request")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.detail", is("Cannot convert 'notInt' to integer")));

    }

}
