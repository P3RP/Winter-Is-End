package com.p3rp.recipe.service.implement;

import com.p3rp.recipe.domain.Post;
import com.p3rp.recipe.domain.dto.post.PostTO;
import com.p3rp.recipe.repository.PostRepository;
import com.p3rp.recipe.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Long save(PostTO postTO) {
        Post post = postTO.toEntity();
        return postRepository.save(post).getId();
    }

    @Override
    public PostTO getPostById(Long id) {
        return new PostTO(postRepository.getById(id));
    }

    @Override
    public PostTO updatePost(Long id, String title, String content) {
        Post post = postRepository.getById(id);
        post.setTitle(title);
        post.setContent(content);

        Post newPost = postRepository.save(post);

        return new PostTO(newPost);
    }

    @Override
    public Long deletePost(Long id) {
        Post post = postRepository.getById(id);
        Long target = post.getId();
        postRepository.delete(post);

        return target;
    }
}
