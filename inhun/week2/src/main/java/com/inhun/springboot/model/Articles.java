package com.inhun.springboot.model;

import javax.persistence.*;

import lombok.*;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Entity
@Table(name="articles")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Articles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title", length=100)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "views")
    private int views;

    @Column(name = "published_at")
    private LocalDateTime published_at;

    public Articles(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.views = 0;
    }

    @PrePersist
    public void createdAt() {
        this.published_at = LocalDateTime.now();
    }

    public void changeArticle(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
