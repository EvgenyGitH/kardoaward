package com.kardoaward.comment.mapper;

import com.kardoaward.comment.dto.CommentDto;
import com.kardoaward.comment.model.Comment;
import com.kardoaward.user.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {
    public static CommentDto commentToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setPostId(comment.getPost().getId());
        commentDto.setSender(UserMapper.userToUserShortPage(comment.getSender()));
        commentDto.setText(comment.getText());
        commentDto.setDateComment(comment.getDateComment());
        return commentDto;
    }

    public static List<CommentDto> commentsToDtoList(List<Comment> comments) {
        return comments.stream()
                .map(CommentMapper::commentToDto)
                .collect(Collectors.toList());
    }

}
