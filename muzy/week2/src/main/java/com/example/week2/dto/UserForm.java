package com.example.week2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
public class UserForm {

    @NotEmpty(message = "이메일은 필수 입니다.") //dto 내용 검증
    @Size(max = 100, message = "이메일은 100자리를 초과할 수 없습니다.")
    private String email;

    @NotEmpty(message = "이름은 필수 입니다.")
    @Size(max = 20, message = "이름은 20자리를 초과할 수 없습니다.")
    private String userName;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    @Size(max = 100, message = "비밀번호는 100자리를 초과할 수 없습니다.")
    private String password;
}
