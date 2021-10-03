package com.freckie.week3.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="boards",
    uniqueConstraints = {
        @UniqueConstraint(columnNames={"id"})
    })
public class Board {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="read_role")
    private int readRole;

    @Column(name="write_role")
    private int writeRole;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @JoinColumn(name="created_by")
    @ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
    private User createdBy;

    public Board(String name, int readRole, int writeRole, LocalDateTime createdAt, User createdBy) {
        this.name = name;
        this.readRole = readRole;
        this.writeRole = writeRole;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
}
