package com.kardoaward.subscription.service;

import com.kardoaward.user.dto.UserShortPage;

import java.util.List;

public interface SubscriptionService {
    void addSubscription(Long followerId, Long followingId);

    boolean existSubscription(Long followerId, Long followingId);

    List<UserShortPage> getUserFollowers(Long userId);

    List<UserShortPage> getUserFollowings(Long userId);

    void deleteSubscription(Long followerId, Long followingId);

}
