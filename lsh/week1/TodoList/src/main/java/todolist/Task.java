package todolist;

import java.util.Date;

public class Task {
    private String title;
    private String content;
    private boolean finish;
    private Date createdAt;
    private Date updatedAt;

    public void setTitle(String _title) {
        this.title = _title;
    }

    public void setContent(String _content) {
        this.content = _content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date _updatedAt) {
        this.updatedAt = _updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date _createdAt) {
        this.createdAt = _createdAt;
    }

    public String isFinish() {
        if (finish) {
            return "○";
        }
        return "Ｘ";
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public void read() {
        System.out.println(String.format("%s[%s]", title, isFinish()));
        System.out.println(content);
    }
}
