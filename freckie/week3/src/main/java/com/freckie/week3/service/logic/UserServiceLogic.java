package com.freckie.week3.service.logic;

import com.freckie.week3.model.User;
import com.freckie.week3.model.UserDTO;
import com.freckie.week3.payload.user.*;
import com.freckie.week3.repository.UserRepository;
import com.freckie.week3.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceLogic implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public GetUserListResponse getUserList() {
        // Get all users as ArrayList
        List<User> users = userRepository.findAll();
        ArrayList<UserDTO> dtoList = users.stream()
                .map(UserDTO::of)
                .collect(Collectors.toCollection(ArrayList<UserDTO>::new));

        // Make a response
        GetUserListResponse resp = new GetUserListResponse();
        resp.setUsers(dtoList);
        return resp;
    }

    @Override
    public GetUserProfileResponse getUserProfile(Long userId) throws NoSuchElementException {
        // raise exception if the user not found
        Optional<User> _user = userRepository.findById(userId);
        if (_user.isEmpty()) {
            throw new NoSuchElementException();
        }

        // Make a response containing the UserDTO
        return new GetUserProfileResponse(UserDTO.of(_user.get()));
    }

    @Override
    @Transactional
    public PostSignUpResponse signUp(PostSignUpRequest req) throws NoSuchElementException {
        // raise exception if the email already exists
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new NoSuchElementException();
        }

        // Create a new User
        User user = new User(req.getName(), req.getEmail());
        user.setPassword(req.getPassword());
        User newUser = userRepository.save(user);

        // Make a response containing the UserDTO
        return new PostSignUpResponse(UserDTO.of(newUser));
    }
}
