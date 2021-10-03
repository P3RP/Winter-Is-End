package com.example.week2.service;

import com.example.week2.domain.User;
import com.example.week2.dto.UserForm;
import com.example.week2.exception.DuplicateEmailException;
import com.example.week2.repository.UserRepository;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signUpUser(UserForm userForm) throws Exception {
        validateDuplicateEmail(userForm.getEmail());
        userRepository.save(User.createUser(userForm));
    }

    private void validateDuplicateEmail(String email) throws Exception {
        if(userRepository.existsByEmail(email)){
            throw new DuplicateEmailException();
        }
    }
}
