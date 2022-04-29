package com.sudijovski.fizzbuzz;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.zalando.problem.jackson.ProblemModule;

@SpringBootApplication(
        exclude = ErrorMvcAutoConfiguration.class
)
public class FizzBuzzApplication {

    public static void main(String[] args) {
        SpringApplication.run(FizzBuzzApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        objectMapper.registerModule(new ProblemModule());

        return objectMapper;
    }

}
