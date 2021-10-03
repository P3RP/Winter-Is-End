package com.p3rp.recipe.repository;

import com.p3rp.recipe.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByName(String name);
}
