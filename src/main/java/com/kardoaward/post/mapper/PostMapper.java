package com.kardoaward.post.mapper;

import com.kardoaward.post.dto.NewPost;
import com.kardoaward.post.dto.PostDto;
import com.kardoaward.post.model.Post;
import com.kardoaward.user.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {
    public static Post newPostToPost(User user, NewPost newPost) {
        Post post = new Post();
        post.setUser(user);
        post.setLink(newPost.getLink());
        post.setText(newPost.getText());
        post.setDatePost(LocalDateTime.now());
        return post;
    }

    public static PostDto postToDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .userId(post.getUser().getId())
                .link(post.getLink())
                .text(post.getText())
                .datePost(post.getDatePost())
                .build();
    }

    public static List<PostDto> postToListDto(List<Post> posts) {
        return posts.stream()
                .map(PostMapper::postToDto)
                .collect(Collectors.toList());
    }

}
