package com.inhun.springboot.service;


import com.inhun.springboot.model.Article;
import com.inhun.springboot.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        articleRepository.findAll().forEach(e -> articles.add(e));
        return articles;
    }

    public Article findById(Long id) {
        Optional<Article> e = articleRepository.findById(id);
        return e.get();
    }

    public Article save(Article article) {
        articleRepository.save(article);
        return article;
    }

    public void updateById(Long id, Article article) {
        Optional<Article> e = articleRepository.findById(id);
        if (e.isPresent()) {
            e.get().changeArticle(article.getTitle(), article.getContent());
            articleRepository.save(e.get());
        }
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }


}
