package com.example.week2.domain;

import com.example.week2.dto.BoardForm;
import com.example.week2.dto.UserForm;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Lob        //크기 제한 없게 함
    private String content;

    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)  //User와의 연관관계 생성
    @JoinColumn(name = "user_id")  //user_id를 FK로 들고 있게 됨.
    private User user;


    public Board(){}

    public Board(BoardForm boardForm){
        this.title = boardForm.getTitle();
        this.content = boardForm.getContent();
        this.createdDate = boardForm.getCreatedDate();
    }

    public static Board createBoard(BoardForm boardForm){
        return new Board(boardForm);
    }

}