package com.freckie.week3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id;
    private String name;
    private int readRole;
    private int writeRole;
    private LocalDateTime createdAt;
    private UserDTO createdBy;

    public static BoardDTO of(Board board) {
        return new BoardDTO(board.getId(), board.getName(), board.getReadRole(),
            board.getWriteRole(), board.getCreatedAt(), UserDTO.of(board.getCreatedBy()));
    }
}
