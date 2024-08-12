package com.kardoaward.comment.repository;

import com.kardoaward.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostIdOrderByDateCommentDesc(Long postId);

}
