package com.kardoaward.user.controller;

import com.kardoaward.user.dto.UserProfile;
import com.kardoaward.user.dto.UserUpdateRequest;
import com.kardoaward.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import jakarta.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "user/users")
public class ProfileUserController {

    private final UserService userService;

    @PatchMapping("/{userId}")
    public UserProfile updateUser(@PathVariable Long userId,
                                  @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        log.info("Request: update User profile");
        return userService.updateUser(userId, userUpdateRequest);
    }

    @GetMapping("/{userId}")
    public UserProfile getUserById(@PathVariable Long userId) {
        log.info("Request: get User profile");
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public UserProfile deleteUserById(@PathVariable Long userId) {
        log.info("Request: delete User profile");
        return userService.deleteUserById(userId);
    }


}
