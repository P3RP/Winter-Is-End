package com.p3rp.recipe.service.implement;

import com.p3rp.recipe.domain.User;
import com.p3rp.recipe.domain.dto.user.RegisterTO;
import com.p3rp.recipe.domain.dto.user.UserTO;
import com.p3rp.recipe.repository.UserRepository;
import com.p3rp.recipe.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public String save(RegisterTO registerTO) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        registerTO.setPassword(passwordEncoder.encode(registerTO.getPassword()));

        User user = registerTO.toEntity();
        return userRepository.save(user).getAccount();
    }

    @Override
    public List<UserTO> getUserAll() {
        List<User> userList =  userRepository.findAll();
        return userList.stream().map(UserTO::new).collect(Collectors.toList());
    }

    @Override
    public UserTO getUserByAccount(String account) {
        Optional<User> userEntityWrapper = userRepository.findByAccount(account);
        User userEntity = userEntityWrapper.orElse(null);
        if (userEntity != null) {
            return new UserTO(userEntity);
        } else {
            return null;
        }
    }

    @Override
    public UserTO updateUser(String account, String name) {
        Optional<User> userEntityWrapper = userRepository.findByAccount(account);
        User userEntity = userEntityWrapper.orElse(null);
        if (userEntity != null) {
            userEntity.setName(name);
            User newUser = userRepository.save(userEntity);
            return new UserTO(newUser);
        } else {
            return null;
        }
    }

    @Override
    public String deleteUser(String account) {
        Optional<User> userEntityWrapper = userRepository.findByAccount(account);
        User userEntity = userEntityWrapper.orElse(null);
        if (userEntity != null) {
            String target = userEntity.getAccount();
            userRepository.delete(userEntity);
            return target;
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Optional<User> userEntityWrapper = userRepository.findByAccount(account);
        User userEntity = userEntityWrapper.orElse(null);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        return new org.springframework.security.core.userdetails.User(
                userEntity.getAccount(),
                userEntity.getPassword(),
                authorities
        );
    }
}
