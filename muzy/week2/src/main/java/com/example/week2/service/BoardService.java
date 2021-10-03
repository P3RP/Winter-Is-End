package com.example.week2.service;

import com.example.week2.domain.Board;
import com.example.week2.dto.BoardForm;
import com.example.week2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void uploadBoard(BoardForm boardForm){
        boardRepository.save(Board.createBoard(boardForm));
    }
}
