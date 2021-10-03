package com.freckie.week3.service;

import com.freckie.week3.payload.board.*;

import java.util.NoSuchElementException;

public interface BoardService {
    public GetBoardListResponse getBoardList();

    public PostBoardResponse createBoard(PostBoardRequest req) throws NoSuchElementException;

    public PutBoardResponse updateBoard(Long boardId, PutBoardRequest req) throws NoSuchElementException;

    public Boolean deleteBoard(Long boardId) throws NoSuchElementException;
}
