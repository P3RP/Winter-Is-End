package com.p3rp.recipe.service;

import com.p3rp.recipe.domain.dto.board.BoardDetailTO;
import com.p3rp.recipe.domain.dto.board.BoardTO;
import com.p3rp.recipe.domain.dto.post.PostListTO;

import java.util.List;

public interface BoardService {
    Long save(BoardTO boardTO);
    List<BoardTO> getBoardAll();
    BoardDetailTO getBoard(Long boardId);
    BoardTO updateBoard(Long boardId, String name);
    Long deleteBoard(Long boardId);
    List<PostListTO> getPostAll(Long boardId);
}
