package com.inhun.springboot.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadsRepository extends JpaRepository<Uploads, Long> {
}
