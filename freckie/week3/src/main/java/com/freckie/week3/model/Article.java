package com.freckie.week3.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="articles",
    uniqueConstraints = {
        @UniqueConstraint(columnNames={"id"})
    }
)
public class Article {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="contents")
    private String contents;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @JoinColumn(name="created_by")
    @ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)
    private User createdBy;

    @JoinColumn(name="board_id")
    @ManyToOne(targetEntity=Board.class, fetch=FetchType.LAZY)
    private Board board;

    public Article(String title, String contents, LocalDateTime createdAt, User createdBy, Board board) {
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.board = board;
    }
}
