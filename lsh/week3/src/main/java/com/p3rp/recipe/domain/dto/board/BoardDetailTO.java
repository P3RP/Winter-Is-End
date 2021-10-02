package com.p3rp.recipe.domain.dto.board;


import com.p3rp.recipe.domain.Board;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@ApiModel(value = "게시판 상세 정보", description = "게시판 상세 정보를 가진 Domain Class")
public class BoardDetailTO {
    final private Long id;
    @Setter private String name;
    @Setter private Long cnt;
    final private LocalDateTime createdAt;
    final private LocalDateTime updatedAt;

    public BoardDetailTO(Board board) {
        id = board.getId();
        name = board.getName();
        createdAt = board.getCreatedAt();
        updatedAt = board.getUpdatedAt();
    }
}
