package com.inhun.springboot.model;

import com.inhun.springboot.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Long> {
}
