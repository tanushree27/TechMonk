package com.project.techmonk.questionanswerservice.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.Instant;

@Service
@AllArgsConstructor
public class MinioFileStorageServiceImpl implements FileStorageService {
    MinioClient minioClient;
    private static final String defaultBucketName = "media";
    private static final String host = "http://localhost:9000";

    @Override
    public String uploadFile(String name, byte[] content, String email) {

        try {
            String fileName = email + Instant.now().toEpochMilli() + name;
            minioClient.putObject(PutObjectArgs.builder().bucket(defaultBucketName).object(fileName)
                    .stream(new ByteArrayInputStream(content), -1, 10485760)
                    .contentType("image/jpeg")
                    .build()
            );

            return "%s/%s/%s".formatted(host, defaultBucketName, fileName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
