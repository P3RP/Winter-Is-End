package todolist;

import code.TaskMenu;
import code.ToDoListMenu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ToDoList {
    private ArrayList<Task> taskList;

    public ToDoList() {
        taskList = new ArrayList<>();
    }

    public void createTask() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("\n======== To Do List [할 일 생성] ========");
        System.out.print("제목 :: ");
        String title = keyboard.nextLine();
        System.out.print("할 일 :: ");
        String content = keyboard.nextLine();

        Task task = new Task();
        task.setTitle(title);
        task.setContent(content);
        task.setFinish(false);

        Date now = new Date();
        task.setCreatedAt(now);
        task.setUpdatedAt(now);

        taskList.add(task);

        System.out.println("\n생성 완료");
    }

    public void updateTask(int idx) {
        Scanner keyboard = new Scanner(System.in);

        Task task = taskList.get(idx);

        System.out.println("\n======== To Do List [할 일 수정] ========");
        System.out.println(String.format("%s[%s]", task.getTitle(), task.isFinish()));
        System.out.print("할 일 :: ");
        String content = keyboard.nextLine();

        Date now = new Date();

        task.setContent(content);
        task.setUpdatedAt(now);

        System.out.println("\n수정 완료");
    }

    public void finishTask(int idx) {
        Date now = new Date();

        Task task = taskList.get(idx);
        task.setFinish(true);
        task.setUpdatedAt(now);

        System.out.println("\n할 일 완료");
    }

    public void deleteTask(int idx) {
        taskList.remove(idx);
        System.out.println("\n삭제 완료");
    }

    public void readAll() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("[%d] [%s]%s", i, taskList.get(i).isFinish(), taskList.get(i).getTitle()));
        }
    }

    public void readSearch() {
        System.out.print("검색 :: ");
        Scanner keyboard = new Scanner(System.in);
        String keyword = keyboard.nextLine();
        final List<Task> result = taskList.stream().filter(task -> task.getTitle().contains(keyword)).collect(Collectors.toList());

        for (Task task : result) {
            System.out.println(String.format("[%d] [%s]%s", taskList.indexOf(task), task.isFinish(), task.getTitle()));
        }
    }

    public void readTask(int idx) {
        TaskMenu select = null;

        while (select != TaskMenu.EXIT) {
            System.out.println("\n======== To Do List [할 일 조회] ========");
            taskList.get(idx).read();
            System.out.println("============================");
            System.out.println("exit: 나가기");
            System.out.println("update: 수정");
            System.out.println("delete: 삭제");
            System.out.println("finish: 완료");
            System.out.println("============================");
            System.out.print("입력 :: ");

            while (true) {
                try {
                    Scanner keyboard = new Scanner(System.in);
                    select = TaskMenu.valueOf(keyboard.nextLine().toUpperCase());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("잘못된 명령어입니다.");
                }
            }

            switch (select) {
                case UPDATE -> updateTask(idx);
                case DELETE -> deleteTask(idx);
                case FINISH -> finishTask(idx);
                default -> {
                }
            }
        }
    }
}
