package com.kardoaward.user.controller;

import com.kardoaward.user.dto.UserProfile;
import com.kardoaward.user.dto.UserUpdateRequest;
import com.kardoaward.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "/user/users")
@Tag(name = "Данные User для авторизованных пользователей", description = "Управление данными User")
public class ProfileUserController {

    private final UserService userService;

    @PatchMapping("/{userId}")
    @Operation(summary = "Обновление данных пользователя")
    public UserProfile updateUser(@Parameter(description = "ID Пользователя") @PathVariable Long userId,
                                  @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        log.info("Request: update User profile");
        return userService.updateUser(userId, userUpdateRequest);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Получение данных профиля User по id")
    public UserProfile getUserById(@Parameter(description = "ID Пользователя") @PathVariable Long userId) {
        log.info("Request: get User profile");
        return userService.getUserById(userId);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Получение данных профиля User по email")
    public UserProfile getUserById(@Parameter(description = "email Пользователя") @PathVariable String email) {
        log.info("Request: get User profile by email");
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Удаление профиля User")
    public UserProfile deleteUserById(@Parameter(description = "ID Пользователя") @PathVariable Long userId) {
        log.info("Request: delete User profile");
        return userService.deleteUserById(userId);
    }


}
