package com.freckie.week2.usermanager;

import lombok.*;
import java.util.*;
import java.util.stream.Collectors;

public class UserManager {
    private HashSet<User> users;

    public UserManager() {
        users = new HashSet<>();
    }

    public boolean add(User user) {
        return users.add(user);
    }

    public ArrayList<User> toArrayList() {
        return users.stream().collect(Collectors.toCollection(ArrayList<User>::new));
    }

    public User findById(String id) {
        for (User it : users) {
            if (it.getId().equals(id)) return it;
        }
        return null;
    }

    public ArrayList<User> findByName(String name) {
        return this.users.stream()
                .filter(user -> user.getName().equals(name))
                .collect(Collectors.toCollection(ArrayList<User>::new));
    }
}
