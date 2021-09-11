package main.java.com.userManager;

import main.java.com.todoList.TodoListItem;

import java.util.*;
import java.lang.IllegalArgumentException;
import java.util.stream.Collectors;

public class UserManager {
    private HashSet<User> userSet;

    public UserManager() {
        this.userSet = new HashSet<User>();
    }

    // Getters & Setters

    // Public Methods
    public boolean add(User user) {
        return this.userSet.add(user);
    }

    public ArrayList<User> toArrayList() {
        return this.userSet.stream()
                .collect(Collectors.toCollection(ArrayList<User>::new));
    }

    public User findById(String id) throws IllegalArgumentException {
        for (User it : this.userSet) {
            if (it.getId().equals(id)) return it;
        }
        throw new IllegalArgumentException("Id not found.");
    }

    public ArrayList<User> findByName(String name) {
        return this.userSet.stream()
                .filter(user -> user.getName().equals(name))
                .collect(Collectors.toCollection(ArrayList<User>::new));
    }
}