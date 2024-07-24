package com.kardoaward.user.controller;

import com.kardoaward.user.dto.NewUserRequest;
import com.kardoaward.user.dto.UserPage;
import com.kardoaward.user.dto.UserProfile;
import com.kardoaward.user.model.Style;
import com.kardoaward.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "/users")
public class PublicUserController {
    private final UserService userService;

    @PostMapping
    public UserProfile create(@RequestBody @Valid NewUserRequest newUserRequest) {
        log.info("request: create User");
        return userService.createUser(newUserRequest);
    }

    @GetMapping("/search")
    public List<UserPage> findUser(@RequestParam(required = false) String nickname,
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

    @GetMapping("/checkEmail")
    public boolean checkEmail(String email){
        log.info("request: check Email ");
        return userService.checkEmail(email);
    }

    @GetMapping("/checkEmail")
    public boolean checkNickname(String nickname){
        log.info("request: check nickname ");
        return userService.checkNickname(nickname);
    }

}
