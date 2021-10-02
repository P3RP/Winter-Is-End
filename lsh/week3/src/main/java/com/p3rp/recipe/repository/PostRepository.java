package com.p3rp.recipe.repository;

import com.p3rp.recipe.domain.Board;
import com.p3rp.recipe.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);
    Long countByBoardId(Long boardId);
    List<Post> findAllByBoard(Board board);
}
