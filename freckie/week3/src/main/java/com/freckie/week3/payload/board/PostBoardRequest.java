package com.freckie.week3.payload.board;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostBoardRequest {
    private Integer readRole;
    private Integer writeRole;

    @NotBlank
    private String name;

    @NotNull
    private Integer userId;
}
