package com.p3rp.recipe.domain.dto.user;


import com.p3rp.recipe.domain.User;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@ApiModel(value = "회원 정보", description = "회원 정보를 가진 Domain Class")
public class UserTO {
    @Setter private String name;
    @Setter private String account;
    @Setter private LocalDateTime lastAccessedAt;
    final private LocalDateTime createdAt;
    final private LocalDateTime updatedAt;

    public UserTO(User user) {
        name = user.getName();
        account = user.getAccount();
        lastAccessedAt = user.getLastAccessedAt();
        createdAt = user.getCreatedAt();
        updatedAt = user.getUpdatedAt();
    }
}
