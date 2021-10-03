package com.inhun.springboot.model;

import com.inhun.springboot.constant.Role;
import com.inhun.springboot.dto.UserJsonDto;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="users")
@Getter
@ToString
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;


    /*
    @Column(name = "privilege_level")
    @ColumnDefault("0")
    private Integer privilegeLevel;
    */
    @Enumerated(EnumType.STRING)
    private Role role;

    public static User createUser(UserJsonDto userJsonDto, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.userId = userJsonDto.getUserId();
        user.name = userJsonDto.getName();
        user.password = passwordEncoder.encode(userJsonDto.getPassword());
        user.role = Role.UNALLOWED;
        return user;
    }
}
