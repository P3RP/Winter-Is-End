package com.example.week2.repository;

import com.example.week2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    //어플리케이션 실행시점에 JPA가 메소드 이름을 보고 위의 쿼리를 만들어줌.
    //쿼리가 틀렸다면 런타임 에러가 발생하며 어플리케이션이 실행되지 않는다.
}

//JPARepository를 상속받으면 기본적인 CRUD는 자동생성되며 메소드 이름으로 쿼리를 만들 수 있음.
//껍데기만 만들어도 기본적인 save, find, update, delete를 사용할 수 있다.