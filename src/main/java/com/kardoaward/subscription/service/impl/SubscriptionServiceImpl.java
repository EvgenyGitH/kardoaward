package com.kardoaward.subscription.service.impl;

import com.kardoaward.exception.DataConflictException;
import com.kardoaward.exception.NotFoundException;
import com.kardoaward.subscription.model.Subscription;
import com.kardoaward.subscription.repository.SubscriptionRepository;
import com.kardoaward.subscription.service.SubscriptionService;
import com.kardoaward.user.dto.UserShortPage;
import com.kardoaward.user.mapper.UserMapper;
import com.kardoaward.user.model.User;
import com.kardoaward.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Override
    public void addSubscription(Long followerId, Long followingId) {
        User follower = userRepository.findById(followerId).orElseThrow(() -> new NotFoundException("User ID: " + followerId + " not found"));
        User following = userRepository.findById(followingId).orElseThrow(() -> new NotFoundException("User ID: " + followingId + " not found"));
        if (!existSubscription(followerId, followingId) && !followerId.equals(followingId)) {
            Subscription subscription = Subscription.builder()
                    .follower(follower)
                    .following(following)
                    .build();
            subscriptionRepository.save(subscription);

        } else {
            throw new DataConflictException("Not correct data");
        }
    }

    //подписчики
    @Override
    public List<UserShortPage> getUserFollowers(Long userId) {
        List<UserShortPage> followers;
        List<Subscription> userFollowers = subscriptionRepository.findAllByFollowingId(userId);

        if (!userFollowers.isEmpty()) {
            followers = UserMapper.usersToUserShortPage(userFollowers.stream()
                    .map(Subscription::getFollower)
                    .collect(Collectors.toList()));
        } else {
            followers = new ArrayList<>();
        }

        return followers;
    }


    //подписки
    @Override
    public List<UserShortPage> getUserFollowings(Long userId) {
        List<UserShortPage> followings;
        List<Subscription> userFollowings = subscriptionRepository.findAllByFollowerId(userId);
        if (!userFollowings.isEmpty()) {
            followings = UserMapper.usersToUserShortPage(userFollowings.stream()
                    .map(Subscription::getFollowing)
                    .collect(Collectors.toList()));
        } else {
            followings = new ArrayList<>();
        }
        return followings;
    }


    @Override
    public void deleteSubscription(Long followerId, Long followingId) {
        Optional<Subscription> subscription = subscriptionRepository.findByFollowerIdAndByFollowingId(followerId, followingId);
        if (subscription.isPresent()) {
            subscriptionRepository.deleteById(subscription.get().getId());
        } else {
            throw new NotFoundException("Subscription is not found");
        }
    }

    @Override
    public boolean existSubscription(Long followerId, Long followingId) {
        return subscriptionRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }

}
