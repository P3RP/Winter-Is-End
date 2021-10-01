package com.freckie.week3.controller;

import com.freckie.week3.payload.board.GetBoardListResponse;
import com.freckie.week3.service.BoardService;

import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("")
    @ApiOperation(value="Get all boards")
    public ResponseEntity<GetBoardListResponse> getBoardList() {
        GetBoardListResponse resp = boardService.getBoardList();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
