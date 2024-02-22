package com.project.techmonk.questionanswerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class QuestionVoteAlreadyRemovedException extends RuntimeException {
    public QuestionVoteAlreadyRemovedException() {
        super("Question vote already removed!");
    }
}
