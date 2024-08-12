package com.kardoaward.post.service;

import com.kardoaward.post.dto.NewPost;
import com.kardoaward.post.dto.PostDto;
import com.kardoaward.post.dto.PostWithComments;

import java.util.List;

public interface PostService {
    PostDto createPost(Long userId, NewPost newPost);

    PostDto updatePost(Long userId, Long postId, NewPost newPost);

    List<PostDto> getAllPostsByUserId(Long userId);

    List<PostDto> findPostByParam(String nickname, String firstName, String lastName,
                                  String text, int from, int size);

    PostWithComments getPostWithCommentsById(Long postId);

    void deletePost(Long userId, Long postId);


}
