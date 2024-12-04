package com.tosin.notez.file;


import com.tosin.notez.file.dto.FileDto;
import com.tosin.notez.file.dto.FileType;
import com.tosin.notez.model.PagedResponse;
import com.tosin.notez.model.Request;
import com.tosin.notez.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {


    private final FileService fileService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Response<FileDto>> createNewFile(@RequestPart("file") MultipartFile file,
            @RequestParam("title") String title, @RequestParam("type") String type) throws IOException {

        FileDto fileDto = FileDto.builder()
                .file(file)
                .title(title)
                .type(FileType.valueOf(type))
                .build();
        Response<FileDto> response = Response.<FileDto>builder()
                .body(fileService.createNewFile(fileDto))
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
