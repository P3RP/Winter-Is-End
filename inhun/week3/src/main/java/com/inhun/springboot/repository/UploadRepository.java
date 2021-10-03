package com.inhun.springboot.repository;

import com.inhun.springboot.model.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long> {
}
