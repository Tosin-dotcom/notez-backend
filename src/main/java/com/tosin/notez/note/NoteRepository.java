package com.tosin.notez.note;


import com.tosin.notez.Tables;
import com.tosin.notez.category.CategoryRepository;
import com.tosin.notez.exception.NotezException;
import com.tosin.notez.model.PagedResponse;
import com.tosin.notez.note.dto.NoteDto;
import com.tosin.notez.note.dto.NoteSummaryDto;
import com.tosin.notez.tables.daos.NotesDao;
import com.tosin.notez.tables.pojos.Notes;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteRepository {

    private final NotesDao notesDao;
    private final ModelMapper modelMapper;
    private final DSLContext dslContext;
    private final CategoryRepository categoryRepository;


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


    public PagedResponse<NoteDto> getAllNotes(UUID userId, int page, int size, String searchValue) {

        int rowNumber = (page * size) - size;

        List<Condition> conditions = new ArrayList<>();
        conditions.add(Tables.NOTES.USER_ID.eq(userId));

        if (StringUtils.hasText(searchValue)) {
            conditions.add(Tables.NOTES.TITLE.containsIgnoreCase(searchValue)
                    .or(Tables.NOTES.TEXT_CONTENT.containsIgnoreCase(searchValue)));
        }

        long totalNotes = dslContext.selectCount()
                .from(Tables.NOTES).where(conditions)
                .fetchOptionalInto(long.class).orElse(0L);

        List<NoteDto> notes = dslContext.selectFrom(Tables.NOTES)
                .where(conditions)
                .offset(rowNumber)
                .limit(size)
                .fetchInto(Notes.class).stream().map(this::map).toList();

        return PagedResponse.<NoteDto>builder()
                .records(notes)
                .page(page)
                .size(size)
                .total(totalNotes)
                .build();
    }


    public NoteDto getNoteById(NoteDto noteDto) {

        return map(getNoteOrThrow(noteDto));
    }

    public NoteDto updateNote(NoteDto noteDto) {

        Notes note = getNoteOrThrow(noteDto);
        note.setTitle(noteDto.getTitle());
        note.setTextContent(noteDto.getTextContent());
        note.setCategoryId(noteDto.getCategory().getId());
        note.setUpdatedAt(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));

        notesDao.update(note);
        return map(note);
    }

    public void deleteNote(NoteDto noteDto) {

        Notes note = getNoteOrThrow(noteDto);
        notesDao.delete(note);
    }


    public NoteSummaryDto getDashboardData(UUID userId) {

        List<NoteDto> recentNotes = dslContext
                .selectFrom(Tables.NOTES)
                .where(Tables.NOTES.USER_ID.eq(userId))
                .orderBy(Tables.NOTES.CREATED_AT.desc())
                .offset(0)
                .limit(3)
                .fetchInto(Notes.class).stream().map(this::map).toList();

        Result<Record2<Integer, LocalDateTime>> result = dslContext
                .select(
                        DSL.count().as("totalNote"),
                        DSL.max(Tables.NOTES.CREATED_AT).as("lastNoteDate")
                )
                .from(Tables.NOTES)
                .where(Tables.NOTES.USER_ID.eq(userId))
                .groupBy(Tables.NOTES.USER_ID)
                .fetch();

        int totalNote = 0;
        LocalDateTime lastNoteDate = null;

        // If result is not empty, extract the values
        if (!result.isEmpty()) {
            totalNote = (int) result.get(0).get("totalNote");
            lastNoteDate = (LocalDateTime) result.get(0).get("lastNoteDate");
        }

        return NoteSummaryDto.builder()
                .recentNotes(recentNotes)
                .lastCreatedDate(lastNoteDate)
                .totalNotes(totalNote)
                .build();
    }



    private Notes getNoteOrThrow(NoteDto noteDto) {

        return dslContext.selectFrom(Tables.NOTES)
                .where(Tables.NOTES.ID.eq(noteDto.getId()).and(Tables.NOTES.USER_ID.eq(noteDto.getUserId())))
                .fetchOptionalInto(Notes.class)
                .orElseThrow(() -> new NotezException("Note not found", HttpStatus.NOT_FOUND));
    }

    private NoteDto map(Notes notes) {

        NoteDto map = modelMapper.map(notes, NoteDto.class);

        map.setCategory(categoryRepository.getCategoryById(notes.getCategoryId()));
        return map;
    }



}
