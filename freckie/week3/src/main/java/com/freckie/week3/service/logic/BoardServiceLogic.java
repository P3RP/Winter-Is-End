package com.freckie.week3.service.logic;

import com.freckie.week3.payload.board.GetBoardListResponse;
import com.freckie.week3.repository.BoardRepository;
import com.freckie.week3.repository.UserRepository;
import com.freckie.week3.service.BoardService;
import com.freckie.week3.model.BoardDTO;
import com.freckie.week3.model.Board;
import com.freckie.week3.model.UserDTO;
import com.freckie.week3.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceLogic implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public GetBoardListResponse getBoardList() {
        List<Board> boards = boardRepository.findAll();
        System.out.println("count : " + Long.toString(boards.size()));
        boards.forEach(System.out::println);

        ArrayList<BoardDTO> dtoList;
        dtoList = boards.stream()
                .map(BoardDTO::of)
                .collect(Collectors.toCollection(ArrayList<BoardDTO>::new));

        GetBoardListResponse resp = new GetBoardListResponse();
        resp.setBoards(dtoList);
        return resp;
    }
}
