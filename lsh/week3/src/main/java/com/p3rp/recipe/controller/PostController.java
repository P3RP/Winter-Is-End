package com.p3rp.recipe.controller;

import com.p3rp.recipe.domain.dto.board.BoardTO;
import com.p3rp.recipe.domain.dto.post.PostTO;
import com.p3rp.recipe.service.BoardService;
import com.p3rp.recipe.service.PostService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Post", tags = { "Post" })
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    @ApiOperation(value = "게시물 생성", notes = "신규 게시물을 생성한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardId", value = "게시판 ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "title", value = "제목", required = true),
            @ApiImplicitParam(name = "content", value = "내용", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시물 생성 완료"),
            @ApiResponse(code = 401, message = "인증되지 않음"),
            @ApiResponse(code = 403, message = "권한이 없음"),
            @ApiResponse(code = 404, message = "게시물 생성 경로를 찾을 수 없음"),
    })
    public ResponseEntity<Long> createPost(
            @RequestBody Long boardId,
            @RequestBody String title,
            @RequestBody String content
    ) {
        PostTO postTO = new PostTO(boardId, title, content);
        return new ResponseEntity<>(postService.save(postTO), null, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{id}")
    @ApiOperation(value = "게시물 조회", notes = "게시물을 검색한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "게시물 ID", required = true, dataType = "int"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "검색 완료"),
            @ApiResponse(code = 401, message = "인증되지 않음"),
            @ApiResponse(code = 403, message = "권한이 없음"),
            @ApiResponse(code = 404, message = "게시물 검색 경로를 찾을 수 없음"),
    })
    public ResponseEntity<PostTO> retrievePost(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(postService.getPostById(id), null, HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    @ApiOperation(value = "게시물 수정", notes = "게시물을 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "게시물 ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "title", value = "제목", required = true),
            @ApiImplicitParam(name = "content", value = "내용", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "게시물 수정 완료"),
            @ApiResponse(code = 401, message = "인증되지 않음"),
            @ApiResponse(code = 403, message = "권한이 없음"),
            @ApiResponse(code = 404, message = "게시물 수정 경로를 찾을 수 없음"),
    })
    public ResponseEntity<PostTO> updatePost(
            @PathVariable("id") Long id,
            @RequestBody String title,
            @RequestBody String content
    ) {
        return new ResponseEntity<>(postService.updatePost(id, title, content), null, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    @ApiOperation(value = "게시물 삭제", notes = "게시물을 삭제한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "게시물 ID", required = true, dataType = "int"),
    })
    @ApiResponses({
            @ApiResponse(code = 204, message = "삭제 완료"),
            @ApiResponse(code = 401, message = "인증되지 않음"),
            @ApiResponse(code = 403, message = "권한이 없음"),
            @ApiResponse(code = 404, message = "게시물 삭제 경로를 찾을 수 없음"),
    })
    public ResponseEntity<Long> deletePost(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(postService.deletePost(id), null, HttpStatus.NO_CONTENT);
    }
}
