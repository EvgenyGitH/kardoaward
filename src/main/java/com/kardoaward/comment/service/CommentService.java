package com.kardoaward.comment.service;

import com.kardoaward.comment.dto.CommentDto;
import com.kardoaward.comment.dto.CommentNew;
import com.kardoaward.comment.dto.CommentUpdate;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long userId, CommentNew commentNew);

    CommentDto updateComment(Long userId, Long commentId, CommentUpdate commentUpdate);

    List<CommentDto> getAllCommentsByPostId(Long postId);

    void deleteCommentById(Long userId, Long commentId);

}
