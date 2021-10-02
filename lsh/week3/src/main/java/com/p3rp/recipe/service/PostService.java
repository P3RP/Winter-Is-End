package com.p3rp.recipe.service;

import com.p3rp.recipe.domain.dto.post.PostTO;

public interface PostService {
    Long save(PostTO postTO);
    PostTO getPostById(Long id);
    PostTO updatePost(Long id, String title, String content);
    Long deletePost(Long id);
}
