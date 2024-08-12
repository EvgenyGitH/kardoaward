package com.kardoaward.comment.controller;

import com.kardoaward.comment.dto.CommentDto;
import com.kardoaward.comment.dto.CommentNew;
import com.kardoaward.comment.dto.CommentUpdate;
import com.kardoaward.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@Validated
@RequestMapping(path = "/user/comments")
@Tag(name = "Комментарии к Публикациям  для авторизованных пользователей", description = "Управление публикациями")
public class PrivateCommentController {

    private final CommentService commentService;

    @PostMapping("/{userId}")
    @Operation(summary = "Создание Комментария")
    public CommentDto create(@PathVariable Long userId,
                             @RequestBody @Valid CommentNew commentNew) {
        log.info("request: create Comment");
        return commentService.createComment(userId, commentNew);
    }

    @PatchMapping("/{userId}/{commentId}")
    @Operation(summary = "Обновление Комментария")
    public CommentDto updateComment(@Parameter(description = "ID Пользователя") @PathVariable Long userId,
                                    @Parameter(description = "ID Комментария") @PathVariable Long commentId,
                                    @RequestBody @Valid CommentUpdate commentUpdate) {
        log.info("Request: update Comment");
        return commentService.updateComment(userId, commentId, commentUpdate);
    }


    @DeleteMapping("/{userId}/{commentId}")
    @Operation(summary = "Удаление Комментария")
    public void deleteCommentById(@Parameter(description = "ID Пользователя") @PathVariable Long userId,
                                  @Parameter(description = "ID Комментария") @PathVariable Long commentId) {
        log.info("Request: delete Comment");
        commentService.deleteCommentById(userId, commentId);
    }

}
