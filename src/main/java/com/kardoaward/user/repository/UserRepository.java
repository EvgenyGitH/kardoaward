package com.kardoaward.user.repository;

import com.kardoaward.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findUserByNicknameContainingIgnoreCase(String nickname);
    Optional<User>findUserByEmailContainingIgnoreCase(String email);

    User findByEmail(String username);
}
