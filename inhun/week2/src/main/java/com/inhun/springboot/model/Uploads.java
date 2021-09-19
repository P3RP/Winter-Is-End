package com.inhun.springboot.model;

import javax.persistence.*;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "uploads")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Uploads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", length=100)
    private String name;

    @Column(name = "path", length = 255)
    private String path;

    @Column(name = "uploaded_at")
    private LocalDateTime uploaded_at;

    public Uploads(String name, String path) {
        this.name = name;
        this.path = path;
    }

    @PrePersist
    public void createdAt() {
        this.uploaded_at = LocalDateTime.now();
    }

}
