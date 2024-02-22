package com.project.techmonk.questionanswerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileUploadException extends RuntimeException {
    public FileUploadException () {
        super("Exception caught while uploading file!");
    }
}
