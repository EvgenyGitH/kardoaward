package com.kardoaward.post.repository;

import com.kardoaward.post.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p " +
            "FROM Post p " +
            "WHERE p.user.id = ?1 " +
            "ORDER BY p.datePost desc")
    List<Post> findAllByUserId(Long userId);


    @Query("SELECT p " +
            "FROM Post as p " +
            "JOIN p.user as u " +
            "WHERE (lower(u.nickname) like lower(concat('%', ?1, '%')) or ?1 is null) " +
            "AND (lower(u.firstName) like lower(concat('%', ?2, '%')) or ?2 is null) " +
            "AND (lower(u.lastName) like lower(concat('%', ?3, '%')) or ?3 is null) " +
            "AND (lower(p.text) like lower(concat('%', ?4, '%')) or ?4 is null) " +
            "ORDER BY p.datePost desc "
    )
    List<Post> findAllByParam(String nickname,
                              String firstName,
                              String lastName,
                              String text,
                              Pageable pageable);

}
