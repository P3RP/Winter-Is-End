package com.freckie.week3.service;

import com.freckie.week3.payload.article.*;

import java.util.NoSuchElementException;

public interface ArticleService {
    public GetArticleListResponse getArticleList(Long boardId) throws NoSuchElementException;

    public GetArticleResponse getArticle(Long boardId, Long articleId) throws NoSuchElementException;

    public PostArticleResponse createArticle(Long boardId, PostArticleRequest req) throws NoSuchElementException;

    public PutArticleResponse updateArticle(Long boardId, Long articleId, PutArticleRequest req) throws NoSuchElementException;

    public Boolean deleteArticle(Long boardId, Long articleId) throws NoSuchElementException;
}
