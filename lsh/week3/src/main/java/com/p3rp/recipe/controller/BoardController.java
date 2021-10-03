package com.p3rp.recipe.controller;

import com.p3rp.recipe.domain.dto.board.BoardDetailTO;
import com.p3rp.recipe.domain.dto.board.BoardTO;
import com.p3rp.recipe.domain.dto.post.PostListTO;
import com.p3rp.recipe.service.BoardService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Board", tags = { "Board" })
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/boards")
    @ApiOperation(value = "게시판 생성", notes = "신규 게시판을 생성한다.")
    @ApiImplicitParam(name = "name", value = "이름", required = true)
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시판 생성 완료"),
            @ApiResponse(code = 401, message = "인증되지 않음"),
            @ApiResponse(code = 403, message = "권한이 없음"),
            @ApiResponse(code = 404, message = "게시판 생성 경로를 찾을 수 없음"),
    })
    public ResponseEntity<Long> createBoard(@RequestBody String name) {
        BoardTO boardTO = new BoardTO(name);
        return new ResponseEntity<>(boardService.save(boardTO), null, HttpStatus.CREATED);
    }

    @GetMapping("/boards")
    @ApiOperation(value = "전체 게시판 조회", notes = "전체 게시판 목록을 검색한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "검색 완료"),
            @ApiResponse(code = 401, message = "인증되지 않음"),
            @ApiResponse(code = 403, message = "권한이 없음"),
            @ApiResponse(code = 404, message = "검색 경로를 찾을 수 없음"),
    })
    public ResponseEntity<List<BoardTO>> retrieveBoardAll() {
        return new ResponseEntity<>(
                boardService.getBoardAll(), null, HttpStatus.OK
        );
    }

    @GetMapping("/boards/{boardId}")
    @ApiOperation(value = "게시판 정보 조회", notes = "게시판 ID로 게시판 정보를 검색한다.")
    @ApiImplicitParam(name = "boardId", value = "게시판 ID", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "검색 완료"),
            @ApiResponse(code = 401, message = "인증되지 않음"),
            @ApiResponse(code = 403, message = "권한이 없음"),
            @ApiResponse(code = 404, message = "검색 경로를 찾을 수 없음"),
    })
    public ResponseEntity<BoardDetailTO> retrieveBoard(@PathVariable("boardId") Long boardId) {
        return new ResponseEntity<>(
                boardService.getBoard(boardId), null, HttpStatus.OK
        );
    }

    @PutMapping("/boards/{boardId}")
    @ApiOperation(value = "게시판 이름 수정", notes = "게시판의 이름을 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardId", value = "게시판 ID", required = true),
            @ApiImplicitParam(name = "newName", value = "신규 게시판 이름", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정 완료"),
            @ApiResponse(code = 401, message = "인증되지 않음"),
            @ApiResponse(code = 403, message = "권한이 없음"),
            @ApiResponse(code = 404, message = "수정 경로를 찾을 수 없음"),
    })
    public ResponseEntity<BoardTO> modifyBoard(
            @PathVariable("boardId") Long boardId,
            @RequestBody String newName
    ) {
        return new ResponseEntity<>(
                boardService.updateBoard(boardId, newName), null, HttpStatus.OK
        );
    }

    @DeleteMapping("/boards/{boardId}")
    @ApiOperation(value = "게시판 삭제", notes = "해당 게시판을 삭제한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardId", value = "게시판 ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 204, message = "삭제 완료"),
            @ApiResponse(code = 401, message = "인증되지 않음"),
            @ApiResponse(code = 403, message = "권한이 없음"),
            @ApiResponse(code = 404, message = "검색 경로를 찾을 수 없음"),
    })
    public ResponseEntity<Long> deleteBoard(
            @PathVariable("boardId") Long boardId
    ) {
        return new ResponseEntity<>(
                boardService.deleteBoard(boardId), null, HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/boards/{boardId}/posts")
    @ApiOperation(value = "게시판 내 게시물 조회", notes = "게시판 ID로 게시판 내 게시물 목록을 검색한다.")
    @ApiImplicitParam(name = "boardId", value = "게시판 ID", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "검색 완료"),
            @ApiResponse(code = 401, message = "인증되지 않음"),
            @ApiResponse(code = 403, message = "권한이 없음"),
            @ApiResponse(code = 404, message = "검색 경로를 찾을 수 없음"),
    })
    public ResponseEntity<List<PostListTO>> retrieveBoardPosts(@PathVariable("boardId") Long boardId) {
        return new ResponseEntity<>(
                boardService.getPostAll(boardId), null, HttpStatus.OK
        );
    }
}
