package com.kardoaward.user.service.impl;

import com.kardoaward.exception.DataConflictException;
import com.kardoaward.exception.DuplicateException;
import com.kardoaward.exception.NotFoundException;
import com.kardoaward.user.dto.NewUserRequest;
import com.kardoaward.user.dto.UserPage;
import com.kardoaward.user.dto.UserProfile;
import com.kardoaward.user.dto.UserUpdateRequest;
import com.kardoaward.user.mapper.UserMapper;
import com.kardoaward.user.model.State;
import com.kardoaward.user.model.User;
import com.kardoaward.user.model.UserRole;
import com.kardoaward.user.repository.UserRepository;
import com.kardoaward.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserProfile createUser(NewUserRequest newUserRequest) {
        User user = UserMapper.newUserRequestToUser(newUserRequest);
        checkNickname(newUserRequest.getNickname());
        user.setState(State.ACTIVE);
        user.setUserRole(UserRole.USER);
        User savedUser;
        try {
            savedUser = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DataConflictException("Пользователь с указанным email уже зарегистрирован");
        }
        return UserMapper.userToUserProfile(savedUser);
    }


    @Override
    public List<UserPage> findUserByParam(String nickname, String firstName, String lastName, String middleName,
                                          LocalDate birthday, String country, String region, String city, String style, int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<User> foundUsers = userRepository.findAllByParam(nickname, firstName, lastName, middleName, birthday, country, region, city, style, pageable);
        return UserMapper.usersToUserPages(foundUsers);
    }


    @Override
    @Transactional
    public UserProfile updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        //    User user = UserMapper.userUpdateRequestToUser(userUpdateRequest);
        // User savedUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + "is not found."));
        return null;
    }


    @Override
    public UserProfile getUserById(Long userId) {
        User savedUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + "is not found."));
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
    public void checkEmail(String email) {
        Optional<User> savedUser = userRepository.findUserByEmailContainingIgnoreCase(email);
        if (savedUser.isPresent() && savedUser.get().getEmail().equals(email)) {
            throw new DuplicateException("Пользователь с указанным email уже зарегистрирован");
        }
    }

    @Override
    public void checkNickname(String nickname) {
        Optional<User> savedUser = userRepository.findUserByNicknameContainingIgnoreCase(nickname);
        if (savedUser.isPresent() && savedUser.get().getNickname().equals(nickname)) {
            throw new DuplicateException("Пользователь с указанным nickname: " + nickname + " уже зарегистрирован");
        }
    }

}
