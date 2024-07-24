package com.kardoaward.user.repository;

import com.kardoaward.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByNicknameContainingIgnoreCase(String nickname);

    Optional<User> findUserByEmailContainingIgnoreCase(String email);

    @Query("SELECT u " +
            "FROM User u " +
            "WHERE (lower(u.nickname) like lower(concat('%', ?1, '%')) or ?1 is null) " +
            "AND (lower(u.firstName) like lower(concat('%', ?2, '%')) or ?2 is null) " +
            "AND (lower(u.lastName) like lower(concat('%', ?3, '%')) or ?3 is null) " +
            "AND (lower(u.middleName) like lower(concat('%', ?4, '%')) or ?4 is null) " +
            "AND (u.birthday = cast(?5 as timestamp) or  cast(?5 as timestamp) is null) " +
            "AND (lower(u.country) like lower(concat('%', ?6, '%')) or ?6 is null) " +
            "AND (lower(u.region) like lower(concat('%', ?7, '%')) or ?7 is null) " +
            "AND (lower(u.city) like lower(concat('%', ?8, '%')) or ?8 is null) " +
            "AND (lower(u.style) like lower(concat('%', ?9, '%')) or ?9 is null) "
    )
    List<User> findAllByParam(String nickname,
                              String firstName,
                              String lastName,
                              String middleName,
                              LocalDate birthday,
                              String country,
                              String region,
                              String city,
                              String style,
                              Pageable pageable);
}
