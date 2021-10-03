package com.example.week2.domain;

import com.example.week2.dto.UserForm;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Entity             //JPA의 entitiy로 등록 됨.
@Getter
public class User {

    @Id       // 해당 칼럼이 Primary key라는 것을 의미함.
    @GeneratedValue(strategy = GenerationType.AUTO) //@Id와 함께 쓰는데 어떤 전략으로 생성하는지 명시
    @Column(name = "user_id")  //db 칼럼 등록
    private Long id;

    @Column(length = 50, nullable = false)
    private String email;

    @Column( length = 50, nullable = false)
    private String userName;

    @Column( length = 50, nullable = false)
    private String password;


    // 생성자 및 정적 팩토리 메소드 추가 클래스의 인스턴스를 얻는 거
    // 정적 팩토리 메소드의 장점
    // 1. 이름을 가질 수 있음.
    //
    public User(){}  //빈 User 생성자는 JPA에서 필요로 하기 때문에 생성

    public User(UserForm userForm) {
        this.email = userForm.getEmail();
        this.userName = userForm.getUserName();
        this.password = userForm.getPassword();
    }

    public static User createUser(UserForm userForm){
        return new User(userForm);
    }

}