package com.kardoaward;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserDto {

    private String key;
    private String name;
    private Long points = 0L;
    private Gender gender;
    private LocalDateTime regDate = LocalDateTime.now();

    public UserDto() {
    }

    public UserDto(String key, String name, Gender gender) {
        this.key = key;
        this.name = name;
        this.gender = gender;
    }

    public static UserDto of(String key, String value, Gender gender) {
        return new UserDto(key, value, gender);
    }

    // getters and setters

}