package com.freckie.week3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = { "id" })
    })
public class User {
    @Id
    @Getter
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="encrypted_password")
    private String encryptedPassword;

    @Getter
    @Column(name="name")
    private String name;

    @Getter
    @Column(name="email")
    private String email;

    @Getter
    @Column(name="role")
    private int role;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setPassword(String password) {
        encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Boolean comparePassword(String password) {
        return BCrypt.checkpw(encryptedPassword, password);
    }
}
