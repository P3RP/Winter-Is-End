package com.freckie.week3.controller;

import com.freckie.week3.payload.board.*;
import com.freckie.week3.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
@Api(value="Boards", tags={"Board Management"})
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/boards")
    @ApiOperation(value="Get all boards")
    @ApiResponses({
        @ApiResponse(code=200, message="Success")
    })
    public ResponseEntity<GetBoardListResponse> getBoardList() {
        GetBoardListResponse resp = boardService.getBoardList();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/boards")
    @ApiOperation(value="Create a new board")
    @ApiImplicitParams({
        @ApiImplicitParam(name="name", value="The new Board's title", required=true, dataType="String"),
        @ApiImplicitParam(name="read_role", value="The new Board's read role", dataType="Long"),
        @ApiImplicitParam(name="write_role", value="The new Board's write role", dataType="Long"),
        @ApiImplicitParam(name="user_id", value="ID of the User who owns the new Board", required=true, dataType="Long"),
    })
    @ApiResponses({
        @ApiResponse(code=200, message="Success"),
        @ApiResponse(code=400, message="user_id does not exists."),
    })
    public ResponseEntity<PostBoardResponse> createBoard(
        @Valid @RequestBody PostBoardRequest req
    ) {
        // Validation check
        if (req.getName() == null || req.getReadRole() == null || req.getWriteRole() == null ||
                req.getUserId() == null
        ) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try {
            PostBoardResponse resp = boardService.createBoard(req);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/boards/{board_id}")
    @ApiOperation(value="Update a specific board")
    @ApiImplicitParams({
        @ApiImplicitParam(name="name", value="The Board's new title", dataType="String"),
        @ApiImplicitParam(name="read_role", value="The Board's new read role", dataType="Long"),
        @ApiImplicitParam(name="write_role", value="The Board's new write role", dataType="Long")
    })
    @ApiResponses({
        @ApiResponse(code=200, message="Success"),
        @ApiResponse(code=404, message="board_id does not exists."),
    })
    public ResponseEntity<PutBoardResponse> updateBoard(
            @PathVariable(value="board_id") String boardId,
            @Valid @RequestBody PutBoardRequest req
    ) {
        try {
            Long _boardId = Long.valueOf(boardId);
            PutBoardResponse resp = boardService.updateBoard(_boardId, req);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/boards/{board_id}")
    @ApiOperation(value="Delete a specific board")
    @ApiImplicitParams({
        @ApiImplicitParam(name="board_id", value="The Board's ID", required=true, dataType="String")
    })
    @ApiResponses({
        @ApiResponse(code=200, message="Success"),
        @ApiResponse(code=404, message="board_id does not exists.")
    })
    public ResponseEntity<Boolean> updateBoard(
            @PathVariable(value="board_id") String boardId) {
        try {
            Long _boardId = Long.valueOf(boardId);
            Boolean resp = boardService.deleteBoard(_boardId);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
