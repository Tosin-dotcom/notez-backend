package com.tosin.notez.storage;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.tosin.notez.file.dto.FileType;
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


    public String uploadFileToS3(MultipartFile file, FileType type) throws IOException {

        String originalFileName = file.getOriginalFilename();
        String extension =
                originalFileName != null ? originalFileName.substring(originalFileName.lastIndexOf(".")) : "";
        String fileName = String.format("%s/%s%s", type.toString().toLowerCase(), UUID.randomUUID(), extension);
        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getInputStream().available());
        metadata.setContentType(file.getContentType());

        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata));
        return fileName;
    }

}
