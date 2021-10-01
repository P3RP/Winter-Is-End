package com.freckie.week3.service.logic;

import com.freckie.week3.model.User;
import com.freckie.week3.model.UserDTO;
import com.freckie.week3.payload.user.GetUserListResponse;
import com.freckie.week3.payload.user.GetUserProfileResponse;
import com.freckie.week3.payload.user.PostSignUpRequest;
import com.freckie.week3.payload.user.PostSignUpResponse;
import com.freckie.week3.repository.UserRepository;
import com.freckie.week3.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceLogic implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public GetUserListResponse getUserList() {
        List<User> users = userRepository.findAll();
        ArrayList<UserDTO> dtoList = users.stream()
                .map(UserDTO::of)
                .collect(Collectors.toCollection(ArrayList<UserDTO>::new));

        GetUserListResponse resp = new GetUserListResponse();
        resp.setUsers(dtoList);
        return resp;
    }

    @Override
    public GetUserProfileResponse getUserProfile(Long userId) {
        User user = userRepository.getById(userId);
        UserDTO dto = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());

        return new GetUserProfileResponse(dto);
    }

    @Override
    public PostSignUpResponse signUp(PostSignUpRequest req) throws NoSuchElementException {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new NoSuchElementException();
        }

        User user = new User(req.getName(), req.getEmail());
        user.setPassword(req.getPassword());
        User newUser = userRepository.save(user);

        UserDTO dto = new UserDTO(newUser.getId(), newUser.getName(), newUser.getEmail(), newUser.getRole());
        return new PostSignUpResponse(dto);
    }
}
