package com.sudijovski.fizzbuzz.editor;

import com.sudijovski.fizzbuzz.exception.FizzBuzzBazzBadRequestException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IntegerListEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.hasLength(text)) {
            setValue(Collections.emptyList());
            return;
        }
        List<Integer> result = Arrays.stream(text.split(","))
                .mapToInt(s -> {
                    if (NumberUtils.isParsable(s)) {
                        return Integer.parseInt(s);
                    } else {
                        throw new FizzBuzzBazzBadRequestException(String.format("Cannot convert \"%s\" to integer", s));
                    }
                })
                .boxed()
                .collect(Collectors.toList());
        setValue(result);
    }
}
