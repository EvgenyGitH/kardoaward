package com.kardoaward.user.mapper;

import com.kardoaward.user.dto.NewUserRequest;
import com.kardoaward.user.dto.UserProfile;
import com.kardoaward.user.model.User;

public class UserMapper {
    public static User newUserRequestToUser(NewUserRequest newUserRequest) {
        User user = new User();
        user.setEmail(newUserRequest.getEmail());
        user.setPassword(newUserRequest.getPassword());
        user.setNickname(newUserRequest.getNickname());
        user.setFirstName(newUserRequest.getFirstName());
        user.setLastName(newUserRequest.getLastName());
        user.setMiddleName(newUserRequest.getMiddle_name());
        user.setBirthday(newUserRequest.getBirthday());
        user.setCountry(newUserRequest.getCountry());
        user.setRegion(newUserRequest.getRegion());
        user.setCity(newUserRequest.getCity());
        user.setPhone(newUserRequest.getPhone());
        user.setPhotoLink(newUserRequest.getPhotoLink());
        user.setBackgroundLink(newUserRequest.getBackgroundLink());
        user.setPageLink(newUserRequest.getPageLink());
        user.setAboutMe(newUserRequest.getAboutMe());
        return user;
    }

    public static UserProfile UserToUserProfile(User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(user.getId());
        userProfile.setEmail(user.getEmail());
        userProfile.setPassword(user.getPassword());
        userProfile.setNickname(user.getNickname());
        userProfile.setFirstName(user.getFirstName());
        userProfile.setLastName(user.getLastName());
        userProfile.setMiddleName(user.getMiddleName());
        userProfile.setBirthday(user.getBirthday());
        userProfile.setCountry(user.getCountry());
        userProfile.setRegion(user.getRegion());
        userProfile.setCity(user.getCity());
        userProfile.setPhone(user.getPhone());
        userProfile.setPhotoLink(user.getPhotoLink());
        userProfile.setBackgroundLink(user.getBackgroundLink());
        userProfile.setPageLink(user.getPageLink());
        userProfile.setAboutMe(user.getAboutMe());
        userProfile.setState(user.getState());
        return userProfile;
    }

}
