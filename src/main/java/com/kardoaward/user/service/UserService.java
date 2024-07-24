package com.kardoaward.user.service;

import com.kardoaward.user.dto.NewUserRequest;
import com.kardoaward.user.dto.UserPage;
import com.kardoaward.user.dto.UserProfile;
import com.kardoaward.user.dto.UserUpdateRequest;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    UserProfile createUser(NewUserRequest newUserRequest);

    List<UserPage> findUserByParam(String nickname, String firstName, String lastName, String middleName, LocalDate birthday, String country, String region, String city, String style, int from, int size);

    UserProfile updateUser(Long userId, UserUpdateRequest userUpdateRequest);

    UserProfile getUserById(Long userId);

    UserProfile deleteUserById(Long userId);


    void checkEmail(String email);

    void checkNickname(String nickname);
}
