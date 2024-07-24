package com.kardoaward.user.mapper;

import com.kardoaward.user.dto.NewUserRequest;
import com.kardoaward.user.dto.UserPage;
import com.kardoaward.user.dto.UserProfile;
import com.kardoaward.user.dto.UserUpdateRequest;
import com.kardoaward.user.model.Style;
import com.kardoaward.user.model.User;

import java.util.List;
import java.util.stream.Collectors;

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
        user.setStyle(Style.valueOf(newUserRequest.getStyle()));
        user.setAboutMe(newUserRequest.getAboutMe());
        return user;
    }

    public static User userUpdateRequestToUser(UserUpdateRequest userUpdateRequest) {
        User user = new User();
        user.setEmail(userUpdateRequest.getEmail());
        user.setPassword(userUpdateRequest.getPassword());
        user.setNickname(userUpdateRequest.getNickname());
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setMiddleName(userUpdateRequest.getMiddle_name());
        user.setBirthday(userUpdateRequest.getBirthday());
        user.setCountry(userUpdateRequest.getCountry());
        user.setRegion(userUpdateRequest.getRegion());
        user.setCity(userUpdateRequest.getCity());
        user.setPhone(userUpdateRequest.getPhone());
        user.setPhotoLink(userUpdateRequest.getPhotoLink());
        user.setBackgroundLink(userUpdateRequest.getBackgroundLink());
        user.setPageLink(userUpdateRequest.getPageLink());
        user.setStyle(Style.valueOf(userUpdateRequest.getStyle()));
        user.setAboutMe(userUpdateRequest.getAboutMe());
        return user;
    }

    public static UserProfile userToUserProfile(User user) {
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
        userProfile.setStyle(user.getStyle());
        userProfile.setAboutMe(user.getAboutMe());
        userProfile.setState(user.getState());
        return userProfile;
    }

    public static UserPage userToUserPage(User user) {
        UserPage userPage = new UserPage();
        userPage.setNickname(user.getNickname());
        userPage.setFirstName(user.getFirstName());
        userPage.setLastName(user.getLastName());
        userPage.setMiddleName(user.getMiddleName());
        userPage.setBirthday(user.getBirthday());
        userPage.setCountry(user.getCountry());
        userPage.setRegion(user.getRegion());
        userPage.setCity(user.getCity());
        userPage.setPhotoLink(user.getPhotoLink());
        userPage.setBackgroundLink(user.getBackgroundLink());
        userPage.setPageLink(user.getPageLink());
        userPage.setStyle(user.getStyle());
        userPage.setAboutMe(user.getAboutMe());
        userPage.setState(user.getState());
        return userPage;
    }

    public static List<UserPage> usersToUserPages(List<User> users) {
        return users.stream()
                .map(UserMapper::userToUserPage)
                .collect(Collectors.toList());
    }

}
