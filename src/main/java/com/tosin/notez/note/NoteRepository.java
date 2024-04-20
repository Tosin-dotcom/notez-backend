package com.tosin.notez.note;


import com.tosin.notez.Tables;
import com.tosin.notez.category.dto.CategoryDto;
import com.tosin.notez.model.PagedResponse;
import com.tosin.notez.note.dto.NoteDto;
import com.tosin.notez.tables.daos.NotesDao;
import com.tosin.notez.tables.pojos.Notes;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteRepository {

    private final NotesDao notesDao;
    private final ModelMapper modelMapper;
    private final DSLContext dslContext;


    public NoteDto saveNote(NoteDto noteDto) {

        Notes notes = new Notes();
        notes.setId(UUID.randomUUID());
        notes.setTitle(noteDto.getTitle());
        notes.setTextContent(noteDto.getTextContent());
        notes.setUserId(noteDto.getUserId());
        notes.setCategoryId(noteDto.getCategory().getId());
        notes.setCreatedAt(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));
        notes.setUpdatedAt(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));

        notesDao.insert(notes);
        return map(notes);
    }


    public PagedResponse<NoteDto> getAllNotes(UUID userId, int page, int size) {

        int rowNumber = (page * size) - size;

        long totalNotes = dslContext.selectCount()
                .from(Tables.NOTES).where(Tables.NOTES.USER_ID.eq(userId))
                .fetchOptionalInto(long.class).orElse(0L);

        List<NoteDto> categoryDtoList = dslContext.selectFrom(Tables.NOTES)
                .where(Tables.NOTES.USER_ID.eq(userId))
                .offset(rowNumber)
                .limit(size)
                .fetchInto(Notes.class).stream().map(this::map).toList();

        return PagedResponse.<NoteDto>builder()
                .records(categoryDtoList)
                .page(page)
                .size(size)
                .total(totalNotes)
                .build();
    }


    private NoteDto map(Notes notes) {

        NoteDto map = modelMapper.map(notes, NoteDto.class);
        map.setCategory(CategoryDto.builder().id(notes.getCategoryId()).build());
        return map;
    }

}
