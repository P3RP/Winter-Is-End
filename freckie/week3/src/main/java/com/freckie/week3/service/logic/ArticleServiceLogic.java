package com.freckie.week3.service.logic;

import com.freckie.week3.model.*;
import com.freckie.week3.payload.article.*;
import com.freckie.week3.payload.board.PutBoardResponse;
import com.freckie.week3.repository.ArticleRepository;
import com.freckie.week3.repository.BoardRepository;
import com.freckie.week3.repository.UserRepository;
import com.freckie.week3.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ArticleServiceLogic implements ArticleService {
    @Autowired
    private ArticleRepository articleRepo;

    @Autowired
    private BoardRepository boardRepo;

    @Autowired
    private UserRepository userRepo;

    public GetArticleListResponse getArticleList(Long boardId) throws NoSuchElementException {
        // raise exception if no Board exists
        boardRepo.findById(boardId).orElseThrow(() -> new NoSuchElementException("No specific board exists"));
        Board board = boardRepo.getById(boardId);

        // Create DTO list
        List<Article> articles = articleRepo.findAllByBoard(board);
        ArrayList<ArticleDTO> dtoList = articles.stream()
                .map(ArticleDTO::of)
                .collect(Collectors.toCollection(ArrayList<ArticleDTO>::new));

        // Make a response containing the Article List
        return new GetArticleListResponse(BoardDTO.of(board), dtoList);
    }

    public GetArticleResponse getArticle(Long boardId, Long articleId) throws NoSuchElementException {
        // raise exception if no Board exists
        boardRepo.findById(boardId).orElseThrow(() -> new NoSuchElementException("No specific board exists"));
        articleRepo.findById(articleId).orElseThrow(() -> new NoSuchElementException("No specific article exists"));

        // Get the Article
        Article article = articleRepo.getById(articleId);

        // Make a response containing the Article
        return new GetArticleResponse(ArticleDTO.of(article));
    }

    @Transactional
    public PostArticleResponse createArticle(Long boardId, PostArticleRequest req) throws NoSuchElementException {
        // raise exception if no Board exists
        Long userId = req.getCreatedBy();
        userRepo.findById(userId).orElseThrow(() -> new NoSuchElementException("No specific user exists"));
        boardRepo.findById(boardId).orElseThrow(() -> new NoSuchElementException("No specific board exists"));

        // Create a new Post
        User user = userRepo.getById(userId);
        Board board = boardRepo.getById(boardId);
        Article article = new Article(req.getTitle(), req.getContents(), LocalDateTime.now(), user, board);
        ArticleDTO dto = ArticleDTO.of(articleRepo.save(article));

        // Make a response containing the ArticleDTO
        return new PostArticleResponse(dto);
    }

    @Transactional
    public PutArticleResponse updateArticle(Long boardId, Long articleId, PutArticleRequest req) throws NoSuchElementException {
        // raise exception if no Article exists
        boardRepo.findById(boardId).orElseThrow(() -> new NoSuchElementException("No specific board exists"));
        articleRepo.findById(boardId).orElseThrow(() -> new NoSuchElementException("No specific article exists"));

        // Get the Article
        Article article = articleRepo.getById(articleId);

        // Update the Article
        if (req.getTitle().isPresent()) {
            article.setTitle(req.getTitle().get());
        }
        if (req.getContents().isPresent()) {
            article.setContents(req.getContents().get());
        }
        ArticleDTO dto = ArticleDTO.of(articleRepo.save(article));

        // Make a response containing the BoardDTO
        return new PutArticleResponse(dto);
    }

    @Transactional
    public Boolean deleteArticle(Long boardId, Long articleId) throws NoSuchElementException {
        // raise exception if no Board exists
        boardRepo.findById(boardId).orElseThrow(() -> new NoSuchElementException("No specific board exists"));
        articleRepo.findById(boardId).orElseThrow(() -> new NoSuchElementException("No specific article exists"));

        articleRepo.deleteById(articleId);
        return true;
    }
}
