package com.kardoaward.subscription.controller;

import com.kardoaward.subscription.service.SubscriptionService;
import com.kardoaward.user.dto.UserShortPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "user/users/subscriptions")
@Tag(name = "Подписки авторизованного User", description = "Управление подписками")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/{userId}/{followingId}")
    @Operation(summary = "Создание подписки")
    public void create(@Parameter(description = "User Id")@PathVariable Long userId,
                       @Parameter(description = "Following Id")@PathVariable Long followingId) {
        log.info("request: create Subscription");
        subscriptionService.addSubscription(userId, followingId);
    }

    @GetMapping("/followers/{userId}")
    @Operation(summary = "Получить список подписчиков")
    public List<UserShortPage> getUserFollowers (@Parameter(description = "User Id")@PathVariable Long userId){
        log.info("request: get User's Followers");
        return subscriptionService.getUserFollowers(userId);
    }
    @GetMapping("/followings/{userId}")
    @Operation(summary = "Получить список подписок")
    public List<UserShortPage> getUserFollowings (@Parameter(description = "User Id")@PathVariable Long userId){
        log.info("request: get User's Followings");
        return subscriptionService.getUserFollowings(userId);
    }

    @DeleteMapping("/{userId}/{followingId}")
    @Operation(summary = "Удаление подписки")
    public void deleteSubscription(@Parameter(description = "User Id")@PathVariable Long userId,
                                   @Parameter(description = "Following Id")@PathVariable Long followingId) {
        log.info("request: delete Subscription");
        subscriptionService.deleteSubscription(userId, followingId);
    }

}
