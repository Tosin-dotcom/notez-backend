package com.tosin.notez.storage.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public final class FileDto {
    private UUID id;
    private MultipartFile file;
    private String url;
    private String type;
    private String fileName;
    private UUID resourceOwnerId;

}
