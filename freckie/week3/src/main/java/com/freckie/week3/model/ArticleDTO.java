package com.freckie.week3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private Long id;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private UserDTO createdBy;
    private BoardDTO board;

    public static ArticleDTO of(Article article) {
        return new ArticleDTO(article.getId(), article.getTitle(), article.getContents(),
            article.getCreatedAt(), UserDTO.of(article.getCreatedBy()), BoardDTO.of(article.getBoard()));
    }
}
