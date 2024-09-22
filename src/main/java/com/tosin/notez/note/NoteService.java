package com.tosin.notez.note;


import com.tosin.notez.model.PagedResponse;
import com.tosin.notez.note.dto.NoteDto;
import com.tosin.notez.note.dto.NoteSummaryDto;
import com.tosin.notez.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {


    private final NoteRepository noteRepository;
    private final UserService userService;


    public NoteDto createNewNote(NoteDto noteDto) {

        noteDto.setUserId(userService.getUserDetails().getId());
        return noteRepository.saveNote(noteDto);
    }

    public PagedResponse<NoteDto> getAllNotes(int page, int size) {

        UUID userId = userService.getUserDetails().getId();
        return noteRepository.getAllNotes(userId, page, size);
    }

    public NoteDto getNoteById(UUID id) {

        return noteRepository.getNoteById(
                NoteDto.builder().id(id).userId(userService.getUserDetails().getId()).build());
    }


    public NoteDto updateNote(NoteDto noteDto) {

        noteDto.setUserId(userService.getUserDetails().getId());
        return noteRepository.updateNote(noteDto);
    }

    public void deleteNote(UUID id) {

        noteRepository.deleteNote(NoteDto.builder().id(id).
                userId(userService.getUserDetails().getId()).build());
    }

    public NoteSummaryDto getNoteSummary() {

        return noteRepository.getDashboardData(userService.getUserDetails().getId());
    }


}

