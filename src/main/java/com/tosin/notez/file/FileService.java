package com.tosin.notez.file;


import com.tosin.notez.file.dto.FileDto;
import com.tosin.notez.model.PagedResponse;
import com.tosin.notez.storage.StorageService;
import com.tosin.notez.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {


    private final FileRepository fileRepository;
    private final UserService userService;
    private final StorageService storageService;


    @Transactional
    public FileDto createNewFile(FileDto fileDto) throws IOException {

        String fileName = storageService.uploadFileToS3(fileDto.getFile(), fileDto.getType());
        fileDto.setFilename(fileName);
        fileDto.setUserId(userService.getUserDetails().getId());

        return fileRepository.save(fileDto);
    }


    public PagedResponse<FileDto> getAllFiles(int page, int size) {

        UUID userId = userService.getUserDetails().getId();
        return fileRepository.getAllFiles(userId, page, size);
    }


}
