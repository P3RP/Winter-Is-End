package com.p3rp.recipe.domain.dto.post;


import com.p3rp.recipe.domain.Board;
import com.p3rp.recipe.domain.Post;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@ApiModel(value = "게시물 정보", description = "게시물 정보를 가진 Domain Class")
public class PostListTO {
    final private Long id;
    @Setter private Board board;
    @Setter private String title;
    final private LocalDateTime createdAt;
    final private LocalDateTime updatedAt;

    public PostListTO(Post post) {
        id = post.getId();
        board = post.getBoard();
        title = post.getTitle();
        createdAt = post.getCreatedAt();
        updatedAt = post.getUpdatedAt();
    }
}
