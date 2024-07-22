package com.kardoaward.user.controller;

import com.kardoaward.user.dto.NewUserRequest;
import com.kardoaward.user.dto.UserProfile;
import com.kardoaward.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "/users")
public class PublicUserController {

    private final UserService userService;

    @PostMapping
    public UserProfile create (@RequestBody @Valid NewUserRequest newUserRequest){
        log.info("request: create User");
        return userService.createUser(newUserRequest);
    }

}
