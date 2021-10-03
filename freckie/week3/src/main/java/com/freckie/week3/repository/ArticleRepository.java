package com.freckie.week3.repository;

import com.freckie.week3.model.Board;
import com.freckie.week3.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("select a from Article a where a.board=?1")
    public List<Article> findAllByBoard(Board board);
}
