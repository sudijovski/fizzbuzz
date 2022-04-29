package com.sudijovski.fizzbuzz.condition;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ConditionFactory {
    public Condition getCondition(int multipleOf, String result) {
        return (number, capitalized) -> {
            if (number % multipleOf != 0) {
                return "";
            }
            return capitalized ? StringUtils.capitalize(result) : result;
        };
    }
}
