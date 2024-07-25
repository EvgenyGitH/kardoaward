package com.kardoaward.user.service.impl;


import com.kardoaward.exception.DataConflictException;
import com.kardoaward.exception.DuplicateException;
import com.kardoaward.security.jwt.CustomUserDetails;
import com.kardoaward.user.dto.NewUserRequest;
import com.kardoaward.user.dto.UserProfile;
import com.kardoaward.user.mapper.UserMapper;
import com.kardoaward.user.model.State;
import com.kardoaward.user.model.User;
import com.kardoaward.user.model.UserRole;
import com.kardoaward.user.repository.UserRepository;
import com.kardoaward.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserProfile createUser(NewUserRequest newUserRequest) {
        User user = UserMapper.newUserRequestToUser(newUserRequest);
        checkNickname(newUserRequest.getNickname());
        String encodedPassword = passwordEncoder.encode(newUserRequest.getPassword());
        user.setPassword(encodedPassword);
        user.setState(State.ACTIVE);
        user.setUserRole(UserRole.USER);
        User savedUser;
        try {
            savedUser = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DataConflictException("Пользователь с указанным email уже зарегистрирован");
        }
        return UserMapper.UserToUserProfile(savedUser);
    }

    public void checkEmail(String email) {
        Optional<User> savedUser = userRepository.findUserByEmailContainingIgnoreCase(email);
        if (savedUser.isPresent() && savedUser.get().getEmail().equals(email)) {
            throw new DuplicateException("Пользователь с указанным email уже зарегистрирован");
        }
    }

    public void checkNickname(String nickname) {
        Optional<User> savedUser = userRepository.findUserByNicknameContainingIgnoreCase(nickname);
        if (savedUser.isPresent() && savedUser.get().getEmail().equals(nickname)) {
            throw new DuplicateException("Пользователь с указанным nickname: " + nickname + " уже зарегистрирован");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(user.getUserRole().name())
        );
        return new CustomUserDetails(user.getEmail(), user.getPassword(), authorities);
    }
}