package org.winterisend.board.post;

import java.time.LocalDateTime;

public class Post {
    public int id;
    public String title;
    public String content;
    public LocalDateTime created_at;
    public LocalDateTime updated_at;

    public Post(int _id, String _title, String _content) {
        id = _id;
        title = _title;
        content = _content;
        created_at = LocalDateTime.now();
        updated_at = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
        updated_at = LocalDateTime.now();
    }

    public void setContent(String content) {
        this.content = content;
        updated_at = LocalDateTime.now();
    }
}
