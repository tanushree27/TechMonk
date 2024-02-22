package com.project.techmonk.questionanswerservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserUnauthorizedException extends RuntimeException {
    public UserUnauthorizedException () {
        super ("User unauthorized to make this action!");
    }
}
