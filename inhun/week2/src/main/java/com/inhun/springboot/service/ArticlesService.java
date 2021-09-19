package com.inhun.springboot.service;


import com.inhun.springboot.model.Attachments;
import com.inhun.springboot.model.Uploads;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inhun.springboot.model.Articles;
import com.inhun.springboot.model.ArticlesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ArticlesService {

    private final ArticlesRepository articlesRepository;

    public ArticlesService(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }


    public List<Articles> findAll() {
        List<Articles> articles = new ArrayList<>();
        articlesRepository.findAll().forEach(e -> articles.add(e));
        return articles;
    }

    public Articles save(Articles article) {
        articlesRepository.save(article);
        return article;
    }

    public void updateById(Long id, Articles article) {
        Optional<Articles> e = articlesRepository.findById(id);
        if (e.isPresent()) {
            e.get().changeArticle(article.getTitle(), article.getContent());
            articlesRepository.save(e.get());
        }
    }

    public void deleteById(Long id) {
        articlesRepository.deleteById(id);
    }


}
