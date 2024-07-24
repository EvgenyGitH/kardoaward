package com.kardoaward.subscription.service.impl;

import com.kardoaward.exception.DataConflictException;
import com.kardoaward.exception.NotFoundException;
import com.kardoaward.subscription.dto.SubscriptionDto;
import com.kardoaward.subscription.mapper.SubscriptionMapper;
import com.kardoaward.subscription.model.Subscription;
import com.kardoaward.subscription.repository.SubscriptionRepository;
import com.kardoaward.subscription.service.SubscriptionService;
import com.kardoaward.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Override
    public void addSubscription(Long followerId, Long followingId) {
        if (userRepository.existsById(followerId) && userRepository.existsById(followingId) && !existSubscription(followerId, followingId)) {
            Subscription subscription = Subscription.builder()
                    .followerId(followerId)
                    .followingId(followingId)
                    .build();
            subscriptionRepository.save(subscription);

        } else {
            throw new DataConflictException("Not correct data");
        }
    }

    //подписчики
    @Override
    public List<SubscriptionDto> getUserFollowers(Long userId){
        List<SubscriptionDto> followers;
        List<Subscription> userFollowers = subscriptionRepository.findAllByFollowingId(userId);
                if (!userFollowers.isEmpty()){
                    followers = SubscriptionMapper.subscriptionToDtoList(userFollowers);
                }else {
                    followers = new ArrayList<>();
                }
           return followers;
    }

    //подписки
    @Override
    public List<SubscriptionDto> getUserFollowings(Long userId){
        List<SubscriptionDto> followings;
        List<Subscription> userFollowings = subscriptionRepository.findAllByFollowerId(userId);
        if (!userFollowings.isEmpty()){
            followings = SubscriptionMapper.subscriptionToDtoList(userFollowings);
        }else {
            followings = new ArrayList<>();
        }
        return followings;
    }

    @Override
    public void deleteSubscription (Long followerId, Long followingId){
        Optional<Subscription> subscription = subscriptionRepository.findByFollowerIdAndByFollowingId(followerId,followingId);
        if (subscription.isPresent()){
            subscriptionRepository.deleteById(subscription.get().getId());
        }else {
            throw new NotFoundException("Subscription is not found");
        }
    }

    @Override
    public boolean existSubscription(Long followerId, Long followingId){
        return subscriptionRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }



}
