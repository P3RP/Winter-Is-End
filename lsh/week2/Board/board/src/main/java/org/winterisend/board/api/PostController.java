package org.winterisend.board.api;

import org.springframework.http.RequestEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import org.winterisend.board.post.Post;
import org.winterisend.board.post.PostList;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@Api(value = "Post", description = "REST API for Post", tags = { "Post" })
public class PostController {

    private final PostList postList = new PostList();

    @PostMapping("/posts")
    @ApiOperation(value = "게시물 생성", notes = "신규 게시물을 생성한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name="title", value="제목", required = true, dataType = "string"),
            @ApiImplicitParam(name="content", value="내용", required = true, dataType = "string"),
    })
    public int createPost(String title, String content) {
        return postList.addPost(title, content);
    }

    @GetMapping("/posts")
    @ApiOperation(value = "게시물 목록 조회", notes = "전체 게시물 목록을 조회한다.")
    public Object getPostList() {
        HashMap<Integer, Post> posts = postList.getPostList();
        return posts.values();
    }

    @GetMapping("/posts/{id}")
    @ApiOperation(value = "게시물 조회", notes = "해당 게시물의 정보를 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "게시물 ID", required = true, dataType = "int")
    })
    public Object getPost(int id) {
        return postList.getPost(id);
    }

    @PutMapping("/posts/{id}")
    @ApiOperation(value = "게시물 수정", notes = "해당 게시물의 정보를 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "게시물 ID", required = true, dataType = "int"),
            @ApiImplicitParam(name="title", value="제목", dataType = "string"),
            @ApiImplicitParam(name="content", value="내용", dataType = "string"),
    })
    public Object editPost(int id, String title, String content) {
        if (title != null) {
            postList.editPostTitle(id, title);
        }

        if (content != null) {
            postList.editPostContent(id, content);
        }

        return postList.getPost(id);
    }

    @DeleteMapping("/posts/{id}")
    @ApiOperation(value = "게시물 삭제", notes = "해당 게시물을 삭제한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "게시물 ID", required = true, dataType = "int")
    })
    public HttpStatus deletePost(int id) {
        postList.deletePost(id);
        return HttpStatus.NO_CONTENT;
    }
}
