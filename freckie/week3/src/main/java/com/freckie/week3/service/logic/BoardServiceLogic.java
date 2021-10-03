package com.freckie.week3.service.logic;

import com.freckie.week3.payload.board.*;
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
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
public class BoardServiceLogic implements BoardService {
    @Autowired
    private BoardRepository boardRepo;

    @Autowired
    private UserRepository userRepo;

    public GetBoardListResponse getBoardList() {
        List<Board> boards = boardRepo.findAll();

        ArrayList<BoardDTO> dtoList;
        dtoList = boards.stream()
                .map(BoardDTO::of)
                .collect(Collectors.toCollection(ArrayList<BoardDTO>::new));

        GetBoardListResponse resp = new GetBoardListResponse();
        resp.setBoards(dtoList);
        return resp;
    }

    @Transactional
    public PostBoardResponse createBoard(PostBoardRequest req) throws NoSuchElementException {
        // raise exception if no User exists
        Long _userId = Long.valueOf(req.getUserId());
        userRepo.findById(_userId).orElseThrow(() -> new NoSuchElementException("No user exists"));

        // Create a new Board
        User user = userRepo.getById(_userId);
        Board board = new Board(req.getName(), req.getReadRole(), req.getWriteRole(), LocalDateTime.now(), user);
        Board newBoard = boardRepo.save(board);

        // Make a response containing the BoardDTO
        return new PostBoardResponse(BoardDTO.of(newBoard));
    }

    @Transactional
    public PutBoardResponse updateBoard(Long boardId, PutBoardRequest req) throws NoSuchElementException {
        // raise exception if no Board exists
        boardRepo.findById(boardId).orElseThrow(() -> new NoSuchElementException("No board exists"));

        // Get the Board
        Board board = boardRepo.getById(boardId);

        // Update the Board
        if (req.getName() != null) {
            board.setName(req.getName().get());
        }
        if (req.getReadRole() != null) {
            board.setReadRole(req.getReadRole().get());
        }
        if (req.getWriteRole() != null) {
            board.setWriteRole(req.getWriteRole().get());
        }

        Board updatedBoard = boardRepo.save(board);

        // Make a response containing the BoardDTO
        return new PutBoardResponse(BoardDTO.of(updatedBoard));
    }

    @Transactional
    public Boolean deleteBoard(Long boardId) throws NoSuchElementException {
        // raise exception if no Board exists
        boardRepo.findById(boardId).orElseThrow(() -> new NoSuchElementException("No board exists"));

        boardRepo.deleteById(boardId);
        return true;
    }
}
