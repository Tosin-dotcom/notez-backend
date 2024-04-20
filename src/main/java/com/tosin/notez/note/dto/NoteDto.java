package com.tosin.notez.note.dto;


import com.tosin.notez.category.dto.CategoryDto;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteDto {

    private UUID id;
    private String title;
    private String textContent;
    private CategoryDto category;
    private UUID userId;

}
