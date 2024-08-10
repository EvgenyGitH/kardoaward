package com.kardoaward.post.controller;

import com.kardoaward.post.dto.NewPost;
import com.kardoaward.post.dto.PostDto;
import com.kardoaward.post.service.PostService;
import com.kardoaward.user.dto.UserPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "/user/posts")
@Tag(name = "Публикации авторизованных пользователей", description = "Управление публикациями")
public class PrivatePostController {
    private final PostService postService;

    @PostMapping("/{userId}")
    @Operation(summary = "Создание публикации")
    public PostDto create(@PathVariable Long userId,
                          @RequestBody @Valid NewPost newPost) {
        log.info("request: create Post");
        return postService.createPost(userId, newPost);
    }

    @PatchMapping("/{userId}/{postId}")
    @Operation(summary = "Обновление публикации")
    public PostDto updatePost(@Parameter(description = "ID Пользователя") @PathVariable Long userId,
                              @Parameter(description = "ID Публикации") @PathVariable Long postId,
                              @RequestBody @Valid NewPost newPost) {
        log.info("Request: update Post");
        return postService.updatePost(userId, postId, newPost);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Получение публикаций Пользователя")
    public List<PostDto>getAllPostsByUserId(@Parameter(description = "ID Пользователя")@PathVariable Long userId) {
        log.info("Request: get User's Posts");
        return postService.getAllPostsByUserId(userId);
    }

    @DeleteMapping("/{userId}/{postId}")
    @Operation(summary = "Удаление публикации")
    public void deleteUserById(@Parameter(description = "ID Пользователя") @PathVariable Long userId,
                               @Parameter(description = "ID Публикации") @PathVariable Long postId) {
        log.info("Request: delete Post");
        postService.deletePost(userId, postId);
    }

}
