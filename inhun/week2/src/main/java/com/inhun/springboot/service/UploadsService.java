package com.inhun.springboot.service;

import com.inhun.springboot.model.ArticlesRepository;
import com.inhun.springboot.model.Uploads;
import com.inhun.springboot.model.UploadsRepository;
import org.springframework.stereotype.Service;

@Service
public class UploadsService {

    private UploadsRepository uploadsRepository;


    private final ArticlesRepository articlesRepository;

    public UploadsService(UploadsRepository uploadsRepository, ArticlesRepository articlesRepository) {
        this.uploadsRepository = uploadsRepository;
        this.articlesRepository = articlesRepository;
    }

    public Uploads save(Uploads upload) {
        uploadsRepository.save(upload);
        return upload;
    }
}
