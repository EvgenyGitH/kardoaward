package com.kardoaward.post.service;

import com.kardoaward.exception.DataConflictException;
import com.kardoaward.exception.NotFoundException;
import com.kardoaward.post.dto.NewPost;
import com.kardoaward.post.dto.PostDto;
import com.kardoaward.post.mapper.PostMapper;
import com.kardoaward.post.model.Post;
import com.kardoaward.post.repository.PostRepository;
import com.kardoaward.user.model.User;
import com.kardoaward.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public PostDto createPost(Long userId, NewPost newPost) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User ID: " + userId + " not found"));
        Post post = PostMapper.newPostToPost(user, newPost);
        postRepository.save(post);
        return PostMapper.postToDto(post);
    }

    @Override
    public PostDto updatePost(Long userId, Long postId, NewPost newPost) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("Post ID: " + postId + " not found"));
        if (!post.getUser().getId().equals(userId)) {
            throw new DataConflictException("Only owner of the post can update");
        }
        if (newPost.getLink() != null) {
            post.setLink(newPost.getLink());
        }
        if (newPost.getText() != null) {
            post.setText(newPost.getText());
        }
        postRepository.save(post);
        return PostMapper.postToDto(post);
    }

    @Override
    public List<PostDto> getAllPostsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User ID: " + userId + " not found");
        }
        List<Post> posts = postRepository.findAllByUserId(userId);
        return PostMapper.postToListDto(posts);
    }

    @Override
    public List<Post> findPostByParam(String nickname, String firstName, String lastName,
                                      String text, int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<Post> foundPost = postRepository.findAllByParam(nickname, firstName, lastName, text, pageable);
        return foundPost;
    }

    @Override
    public void deletePost(Long userId, Long postId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User ID: " + userId + " not found");
        }
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("Post ID: " + postId + " not found"));
        if (!post.getUser().getId().equals(userId)) {
            throw new DataConflictException("Only owner of the post can delete");
        }
        postRepository.deleteById(postId);
    }
}
