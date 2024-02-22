package com.project.techmonk.questionanswerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AnswerVoteAlreadyRemovedException extends RuntimeException {
    public AnswerVoteAlreadyRemovedException() {
        super("Answer vote already removed!");
    }
}
