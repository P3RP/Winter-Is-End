package user;
import todolist.ToDoList;

public class User {
    private final String id;
    private String pw;
    private final ToDoList toDoList;

    public User(String id, String pw) {
        this.id = id;
        this.pw = pw;
        toDoList = new ToDoList();
    }

    public boolean checkId(String id) {
        return this.id.equals(id);
    }

    public boolean changePw(String oldPw, String newPw, String chkPw) {
        if (!this.pw.equals(oldPw)) {
            System.out.println("현재 비밀번호가 일치하지 않습니다.");
            return false;
        }

        if (!newPw.equals(chkPw)) {
            System.out.println("비밀번호 확인이 일치하지 않습니다.");
            return false;
        }

        this.pw = newPw;
        return true;
    }

    public boolean checkPw(String pw) {
        return this.pw.equals(pw);
    }

    public ToDoList getToDoList() {
        return toDoList;
    }
}
