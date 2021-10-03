package com.freckie.week3.service;

import com.freckie.week3.payload.user.*;

import java.util.NoSuchElementException;

public interface UserService {
    public GetUserListResponse getUserList();

    public GetUserProfileResponse getUserProfile(Long userId) throws NoSuchElementException;

    public PostSignUpResponse signUp(PostSignUpRequest req) throws NoSuchElementException;
}
