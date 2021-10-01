package com.freckie.week3.payload.user;

import com.freckie.week3.model.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserProfileResponse {
    private UserDTO user;
}
