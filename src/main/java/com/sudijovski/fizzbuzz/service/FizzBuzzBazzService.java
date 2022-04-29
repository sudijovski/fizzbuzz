package com.sudijovski.fizzbuzz.service;

import com.sudijovski.fizzbuzz.condition.Condition;
import com.sudijovski.fizzbuzz.condition.ConditionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FizzBuzzBazzService {
    public static final int SEVEN = 7;

    private final List<Condition> conditions;

    public FizzBuzzBazzService(@Autowired ConditionFactory conditionFactory) {
        this.conditions = List.of(
                conditionFactory.getCondition(3, "fizz"),
                conditionFactory.getCondition(5, "buzz"),
                conditionFactory.getCondition(7, "bazz")
        );
    }

    public List<String> fizzbuzz(List<Integer> entries) {
        return entries.stream().map(entry -> {
            StringBuilder sb = new StringBuilder();
            // if the number is multiple of 7 we should start with capital letters
            boolean capitalize = entry % SEVEN == 0;
            conditions.forEach(condition -> sb.append(condition.process(entry, capitalize)));
            // if sb is empty append the number itself
            sb.append(sb.length() == 0 ? entry : "");
            return sb.toString();
        }).collect(Collectors.toList());
    }
}
