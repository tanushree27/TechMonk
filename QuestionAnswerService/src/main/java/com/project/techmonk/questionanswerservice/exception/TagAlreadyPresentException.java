package com.project.techmonk.questionanswerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TagAlreadyPresentException extends RuntimeException {
    public TagAlreadyPresentException () {
        super("Tag already present!");
    }
}
