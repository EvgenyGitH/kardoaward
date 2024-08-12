package com.kardoaward.user.service;

import com.kardoaward.user.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    UserProfile createUser(NewUserRequest newUserRequest);

    List<UserShortPage> findUserByParam(String nickname, String firstName, String lastName, String middleName, LocalDate birthday, String country, String region, String city, String style, int from, int size);

    UserPage getUserPageById(Long userId);

    UserProfile updateUser(Long userId, UserUpdateRequest userUpdateRequest);

    UserProfile getUserById(Long userId);

    UserProfile getUserByEmail(String email);

    UserProfile deleteUserById(Long userId);

    boolean checkEmail(String email);

    boolean checkNickname(String nickname);

    String createNickname();
}
