package com.sudijovski.fizzbuzz.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@RestControllerAdvice
public class FizzBuzzBazzAdviceTrait implements ProblemHandling {
    @ExceptionHandler
    ResponseEntity<Problem> handleBadRequest(final FizzBuzzBazzBadRequestException exception,
                                                   final NativeWebRequest request) {
        return create(Status.BAD_REQUEST, exception, request);
    }
}
