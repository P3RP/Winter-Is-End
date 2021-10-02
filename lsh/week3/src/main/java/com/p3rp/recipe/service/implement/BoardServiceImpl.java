package com.p3rp.recipe.service.implement;

import com.p3rp.recipe.domain.Board;
import com.p3rp.recipe.domain.Post;
import com.p3rp.recipe.domain.dto.board.BoardDetailTO;
import com.p3rp.recipe.domain.dto.board.BoardTO;
import com.p3rp.recipe.domain.dto.post.PostListTO;
import com.p3rp.recipe.domain.dto.post.PostTO;
import com.p3rp.recipe.repository.BoardRepository;
import com.p3rp.recipe.repository.PostRepository;
import com.p3rp.recipe.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Long save(BoardTO boardTO) {
        Board board = boardTO.toEntity();
        return boardRepository.save(board).getId();
    }

    @Override
    public List<BoardTO> getBoardAll() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream().map(BoardTO::new).collect(Collectors.toList());
    }

    @Override
    public BoardDetailTO getBoard(Long boardId) {
        Board board = boardRepository.getById(boardId);
        BoardDetailTO boardDetailTO = new BoardDetailTO(board);
        boardDetailTO.setCnt(postRepository.countByBoardId(boardDetailTO.getId()));
        return boardDetailTO;
    }

    @Override
    public BoardTO updateBoard(Long boardId, String name) {
        Board board = boardRepository.getById(boardId);
        board.setName(name);
        Board newBoard = boardRepository.save(board);

        return new BoardTO(newBoard);
    }

    @Override
    public Long deleteBoard(Long boardId) {
        Board board = boardRepository.getById(boardId);
        Long target = board.getId();
        boardRepository.delete(board);

        return target;
    }

    @Override
    public List<PostListTO> getPostAll(Long boardId) {
        Board board = boardRepository.getById(boardId);
        List<Post> postList = postRepository.findAllByBoard(board);

        return postList.stream().map(PostListTO::new).collect(Collectors.toList());
    }
}
