package com.kardoaward.user.service.impl;


import com.kardoaward.exception.DataConflictException;
import com.kardoaward.exception.DuplicateException;
import com.kardoaward.exception.NotCorrectDataException;
import com.kardoaward.exception.NotFoundException;
import com.kardoaward.post.dto.PostDto;
import com.kardoaward.post.service.PostService;
import com.kardoaward.security.jwt.CustomUserDetails;
import com.kardoaward.subscription.service.SubscriptionService;
import com.kardoaward.user.dto.*;
import com.kardoaward.user.mapper.UserMapper;
import com.kardoaward.user.model.State;
import com.kardoaward.user.model.Style;
import com.kardoaward.user.model.User;
import com.kardoaward.user.model.UserRole;
import com.kardoaward.user.repository.UserRepository;
import com.kardoaward.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    Long count = 0L;
    private final UserRepository userRepository;
    private final SubscriptionService subscriptionService;
    private final PostService postService;
    private final PasswordEncoder passwordEncoder;

    private Long getNumber() {
        return ++count;
    }

    // -- public -- //
    @Override
    @Transactional
    public UserProfile createUser(NewUserRequest newUserRequest) {
        User user = UserMapper.newUserRequestToUser(newUserRequest);
        checkEmail(newUserRequest.getEmail()); // удалить если будут добавлены предварительные экраны проверки почты и ника
        if (user.getNickname() == null) {
            user.setNickname(createNickname());
        } else {
            checkNickname(newUserRequest.getNickname());// удалить, если поле nickName будет всегда null
        }
        if (newUserRequest.getBirthday().isAfter(LocalDate.now().minusYears(6))) {
            throw new NotCorrectDataException("Ограничение по возрасту 6+");
        }
        String encodedPassword = passwordEncoder.encode(newUserRequest.getPassword());
        user.setPassword(encodedPassword);
        user.setState(State.ACTIVE);
        user.setUserRole(UserRole.USER);
        User savedUser;
        try {
            savedUser = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            log.error("Пользователь с указанным email уже зарегистрирован");
            throw new DataConflictException("Пользователь с указанным email уже зарегистрирован");
        }
        return UserMapper.userToUserProfile(savedUser);
    }

    @Override
    public List<UserShortPage> findUserByParam(String nickname, String firstName, String lastName, String middleName,
                                               LocalDate birthday, String country, String region, String city, String style, int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<User> foundUsers = userRepository.findAllByParam(nickname, firstName, lastName, middleName, birthday, country, region, city, style, pageable);
        return UserMapper.usersToUserShortPage(foundUsers);
    }


    //todo дополнить информацией из фич List<фича> ...
    @Override
    public UserPage getUserPageById(Long userId) {
        User savedUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + "is not found."));
        List<UserShortPage> iFollowings = subscriptionService.getUserFollowings(userId);
        List<UserShortPage> myFollowers = subscriptionService.getUserFollowers(userId);
        List<PostDto> posts = postService.getAllPostsByUserId(userId);
        return UserMapper.userToUserPage(savedUser, iFollowings, myFollowers, posts);
    }


    // -- private -- //
    @Override
    @Transactional
    public UserProfile updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        User savedUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + "is not found."));
        if (userUpdateRequest.getEmail() != null) {
            checkEmail(userUpdateRequest.getEmail());
            savedUser.setEmail(userUpdateRequest.getEmail());
        }
        if (userUpdateRequest.getPassword() != null) {
            savedUser.setPassword(userUpdateRequest.getPassword());
        }
        if (userUpdateRequest.getNickname() != null) {
            checkNickname(userUpdateRequest.getNickname());
            savedUser.setNickname(userUpdateRequest.getNickname());
        }
        if (userUpdateRequest.getFirstName() != null) {
            savedUser.setFirstName(userUpdateRequest.getFirstName());
        }
        if (userUpdateRequest.getLastName() != null) {
            savedUser.setLastName(userUpdateRequest.getLastName());
        }
        if (userUpdateRequest.getMiddleName() != null) {
            savedUser.setMiddleName(userUpdateRequest.getMiddleName());
        }
        if (userUpdateRequest.getBirthday() != null) {
            savedUser.setBirthday(userUpdateRequest.getBirthday());
        }
        if (userUpdateRequest.getCountry() != null) {
            savedUser.setCountry(userUpdateRequest.getCountry());
        }
        if (userUpdateRequest.getRegion() != null) {
            savedUser.setRegion(userUpdateRequest.getRegion());
        }
        if (userUpdateRequest.getCity() != null) {
            savedUser.setCity(userUpdateRequest.getCity());
        }
        if (userUpdateRequest.getCitizenship() != null) {
            savedUser.setCitizenship(userUpdateRequest.getCitizenship());
        }
        if (userUpdateRequest.getGender() != null) {
            savedUser.setGender(userUpdateRequest.getGender());
        }
        if (userUpdateRequest.getPhone() != null) {
            savedUser.setPhone(userUpdateRequest.getPhone());
        }
        if (userUpdateRequest.getPhotoLink() != null) {
            savedUser.setPageLink(userUpdateRequest.getPageLink());
        }
        if (userUpdateRequest.getBackgroundLink() != null) {
            savedUser.setBackgroundLink(userUpdateRequest.getBackgroundLink());
        }
        if (userUpdateRequest.getPageLink() != null) {
            savedUser.setPageLink(userUpdateRequest.getPageLink());
        }
        if (userUpdateRequest.getStyle() != null) {
            savedUser.setStyle(Style.valueOf(userUpdateRequest.getStyle()));
        }
        if (userUpdateRequest.getAboutMe() != null) {
            savedUser.setAboutMe(userUpdateRequest.getAboutMe());
        }
        userRepository.save(savedUser);
        return UserMapper.userToUserProfile(savedUser);
    }


    @Override
    public UserProfile getUserById(Long userId) {
        User savedUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + "is not found."));
        return UserMapper.userToUserProfile(savedUser);
    }

    @Override
    public UserProfile getUserByEmail(String email) {
        User savedUser = userRepository.findUserByEmailContainingIgnoreCase(email).get();
        return UserMapper.userToUserProfile(savedUser);
    }


    //soft delete
    @Override
    public UserProfile deleteUserById(Long userId) {
        User savedUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + "is not found."));
        savedUser.setState(State.DELETED);
        userRepository.save(savedUser);
        return UserMapper.userToUserProfile(savedUser);
    }


    @Override
    public boolean checkEmail(String email) {
        boolean flag = true;
        Optional<User> savedUser = userRepository.findUserByEmailContainingIgnoreCase(email);
        if (savedUser.isPresent() && savedUser.get().getEmail().equals(email)) {
            flag = true;
            log.error("Пользователь с указанным email уже зарегистрирован");
            throw new DuplicateException("Пользователь с указанным email уже зарегистрирован");
        } else {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean checkNickname(String nickname) {
        boolean flag = true;
        Optional<User> savedUser = userRepository.findUserByNicknameContainingIgnoreCase(nickname);
        if (savedUser.isPresent()) {
            flag = true;
            log.error("Пользователь, с указанным nickname, уже зарегистрирован");
            throw new DuplicateException("Пользователь, с указанным nickname: " + nickname + ", уже зарегистрирован");
        } else {
            flag = false;
        }
        return flag;
    }

    @Override
    public String createNickname() {
        String nickname = "User" + getNumber();
        boolean flag = true;
        while (flag) {
            Optional<User> savedUser = userRepository.findUserByNicknameContainingIgnoreCase(nickname);
            if (savedUser.isPresent()) {
                nickname = "User" + getNumber();
            } else {
                flag = false;
            }
        }
        return nickname;
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