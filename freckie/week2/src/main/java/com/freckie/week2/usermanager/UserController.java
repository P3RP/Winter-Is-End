package com.freckie.week2.usermanager;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1")
public class UserController {
    private UserManager users;

    public UserController() {
        users = new UserManager();
    }

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        ArrayList<User> userList = users.toArrayList();
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/users")
    public ResponseEntity createUsers(String id, String name, String password) {
        User user = new User(id, password, name);
        users.add(user);
        return ResponseEntity.ok("Success");
    }
}
