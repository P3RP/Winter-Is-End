package com.inhun.springboot.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    UNALLOWED("UNALLOWED"),
    ALLOWED("ALLOWED"),
    ADMIN("ADMIN");

    private String value;
}
