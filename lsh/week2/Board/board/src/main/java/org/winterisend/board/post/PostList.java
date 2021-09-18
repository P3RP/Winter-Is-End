package org.winterisend.board.post;

import java.util.ArrayList;
import java.util.HashMap;

public class PostList {
    private int idx = 1;
    private final HashMap<Integer, Post> postList;

    public PostList() {
        postList = new HashMap<>();

        for (int i = 0; i < 5; i ++) {
            Post post = new Post(
                    idx,
                    String.format("테스트 %d", i),
                    "테스트 내용 / 테스트 내용 / 테스트 내용 / 테스트 내용 / 테스트 내용 / 테스트 내용 / 테스트 내용 / "
            );
            postList.put(idx, post);
            idx++;
        }
    }

    public int getIdx() {
        return idx;
    }

    public Post getPost(int id) {
        return postList.get(id);
    }

    public int addPost(String title, String content) {
        Post post = new Post(idx, title, content);
        postList.put(idx, post);
        idx++;
        return post.id;
    }

    public void editPostTitle(int id, String title) {
        Post post = postList.get(id);
        post.setTitle(title);
    }

    public void editPostContent(int id, String content) {
        Post post = postList.get(id);
        post.setContent(content);
    }

    public void deletePost(int id) {
        postList.remove(id);
    }

    public HashMap<Integer, Post> getPostList() {
        return postList;
    }
}
