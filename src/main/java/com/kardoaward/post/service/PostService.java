package com.kardoaward.post.service;

import com.kardoaward.post.dto.NewPost;
import com.kardoaward.post.dto.PostDto;
import com.kardoaward.post.model.Post;

import java.util.List;

public interface PostService {
    PostDto createPost(Long userId, NewPost newPost);

    PostDto updatePost(Long userId, Long postId, NewPost newPost);

    List<PostDto> getAllPostsByUserId(Long userId);

    List<Post> findPostByParam(String nickname, String firstName, String lastName,
                               String text, int from, int size);

    void deletePost(Long userId, Long postId);


}
