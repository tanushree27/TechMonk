package com.project.techmonk.questionanswerservice.controller;

import com.project.techmonk.questionanswerservice.service.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class FileController {
    FileStorageService fileStorageService;

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadFile(@RequestPart(value = "file", required = false) MultipartFile file, @RequestHeader("email") String email) {
        try {
            return fileStorageService.uploadFile(file.getOriginalFilename(), file.getBytes(), email);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
