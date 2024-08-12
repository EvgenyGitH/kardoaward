package com.kardoaward.comment.service;

import com.kardoaward.comment.dto.CommentDto;
import com.kardoaward.comment.dto.CommentNew;
import com.kardoaward.comment.dto.CommentUpdate;
import com.kardoaward.comment.mapper.CommentMapper;
import com.kardoaward.comment.model.Comment;
import com.kardoaward.comment.repository.CommentRepository;
import com.kardoaward.exception.DataConflictException;
import com.kardoaward.exception.NotFoundException;
import com.kardoaward.post.model.Post;
import com.kardoaward.post.repository.PostRepository;
import com.kardoaward.user.model.User;
import com.kardoaward.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public CommentDto createComment(Long userId, CommentNew commentNew) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User ID: " + userId + " not found"));
        Post post = postRepository.findById(commentNew.getPostId()).orElseThrow(() -> new NotFoundException("Post ID: " + commentNew.getPostId() + " not found"));
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setSender(user);
        comment.setText(commentNew.getText());
        comment.setDateComment(LocalDateTime.now());
        commentRepository.save(comment);
        return CommentMapper.commentToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long userId, Long commentId, CommentUpdate commentUpdate) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Comment ID: " + commentId + " not found"));
        if (!comment.getSender().getId().equals(userId)) {
            throw new DataConflictException("Only owner of the post can update");
        }
        comment.setText(commentUpdate.getText());
        commentRepository.save(comment);
        return CommentMapper.commentToDto(comment);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostIdOrderByDateCommentDesc(postId);
        return CommentMapper.commentsToDtoList(comments);
    }

    @Override
    public void deleteCommentById(Long userId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Comment ID: " + commentId + " not found"));
        if (!comment.getSender().getId().equals(userId)) {
            throw new DataConflictException("Only owner of the post can update");
        }
        commentRepository.deleteById(commentId);
    }


}
