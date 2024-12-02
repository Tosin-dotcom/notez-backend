package com.tosin.notez.file;


import com.tosin.notez.file.dto.FileDto;
import com.tosin.notez.model.PagedResponse;
import com.tosin.notez.model.Request;
import com.tosin.notez.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {


    private final FileService fileService;

    @PostMapping
    public ResponseEntity<Response<FileDto>> createNewFile(@RequestBody Request<FileDto> request) throws IOException {

        Response<FileDto> response = Response.<FileDto>builder()
                .body(fileService.createNewFile(request.getBody()))
                .status(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<Response<PagedResponse<FileDto>>> getAllFiles(@RequestParam int page,
            @RequestParam int size) {

        Response<PagedResponse<FileDto>> response = Response.<PagedResponse<FileDto>>builder()
                .body(fileService.getAllFiles(page, size))
                .status(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }





}
