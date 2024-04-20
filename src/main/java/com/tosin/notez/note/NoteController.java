package com.tosin.notez.note;


import com.tosin.notez.model.PagedResponse;
import com.tosin.notez.model.Request;
import com.tosin.notez.model.Response;
import com.tosin.notez.note.dto.NoteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<Response<NoteDto>> createNewNote(@RequestBody Request<NoteDto> request) {

        NoteDto createdNote = noteService.createNewNote(request.getBody());
        Response<NoteDto> response = Response.<NoteDto>builder()
                .body(createdNote)
                .status(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response<PagedResponse<NoteDto>>> getAllNotes(@RequestParam int page,
            @RequestParam int size) {

        PagedResponse<NoteDto> notes = noteService.getAllNotes(page, size);
        Response<PagedResponse<NoteDto>> response = Response.<PagedResponse<NoteDto>>builder()
                .body(notes)
                .status(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
