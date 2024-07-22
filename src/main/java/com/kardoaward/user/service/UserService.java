package com.kardoaward.user.service;

import com.kardoaward.user.dto.NewUserRequest;
import com.kardoaward.user.dto.UserProfile;

public interface UserService {

    UserProfile createUser(NewUserRequest newUserRequest);


    void checkEmail(String email);

    void checkNickname(String nickname);
}
