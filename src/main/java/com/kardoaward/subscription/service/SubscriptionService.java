package com.kardoaward.subscription.service;

import com.kardoaward.subscription.dto.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {
    void addSubscription(Long followerId, Long followingId);
    boolean existSubscription(Long followerId, Long followingId);
    List<SubscriptionDto> getUserFollowers(Long userId);
    List<SubscriptionDto> getUserFollowings(Long userId);
    void deleteSubscription (Long followerId, Long followingId);

}
