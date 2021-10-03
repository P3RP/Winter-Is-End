package com.p3rp.recipe.domain.dto.user;

import com.p3rp.recipe.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value = "회원가입 정보", description = "이름, 아이디, 비밀번호를 가진 Domain Class")
public class RegisterTO {
    @ApiModelProperty(value = "이름")
    private String name;

    @ApiModelProperty(value = "아이디")
    private String account;

    @ApiModelProperty(value = "비밀번호")
    private String password;

    public User toEntity() {
        return new User(name, account, password);
    }
}
