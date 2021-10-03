package com.inhun.springboot.service;

import com.inhun.springboot.constant.Role;
import com.inhun.springboot.model.User;
import com.inhun.springboot.repository.UserRepository;
import javassist.Loader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(@Lazy PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new UsernameNotFoundException((userId));
        }
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority(Role.ALLOWED.getValue()));
        if (userId.equals("admin")) {
            grantedAuthoritySet.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), grantedAuthoritySet);
    }


    public User authenticateByUserIdAndPassword(String userId, String password) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new UsernameNotFoundException(userId);
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw  new BadCredentialsException("Password not matched");
        }
        return user;
    }


}