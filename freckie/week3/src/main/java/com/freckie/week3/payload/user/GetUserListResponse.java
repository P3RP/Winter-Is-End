package com.freckie.week3.payload.user;

import com.freckie.week3.model.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserListResponse {
    private ArrayList<UserDTO> users;
}
