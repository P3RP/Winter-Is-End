package com.freckie.week3.repository;

import com.freckie.week3.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.validation.constraints.NotBlank;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(@NotBlank String email);
}
