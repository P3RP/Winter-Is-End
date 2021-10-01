package com.freckie.week3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name="boards",
    uniqueConstraints = {
        @UniqueConstraint(columnNames={"id"})
    })
public class Board {
    @Id
    @Getter
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Getter
    @Column(name="name")
    private String name;

    @Getter
    @Column(name="read_role")
    private int readRole;

    @Getter
    @Column(name="write_role")
    private int writeRole;

    @Getter
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Getter
    @JoinColumn(name="created_by")
    @ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
    private User createdBy;
}
