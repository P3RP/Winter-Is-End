package com.freckie.week3.payload.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostSignUpRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
