package com.project.techmonk.questionanswerservice.service;

import org.springframework.stereotype.Service;

@Service
public interface FileStorageService {
    String uploadFile(String name, byte[] content, String email);
}
