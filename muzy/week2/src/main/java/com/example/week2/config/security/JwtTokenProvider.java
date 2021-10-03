package com.example.week2.config.security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("spring.jwt.secret")
    private String secretKey;
}
