package com.tosin.notez.storage;


import com.tosin.notez.model.Response;
import com.tosin.notez.storage.dto.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;


    @PostMapping
    public ResponseEntity<Response<FileDto>> uploadFile(@RequestPart MultipartFile file) throws IOException {

        FileDto fileDto = storageService.uploadFileToS3(file);
        Response<FileDto> response = Response.<FileDto>builder()
                .body(fileDto)
                .status(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
