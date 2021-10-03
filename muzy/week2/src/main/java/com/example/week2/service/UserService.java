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
@RequiredArgsConstructor  //이 어노테이션을 통해 UserRepository를 받는다.
//초기화 되지않은 final 필드나, @NonNull이 붙은 필드의 생성자를 생성해 줌.
//주로 의존성 주입 편의성을 위해 사용됨.
public class UserService {

    private final UserRepository userRepository;

    @Transactional  //Insert 쿼리가 작동되기 때문에 어노테이션 붙임.
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
