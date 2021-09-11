package menu;

import java.util.HashMap;
import java.util.Scanner;
import code.InitMenu;
import code.MainMenu;
import code.TaskMenu;
import user.User;

public class Menu {
    public HashMap<String, User> userHashMap;
    public User currentUser;

    public Menu() {
        userHashMap = new HashMap<>();
    }

    public void init() {
        InitMenu select = null;

        while (select != InitMenu.EXIT) {
            if (currentUser != null) {
                mainMenu();
            } else {
                System.out.println("======== To Do List [초기화면] ========");
                System.out.println("메뉴를 선택하세요!");

                System.out.println(
                        "exit: 종료\nlogin: 로그인\nregister: 회원가입"
                );
                System.out.println("============================");

                while (true) {
                    try {
                        System.out.print("입력 :: ");
                        Scanner keyboard = new Scanner(System.in);
                        select = InitMenu.valueOf(keyboard.nextLine().toUpperCase());
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("잘못된 명령어입니다.");
                    }
                }

                switch (select) {
                    case LOGIN -> loginMenu();
                    case REGISTER -> registerMenu();
                    case EXIT -> {System.out.println("종료");}
                    default -> {
                    }
                }
            }
        }
    }

    private void registerMenu() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("======== To Do List [회원가입] ========");
        System.out.print("ID 입력 :: ");
        String id = keyboard.nextLine();
        System.out.print("비밀번호 입력 :: ");
        String pw = keyboard.nextLine();
        System.out.print("비밀번호 확인 입력 :: ");
        String pwChk = keyboard.nextLine();

        if (userHashMap.containsKey(id)) {
            // 현재 존재하는 ID 여부 확인
            System.out.println("============================");
            System.out.println("이미 존재하는 ID입니다.");
        } else if (!pw.equals(pwChk)) {
            // 비밀번호 확인
            System.out.println("============================");
            System.out.println("비밀번호 확인이 일치하지 않습니다.");
        } else {
            User user = new User(id, pw);
            userHashMap.put(id, user);
            System.out.println("============================");
            System.out.println("회원가입 완료");
        }
    }

    public void loginMenu() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("======== To Do List [로그인] ========");
        System.out.print("ID 입력 :: ");
        String id = keyboard.nextLine();
        System.out.print("비밀번호 입력 :: ");
        String pw = keyboard.nextLine();

        if (!userHashMap.containsKey(id) || !userHashMap.get(id).checkId(id) || !userHashMap.get(id).checkPw(pw)) {
            System.out.println("============================");
            System.out.println("가입하지 않은 ID거나 비밀번호가 일치하지 않습니다.");
        } else {
            currentUser = userHashMap.get(id);

            System.out.println("============================");
            System.out.println("로그인 성공");
        }
    }

    private void mainMenu() {
        MainMenu select = null;

        while (select != MainMenu.LOGOUT) {
            System.out.println("======== To Do List [메인메뉴] ========");
            System.out.println("logout: 로그아웃");
            System.out.println("create: 추가");
            System.out.println("all: 전체 조회");
            System.out.println("search: 검색");
            System.out.println("read: 할일 조회");
            System.out.println("delete: 삭제");
            System.out.println("============================");

            while (true) {
                try {
                    System.out.print("입력 :: ");
                    Scanner keyboard = new Scanner(System.in);
                    select = MainMenu.valueOf(keyboard.nextLine().toUpperCase());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("잘못된 명령어입니다.");
                }
            }

            switch (select) {
                case LOGOUT -> {
                    currentUser = null;
                    System.out.println("로그아웃!");
                }
                case CREATE -> {currentUser.getToDoList().createTask();}
                case ALL -> {currentUser.getToDoList().readAll();}
                case SEARCH -> {currentUser.getToDoList().readSearch();}
                case READ -> {
                    System.out.print("조회할 할일 ID 입력 :: ");
                    Scanner keyboard = new Scanner(System.in);
                    int idx = keyboard.nextInt();
                    currentUser.getToDoList().readTask(idx);
                }
                case DELETE -> {
                    System.out.print("삭제할 할일 ID 입력 :: ");
                    Scanner keyboard = new Scanner(System.in);
                    int idx = keyboard.nextInt();
                    currentUser.getToDoList().deleteTask(idx);
                }
                default -> {
                }
            }
        }
    }
}