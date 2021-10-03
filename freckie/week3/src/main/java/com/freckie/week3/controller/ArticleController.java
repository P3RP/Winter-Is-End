package com.freckie.week3.controller;

import com.freckie.week3.payload.article.*;
import com.freckie.week3.payload.board.PutBoardRequest;
import com.freckie.week3.payload.board.PutBoardResponse;
import com.freckie.week3.service.ArticleService;

import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
@Api(value="Articles", tags={"Article Management"})
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/boards/{board_id}/articles")
    @ApiOperation(value="Get all articles in a specific board")
    @ApiResponses({
        @ApiResponse(code=200, message="Success")
    })
    public ResponseEntity<GetArticleListResponse> getArticleList(
        @PathVariable(value="board_id") String boardId
    ) {
        Long _boardId = Long.valueOf(boardId);
        GetArticleListResponse resp = articleService.getArticleList(_boardId);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/boards/{board_id}/articles/{article_id}")
    @ApiOperation(value="Get info of a specific Article")
    @ApiResponses({
        @ApiResponse(code=200, message="Success"),
        @ApiResponse(code=404, message="board_id does not exists."),
        @ApiResponse(code=404, message="article_id does not exists.")
    })
    public ResponseEntity<GetArticleResponse> getArticle(
        @PathVariable(value="board_id") String boardId,
        @PathVariable(value="article_id") String articleId
    ) {
        Long _boardId = Long.valueOf(boardId);
        Long _articleId = Long.valueOf(articleId);
        GetArticleResponse resp = articleService.getArticle(_boardId, _articleId);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/boards/{board_id}/articles")
    @ApiOperation(value="Create a new board")
    @ApiImplicitParams({
        @ApiImplicitParam(name="title", value="The new Article's title", required=true, dataType="String"),
        @ApiImplicitParam(name="contents", value="The new Article's contents", required=true, dataType="String"),
        @ApiImplicitParam(name="createdBy", value="User ID", required=true, dataType="Long"),
        @ApiImplicitParam(name="boardId", value="Board ID", required=true, dataType="Long"),
    })
    @ApiResponses({
        @ApiResponse(code=200, message="Success"),
        @ApiResponse(code=404, message="board_id does not exists."),
    })
    public ResponseEntity<PostArticleResponse> createArticle(
        @PathVariable(value="board_id") String boardId,
        @Valid @RequestBody PostArticleRequest req
    ) {
        Long _boardId = Long.valueOf(boardId);
        PostArticleResponse resp = articleService.createArticle(_boardId, req);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping("/boards/{board_id}/article/{article_id}")
    @ApiOperation(value="Update a specific article")
    @ApiImplicitParams({
        @ApiImplicitParam(name="title", value="The Article's new title", dataType="String"),
        @ApiImplicitParam(name="contents", value="The Article's new contents", dataType="String")
    })
    @ApiResponses({
        @ApiResponse(code=200, message="Success"),
        @ApiResponse(code=404, message="board_id does not exists."),
        @ApiResponse(code=404, message="article_id does not exists.")
    })
    public ResponseEntity<PutArticleResponse> updateArticle(
        @PathVariable(value="board_id") String boardId,
        @PathVariable(value="article_id") String articleId,
        @Valid @RequestBody PutArticleRequest req
    ) {
        try {
            Long _boardId = Long.valueOf(boardId);
            Long _articleId = Long.valueOf(articleId);
            PutArticleResponse resp = articleService.updateArticle(_boardId, _articleId, req);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/boards/{board_id}/article/{article_id}")
    @ApiOperation(value="Delete a specific article")
    @ApiImplicitParams({
        @ApiImplicitParam(name="board_id", value="The Board's ID", required=true, dataType="String"),
        @ApiImplicitParam(name="article_id", value="The Article's ID", required=true, dataType="String")
    })
    @ApiResponses({
        @ApiResponse(code=200, message="Success"),
        @ApiResponse(code=404, message="board_id does not exists."),
        @ApiResponse(code=404, message="article_id does not exists.")
    })
    public ResponseEntity<Boolean> deleteArticle(
        @PathVariable(value="board_id") String boardId,
        @PathVariable(value="article_id") String articleId
    ) {
        try {
            Long _boardId = Long.valueOf(boardId);
            Long _articleId = Long.valueOf(articleId);
            Boolean resp = articleService.deleteArticle(_boardId, _articleId);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
