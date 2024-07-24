package com.kardoaward.subscription.mapper;

import com.kardoaward.subscription.dto.SubscriptionDto;
import com.kardoaward.subscription.model.Subscription;

import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionMapper {

    public static SubscriptionDto subscriptionToSubscriptionDto(Subscription subscription){
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setFollowerId(subscription.getFollowerId());
        subscriptionDto.setFollowingId(subscription.getFollowingId());
        return subscriptionDto;
    }

    public static List<SubscriptionDto> subscriptionToDtoList (List<Subscription> subscriptions){
        return subscriptions.stream()
                .map(SubscriptionMapper::subscriptionToSubscriptionDto)
                .collect(Collectors.toList());
    }
}
