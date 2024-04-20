package com.tosin.notez.storage;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.tosin.notez.storage.dto.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageService {

    @Value("${aws.bucket-name}")
    private String bucketName;

    private final AmazonS3 amazonS3;


    public FileDto uploadFileToS3(MultipartFile file) throws IOException {

        String key = String.format("%s%s", file.getOriginalFilename(), UUID.randomUUID());
        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getInputStream().available());
        metadata.setContentType(file.getContentType());

        amazonS3.putObject(new PutObjectRequest(bucketName, key, file.getInputStream(), metadata));

        return FileDto.builder()
                .fileName(file.getOriginalFilename())
                .type(file.getContentType())
                .url(key)
                .build();
    }

}
