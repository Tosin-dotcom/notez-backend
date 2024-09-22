package com.tosin.notez.note.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteSummaryDto {

    private List<NoteDto> recentNotes;
    private int totalNotes;
    private LocalDateTime lastCreatedDate;

}
