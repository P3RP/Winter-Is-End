package com.example.week2.controller;

import com.example.week2.dto.UserForm;
import com.example.week2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor //UserService를 final 필드로 등록함.
@ResponseBody
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signUpUser(@RequestBody @Valid UserForm userForm) throws Exception{
        userService.signUpUser(userForm);
    }



    //@RequestBody를 통해 userForm을 JSON으로 받고 @Valid를 통해 값 검증 함.
    //@Valid를 이용해 @RequestBody 객체 검증하기 https://jyami.tistory.com/55
    //검증의 세부적인 사항은 객체 안에 정의 해두어야 한다.
    //나는 @NotEmpty, @Size가 정의
}

