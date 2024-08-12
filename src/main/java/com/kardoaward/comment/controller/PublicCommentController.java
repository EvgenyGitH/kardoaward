package com.kardoaward.comment.controller;

import com.kardoaward.comment.dto.CommentDto;
import com.kardoaward.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@Validated
@RequestMapping(path = "/public/comments")
@Tag(name = "Комментарии к Публикациям", description = "Управление комментариями")
public class PublicCommentController {
    private final CommentService commentService;

    @GetMapping("/{postId}")
    @Operation(summary = "Получение списка Комментариев к посту")
    public List<CommentDto> getAllCommentsByPostId(@Parameter(description = "ID Публикации") @PathVariable Long postId) {
        log.info("request: get Post's Comments");
        return commentService.getAllCommentsByPostId(postId);
    }
}
