package com.sudijovski.fizzbuzz.editor;

import com.sudijovski.fizzbuzz.exception.FizzBuzzBazzBadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListEditorTest {

    private IntegerListEditor integerListEditor;

    @BeforeEach
    void setUp() {
        integerListEditor = new IntegerListEditor();
    }

    @Test
    void setAsText() {
        integerListEditor.setAsText("1,2,3,4");
        assertEquals(List.of(1, 2, 3, 4), integerListEditor.getValue());

        integerListEditor.setAsText("");
        assertEquals(Collections.emptyList(), integerListEditor.getValue());

        integerListEditor.setAsText(null);
        assertEquals(Collections.emptyList(), integerListEditor.getValue());

        assertThrows(FizzBuzzBazzBadRequestException.class, () -> integerListEditor.setAsText("123,notInt"));

        assertThrows(FizzBuzzBazzBadRequestException.class, () -> integerListEditor.setAsText("123, 23"));
    }
}
