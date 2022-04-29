package com.sudijovski.fizzbuzz.controller;

import com.sudijovski.fizzbuzz.editor.IntegerListEditor;
import com.sudijovski.fizzbuzz.service.FizzBuzzBazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class FizzBuzzBazzController {

    private final FizzBuzzBazzService fizzBuzzBazzService;

    // even though spring has its own binder for converting query params to lists
    // we do not want to expose the exception
    @InitBinder("entry")
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, new IntegerListEditor());
    }

    public FizzBuzzBazzController(@Autowired FizzBuzzBazzService fizzBuzzBazzService) {
        this.fizzBuzzBazzService = fizzBuzzBazzService;
    }

    @GetMapping(value = "/fizzbuzz")
    public List<String> fizzbuzz(@RequestParam(value = "entry", defaultValue = "", required = false) List<Integer> entries) {
        // there is no need to check for null because of the default value and the editor
        List<Integer> toProcess =
                !entries.isEmpty() ? entries : IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        return fizzBuzzBazzService.fizzbuzz(toProcess);
    }
}
