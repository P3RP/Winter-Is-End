package com.freckie.week3.payload.article;

import com.freckie.week3.model.BoardDTO;
import com.freckie.week3.model.ArticleDTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetArticleListResponse {
    private BoardDTO board;
    private ArrayList<ArticleDTO> articles;
}
