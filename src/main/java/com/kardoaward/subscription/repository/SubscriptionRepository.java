package com.kardoaward.subscription.repository;

import com.kardoaward.subscription.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);

    List<Subscription> findAllByFollowingId(Long userId);

    List<Subscription> findAllByFollowerId(Long userId);

    @Query("SELECT s " +
            "FROM Subscription s " +
            "JOIN s.follower " +
            "JOIN s.following " +
            "WHERE follower.id = ?1 " +
            "AND following.id = ?2")
    Optional<Subscription> findByFollowerIdAndByFollowingId(Long followerId, Long followingId);

}
