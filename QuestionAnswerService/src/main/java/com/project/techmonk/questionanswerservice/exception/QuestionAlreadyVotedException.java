package com.project.techmonk.questionanswerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class QuestionAlreadyVotedException extends RuntimeException {
    public QuestionAlreadyVotedException () {
        super("Question already voted!");
    }
}
