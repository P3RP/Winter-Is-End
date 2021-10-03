package com.inhun.springboot.service;

import com.inhun.springboot.repository.ArticleRepository;
import com.inhun.springboot.model.Upload;
import com.inhun.springboot.repository.UploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final UploadRepository uploadRepository;


    public Upload save(Upload upload) {
        uploadRepository.save(upload);
        return upload;
    }
}
