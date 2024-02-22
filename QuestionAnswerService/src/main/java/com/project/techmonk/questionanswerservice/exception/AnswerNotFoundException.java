package com.project.techmonk.questionanswerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AnswerNotFoundException extends RuntimeException {
    public AnswerNotFoundException() {
        super("Answer not found!");
    }

}
