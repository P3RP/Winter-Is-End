package com.p3rp.recipe.domain.dto.post;


import com.p3rp.recipe.domain.Board;
import com.p3rp.recipe.domain.Post;
import com.p3rp.recipe.repository.BoardRepository;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@ToString
@ApiModel(value = "게시물 정보", description = "게시물 정보를 가진 Domain Class")
public class PostTO {
    @Autowired
    private BoardRepository boardRepository;

    final private Long id;
    @Setter private Long board;
    @Setter private String title;
    @Setter private String content;
    final private LocalDateTime createdAt;
    final private LocalDateTime updatedAt;

    public PostTO(Post post) {
        id = post.getId();
        board = post.getBoard().getId();
        title = post.getTitle();
        content = post.getContent();
        createdAt = post.getCreatedAt();
        updatedAt = post.getUpdatedAt();
    }

    public PostTO(Long board, String title, String content) {
        this.title = title;
        this.content = content;
        this.board = board;
        id = null;
        createdAt = null;
        updatedAt = null;
    }

    public Post toEntity() {
        return new Post(boardRepository.getById(board), title, content);
    }
}
