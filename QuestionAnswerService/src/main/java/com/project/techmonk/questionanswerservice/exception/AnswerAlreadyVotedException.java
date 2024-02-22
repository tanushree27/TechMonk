package com.project.techmonk.questionanswerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AnswerAlreadyVotedException extends RuntimeException {
    public AnswerAlreadyVotedException() {
        super("Answer already voted!");
    }
}
