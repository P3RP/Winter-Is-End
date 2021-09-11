package main.java.com.todoList;

import java.util.*;
import java.util.stream.Collectors;

import main.java.com.userManager.*;

public class TodoList {
    private ArrayList<TodoListItem> items;
    private int itemIndex = 0;

    public TodoList() {
        this.items = new ArrayList<TodoListItem>();
    }

    // Getters & Setters
    public ArrayList<TodoListItem> getTodos() {
        return this.items.stream()
                .collect(Collectors.toCollection(ArrayList<TodoListItem>::new));
    }

    // Public Methods
    public boolean addItem(TodoListItem item) {
        return this.items.add(item);
    }

    public LinkedList<TodoListItem> getItemsByAssignee(User assignee) {
        return this.items.stream()
                .filter(item -> item.getAssignee().equals(assignee))
                .collect(Collectors.toCollection(LinkedList<TodoListItem>::new));
    }
}
