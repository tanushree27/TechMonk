package com.project.techmonk.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserNameAlreadyExistsException extends RuntimeException {
    public UserNameAlreadyExistsException() {
        super("UserName already in use!");
    }
}
