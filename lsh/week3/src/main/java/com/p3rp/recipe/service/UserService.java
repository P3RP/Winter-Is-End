package com.p3rp.recipe.service;

import com.p3rp.recipe.domain.dto.user.RegisterTO;
import com.p3rp.recipe.domain.dto.user.UserTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    String save(RegisterTO registerTO);
    List<UserTO> getUserAll();
    UserTO getUserByAccount(String account);
    UserTO updateUser(String account, String name);
    String deleteUser(String account);
}
