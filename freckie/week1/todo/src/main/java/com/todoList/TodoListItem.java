package main.java.com.todoList;

import java.time.*;
import main.java.com.userManager.User;

public class TodoListItem {
    private String title;
    private LocalDateTime due;
    private LocalDateTime createdAt;
    private User assignee;
    private boolean isDone;

    public TodoListItem(String title, LocalDateTime due) {
        this.title = title;
        this.due = due;
        this.assignee = null;
        this.isDone = false;
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters
    public String getTitle() {
        return this.title;
    }
    public LocalDateTime getDue() {
        return this.due;
    }
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    public User getAssignee() { return this.assignee; }
    public boolean isDone() { return this.isDone; }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDue(LocalDateTime due) {
        this.due = due;
    }
    public void setAssignee(User user) {
        this.assignee = user;
    }
}
