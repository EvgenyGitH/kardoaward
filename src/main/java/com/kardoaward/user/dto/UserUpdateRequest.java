package com.kardoaward.user.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @Email
    private String email;
    private String password;
    private String nickname;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthday;
    private String country;
    private String region;
    private String city;
    private String phone;
    private String photoLink;
    private String backgroundLink;
    private String pageLink;
    private String style;
    private String aboutMe;


}
