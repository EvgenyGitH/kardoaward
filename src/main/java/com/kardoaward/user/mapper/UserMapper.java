package com.kardoaward.user.mapper;

import com.kardoaward.user.dto.*;
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
        user.setMiddleName(userUpdateRequest.getMiddleName());
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

        userProfile.setNickname(user.getNickname());
        userProfile.setFirstName(user.getFirstName());
        userProfile.setLastName(user.getLastName());
        userProfile.setMiddleName(user.getMiddleName());
        userProfile.setBirthday(user.getBirthday());
        userProfile.setCountry(user.getCountry());
        userProfile.setRegion(user.getRegion());
        userProfile.setCity(user.getCity());
        userProfile.setCitizenship(user.getCitizenship());
        userProfile.setGender(user.getGender());
        userProfile.setPhone(user.getPhone());
        userProfile.setPhotoLink(user.getPhotoLink());
        userProfile.setBackgroundLink(user.getBackgroundLink());
        userProfile.setPageLink(user.getPageLink());
        userProfile.setStyle(user.getStyle());
        userProfile.setAboutMe(user.getAboutMe());
        userProfile.setState(user.getState());
        return userProfile;
    }

    public static UserShortPage userToUserShortPage(User user) {
        UserShortPage userShortPage = new UserShortPage();
        userShortPage.setId(user.getId());
        userShortPage.setNickname(user.getNickname());
        userShortPage.setFirstName(user.getFirstName());
        userShortPage.setLastName(user.getLastName());
        userShortPage.setPhotoLink(user.getPhotoLink());
        userShortPage.setStyle(user.getStyle());
        userShortPage.setState(user.getState());
        return userShortPage;
    }

    public static List<UserShortPage> usersToUserShortPage(List<User> users) {
        return users.stream()
                .map(UserMapper::userToUserShortPage)
                .collect(Collectors.toList());
    }


    //todo дополнить фичами
    public static UserPage userToUserPage(User user, List<UserShortPage> iFollowings, List<UserShortPage> myFollowers) {
        UserPage userPage = new UserPage();
        userPage.setId(user.getId());
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
        userPage.setIFollowings(iFollowings);
        userPage.setMyFollowers(myFollowers);
        return userPage;
    }

}
