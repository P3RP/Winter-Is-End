package com.inhun.springboot.controller;


import com.inhun.springboot.dto.UserJsonDto;
import com.inhun.springboot.model.User;
import com.inhun.springboot.security.config.JwtTokenUtil;
import com.inhun.springboot.service.JwtUserDetailsService;
import com.inhun.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Api(tags = {"User API"})
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    @ApiOperation(value = "Test")
    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String getRequest(Principal principal) {
        System.out.println(principal.getName());
        return "this is InHun Choi";
    }


    @RequestMapping(method= RequestMethod.POST, path = "/signup", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String SignUp(@RequestBody UserJsonDto userJsonDto) {
        try {
            User user = User.createUser(userJsonDto, passwordEncoder);
            userService.saveUser(user);
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
        return "gooooood";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/signin")
    public ResponseEntity<?> SignIn(@RequestBody UserJsonDto userJsonDto) {
        final User user = jwtUserDetailsService.authenticateByUserIdAndPassword(
                userJsonDto.getUserId(), userJsonDto.getPassword());
        System.out.println(user.getUserId());
        final String token = jwtTokenUtil.generateToken(user.getUserId());
        return ResponseEntity.ok(new JwtResponse(token));

    }


}
@Data
@AllArgsConstructor
class JwtResponse {
    private String token;
}

