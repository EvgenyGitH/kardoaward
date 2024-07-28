package com.kardoaward.subscription.controller;

import com.kardoaward.subscription.service.SubscriptionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "/user/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/{userId}/{followingId}")
    public void create(@PathVariable Long userId,
                       @PathVariable Long followingId) {
        log.info("request: create Subscription");
        subscriptionService.addSubscription(userId, followingId);
    }

    @DeleteMapping("/{userId}/{followingId}")
    public void deleteSubscription(@PathVariable Long userId,
                                   @PathVariable Long followingId) {
        log.info("request: delete Subscription");
        subscriptionService.deleteSubscription(userId, followingId);
    }

}
