package com.freckie.week2.usermanager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;

public class User {
    private @Id @Getter @Setter String id;
    private String encryptedPassword;
    private @Getter @Setter String name;
    private @Getter LocalDateTime createdAt;

    public User(String id, String password, String name) {
        this.id = id;
        this.name = name;
        this.setPassword(password);
        createdAt = LocalDateTime.now();
    }

    public void setPassword(String password) {
        encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.encryptedPassword);
    }
}
