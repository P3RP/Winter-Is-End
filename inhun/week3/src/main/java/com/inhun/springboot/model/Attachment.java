package com.inhun.springboot.model;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;

@Getter
@Entity
@Table(name = "attachments")
@IdClass(Attachment.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Attachment implements Serializable {
    @Id
    @Column(name = "article_id")
    private Long article_id;

    @Id
    @Column(name = "upload_id")
    private Long upload_id;

    public Attachment(Long article_id, Long upload_id) {
        this.article_id = article_id;
        this.upload_id = upload_id;
    }

}
