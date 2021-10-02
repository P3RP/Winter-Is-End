package com.freckie.week3.payload.board;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class PostBoardRequest {
    @NotBlank
    private String name;

    @NotBlank
    private Long userId;
}
