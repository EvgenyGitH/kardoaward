package com.kardoaward.post.controller;

import com.kardoaward.post.model.Post;
import com.kardoaward.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@Validated
@RequestMapping(path = "/public/posts")
@Tag(name = "Публикации для не авторизованных пользователей", description = "Управление публикациями")
public class PublicPostController {

    private final PostService postService;

    @GetMapping("/search")
    @Operation(summary = "Поиск Публикаций по/без параметров")
    public List<Post> findPosts(@RequestParam(required = false) String nickname,
                                @RequestParam(required = false) String firstName,
                                @RequestParam(required = false) String lastName,
                                @RequestParam(required = false) String text,
                                @RequestParam(required = false, defaultValue = "0") @PositiveOrZero int from,
                                @RequestParam(required = false, defaultValue = "10") @Positive int size
    ) {
        log.info("request: find Post by param");
        return postService.findPostByParam(nickname, firstName, lastName, text, from, size);
    }
}
