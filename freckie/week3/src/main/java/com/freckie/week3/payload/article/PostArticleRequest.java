package com.freckie.week3.payload.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostArticleRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @NotBlank
    private Long createdBy;

    @NotBlank
    private Long boardId;
}
