package com.project.techmonk.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserWithEmailAlreadyExistsException extends RuntimeException {
    public UserWithEmailAlreadyExistsException() {
        super("User with this email already exists!");
    }
}
