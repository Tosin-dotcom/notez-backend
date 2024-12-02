package com.tosin.notez.file.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDto {

    private UUID id;
    private String title;
    private String url;
    private String filename;
    private FileType type;
    private UUID userId;
    private MultipartFile file;



}
