package com.example.week2.controller;

import com.example.week2.dto.BoardForm;
import com.example.week2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@ResponseBody
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/content")
    public void uploadBoard(@RequestBody @Valid BoardForm boardForm){
        boardService.uploadBoard(boardForm);
    }
}
