package com.tosin.notez.category.dto;


import com.tosin.notez.storage.dto.FileDto;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private UUID id;
    private String name;
    private String imageUrl;
    private FileDto fileDto;

}
