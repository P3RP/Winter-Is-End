package main.java.com.todo;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.*;

import main.java.com.todoList.TodoList;
import main.java.com.todoList.TodoListItem;
import main.java.com.userManager.*;

/**
 * Hello world!
 *
 */
public class App {
    private static UserManager users;
    private static TodoList todos;

    public static void main( String[] args ) {
        users = new UserManager();
        todos = new TodoList();

        int value = 0;
        while (value != 9) {
            value = printScreen();

            switch (value) {
                case 1:
                    addUser();
                case 2:
                    listUser();
                case 3:
                    createTodo();
                case 4:
                    listTodo();
            }
        }
    }

    public static int printScreen() {
        Scanner sc = new Scanner(System.in);

        System.out.println("========== Menu ==========");
        System.out.println(" > 1. Add User");
        System.out.println(" > 2. List Users");
        System.out.println(" > 3. Create Todo Item");
        System.out.println(" > 4. List Todo Items");
        System.out.println(" > 9. Exit");
        System.out.println("==========================");
        System.out.print(" $ ");

        int value = sc.nextInt();
        return value;
    }

    public static void addUser() {
        Scanner sc = new Scanner(System.in);

        System.out.println("========== Add User ==========");
        System.out.print(" $ ID : ");
        String id = sc.nextLine();

        System.out.print(" $ PW : ");
        String pw = sc.nextLine();

        System.out.print(" $ Name : ");
        String name = sc.nextLine();

        boolean result = users.add(new User(id, pw, name));

        if (result) System.out.println("Add a new user successfully.");
        else System.out.println("Failed to add a new user.");
    }

    public static void listUser() {
        System.out.println("========== Users ==========");

        for (User user : users.toArrayList()) {
            System.out.println(user.getName() + " " + user.getId());
        }
    }

    public static void createTodo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("========== Create Todo ==========");
        System.out.print(" $ Title : ");
        String title = sc.nextLine();

        boolean result = todos.addItem(
                new TodoListItem(title, LocalDateTime.now()));

        if (result) System.out.println("Created a todo item successfully.");
        else System.out.println("Failed to add a new todo item.");
    }

    public static void listTodo() {
        System.out.println("========== Todos ==========");

        for (TodoListItem item : todos.getTodos()) {
            System.out.println(item.getTitle() + " " + item.getDue().format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }
}
