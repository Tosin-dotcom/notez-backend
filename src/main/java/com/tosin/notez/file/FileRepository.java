package com.tosin.notez.file;


import com.tosin.notez.Tables;
import com.tosin.notez.file.dto.FileDto;
import com.tosin.notez.file.dto.FileType;
import com.tosin.notez.model.PagedResponse;
import com.tosin.notez.tables.daos.FilesDao;
import com.tosin.notez.tables.pojos.Files;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileRepository {

    private final FilesDao filesDao;
    private final ModelMapper modelMapper;
    private final DSLContext dslContext;


    @Value("${notez.cdn}")
    private String cdnUrl;


    public FileDto save(FileDto fileDto) {

        Files file = new Files();
        file.setId(UUID.randomUUID());
        file.setType(fileDto.getType().toString());
        file.setFilename(fileDto.getFilename());
        file.setUserId(fileDto.getUserId());
        file.setTitle(fileDto.getTitle());
        file.setCreatedAt(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));
        file.setUpdatedAt(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));

        filesDao.insert(file);
        return map(file);
    }


    public PagedResponse<FileDto> getAllFiles(UUID userId, int page, int size) {

        int rowNumber = (page * size) - size;

        List<Condition> conditions = new ArrayList<>();
        conditions.add(Tables.FILES.USER_ID.eq(userId));

        long totalFiles = dslContext.selectCount()
                .from(Tables.FILES).where(conditions)
                .fetchOptionalInto(long.class).orElse(0L);

        List<FileDto> files = dslContext.selectFrom(Tables.FILES)
                .where(conditions)
                .offset(rowNumber)
                .limit(size)
                .fetchInto(Files.class).stream().map(this::map).toList();

        return PagedResponse.<FileDto>builder()
                .records(files)
                .page(page)
                .size(size)
                .total(totalFiles)
                .build();
    }


    private FileDto map(Files file) {

        FileDto map = modelMapper.map(file, FileDto.class);
        map.setType(FileType.valueOf(file.getType().toUpperCase()));
        map.setUrl(String.format("%s/%s", cdnUrl, file.getFilename()));
        return map;
    }


}
