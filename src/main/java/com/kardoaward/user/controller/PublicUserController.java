package com.kardoaward.user.controller;

import com.kardoaward.user.dto.NewUserRequest;
import com.kardoaward.user.dto.UserPage;
import com.kardoaward.user.dto.UserProfile;
import com.kardoaward.user.dto.UserShortPage;
import com.kardoaward.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@Validated
@RequestMapping(path = "/public/users")
@Tag(name = "Данные User для неавторизованных пользователей", description = "Управление данными User")
public class PublicUserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Создание пользователя")
    public UserProfile create(@RequestBody @Valid NewUserRequest newUserRequest) {
        log.info("request: create User");
        return userService.createUser(newUserRequest);
    }

    @GetMapping("/search")
    @Operation(summary = "Поиск User (публичный профиль) по/без параметров")
    public List<UserShortPage> findUser(@RequestParam(required = false) String nickname,
                                        @RequestParam(required = false) String firstName,
                                        @RequestParam(required = false) String lastName,
                                        @RequestParam(required = false) String middleName,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday,
                                        @RequestParam(required = false) String country,
                                        @RequestParam(required = false) String region,
                                        @RequestParam(required = false) String city,
                                        @RequestParam(required = false) String style,
                                        @RequestParam(required = false, defaultValue = "0") @PositiveOrZero int from,
                                        @RequestParam(required = false, defaultValue = "10") @Positive int size
    ) {
        log.info("request: find User by param");
        return userService.findUserByParam(nickname, firstName, lastName, middleName, birthday, country, region, city, style, from, size);
    }

    @GetMapping("/page/{userId}")
    @Operation(summary = "Получение публичного профиля User")
    public UserPage getUserPageById(@Parameter(description = "ID Пользователя") @PathVariable Long userId) {
        log.info("Request: get User profile");
        return userService.getUserPageById(userId);
    }


    @GetMapping("/checkEmail")
    @Operation(summary = "Проверка уникальности email", description = "Проверка существования email в Базе данных/ exists - true / not exist - false")
    public boolean checkEmail(@RequestParam String email) {
        log.info("request: check Email ");
        return userService.checkEmail(email);
    }

    @GetMapping("/checkNickname")
    @Operation(summary = "Проверка уникальности Nickname", description = "Проверка существования Nickname в Базе данных/ exists - true / not exist - false")
    public boolean checkNickname(@RequestParam String nickname) {
        log.info("request: check nickname ");
        return userService.checkNickname(nickname);
    }

}
